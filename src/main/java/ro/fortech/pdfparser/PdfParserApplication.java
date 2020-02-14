package ro.fortech.pdfparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;
import ro.fortech.pdfparser.service.refactor.ImportPdf;
import ro.fortech.pdfparser.service.refactor.PdfParser;

@SpringBootApplication
public class PdfParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfParserApplication.class, args);
	}

}

//@SpringBootApplication
//public class PdfParserApplication implements CommandLineRunner {
//
//	@Autowired
//	private   BalanceSheetRepository balanceSheetRepository;
//
//	@Autowired
//	private BalanceSheetLineRepository balanceSheetLineRepository;
//
//	public static void main(String[] args) {
//		SpringApplication.run(PdfParserApplication.class, args);
//
//	}
//
//
//	@Override
//	public void run(String...args) throws Exception {
//		String path = "/2017 SAS balanta 31122017.pdf";
//		//new ParserPdfService().importPdf();
//		//  new ParserPdfService().importPdf();
//		// new ImportPdf().importPdf(path);
//		try {
//			new ImportPdf(new PdfParser(balanceSheetRepository, balanceSheetLineRepository)).importPdf(path);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
