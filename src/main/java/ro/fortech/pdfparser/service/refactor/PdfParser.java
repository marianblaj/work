package ro.fortech.pdfparser.service.refactor;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service

//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PdfParser{

    public PdfParser() {
    }

    private BalanceSheetRepository balanceSheetRepository;
    private BalanceSheetLineRepository balanceSheetLineRepository;

    public PdfParser(BalanceSheetRepository balanceSheetRepository, BalanceSheetLineRepository balanceSheetLineRepository) {
        this.balanceSheetRepository = balanceSheetRepository;
        this.balanceSheetLineRepository = balanceSheetLineRepository;
    }

    private DocumentDates documentDates = new DocumentDates();

    private PdfLines pdfLines = new PdfLines();

//    @Autowired
//    public PdfParser(DocumentDates documentDates, PdfLines pdfLines) {
//        this.documentDates = documentDates;
//        this.pdfLines = pdfLines;
//    }


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


                ParsedPdfDto dtoFinal =  pdfLines.addPdfLines(lines, dto);

                addPdfToDatabase(dtoFinal);



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
//    public BalanceSheetEntity toBalanceSheetEntity(ParsedPdfDto pojo) {
//        BalanceSheetEntity bal = new BalanceSheetEntity();
//        bal.setCf(pojo.getCf());
//        bal.setFrom(pojo.getFrom());
//        bal.setTo(pojo.getTo());
//        //BalanceSheetLineEntity balanceSheetLineEntity = new BalanceSheetLineEntity();
//        bal.setLines(pojo.getLines()
//                .stream()
//                .map(ParsedPdfLineDto::update)
//                .collect(Collectors.toList()));
//        return bal;
//    }

    public void addPdfToDatabase(ParsedPdfDto parsedPdfDto){

        BalanceSheetEntity bal = new BalanceSheetEntity();
        bal = bal.toBalanceSheetEntity(parsedPdfDto);


        balanceSheetRepository.save(bal);
       for (int i =0; i<bal.getLines().size() ; i++)
        balanceSheetLineRepository.save(bal.getLines().get(i));

    }
}
