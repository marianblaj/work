package ro.fortech.pdfparser.service.refactor;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.fortech.pdfparser.service.ParsedPdfDto;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@Component

//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PdfParser {

    @Autowired
    private DocumentDates documentDates;

    @Autowired
    private PdfLines pdfLines;

    public ParsedPdfDto parse(InputStream file) {
        try {
            PDFParser parser = getPdfParsed(file);
            try (COSDocument cosDoc = parser.getDocument()) {
                PDDocument pdDoc = new PDDocument(cosDoc);
                PDFTextStripper pdfStripper = configureParser(pdDoc);
                String parsedText = pdfStripper.getText(pdDoc);
                String[] lines = parsedText.split(System.lineSeparator());
                String date = documentDates.getDate(parsedText);
                String[] dates = date.split("\\D");
                LocalDate startDate = LocalDate.of(Integer.parseInt(dates[2]), Integer.parseInt(dates[1]), Integer.parseInt(dates[0]));
                LocalDate endDate = LocalDate.of(Integer.parseInt(dates[8]), Integer.parseInt(dates[7]), Integer.parseInt(dates[6]));
                String firma = lines[0];
                String numeFirma = firma.substring(0, firma.indexOf("c.f."));
                String cif = firma.substring(firma.indexOf("RO"), firma.indexOf("r.c."));
                ParsedPdfDto dto = getParsedPdfDto(numeFirma, cif, startDate, endDate);
                ParsedPdfDto dtoFinal = pdfLines.addPdfLines(lines, dto);
                return  dtoFinal;
                //addPdfToDatabase(dtoFinal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private ParsedPdfDto getParsedPdfDto(String numeFirma, String cif, LocalDate startDate, LocalDate endDate) {
        ParsedPdfDto dto = new ParsedPdfDto();
        dto.setCf(cif);
        dto.setNumeFirma(numeFirma);
        dto.setFrom(startDate);
        dto.setTo(endDate);

        return dto;
    }
    private PDFTextStripper configureParser(PDDocument pdDoc) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setSortByPosition(true);
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
        return pdfStripper;
    }
    private PDFParser getPdfParsed(InputStream file) throws IOException {
        PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(file));
        parser.parse();
        return parser;
    }
}
