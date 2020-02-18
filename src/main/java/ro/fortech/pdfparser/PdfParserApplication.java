package ro.fortech.pdfparser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;
import ro.fortech.pdfparser.service.ParserService;

@SpringBootApplication
public class PdfParserApplication implements CommandLineRunner {

	@Autowired
	private BalanceSheetRepository balanceSheetRepository;

	@Autowired
	private BalanceSheetLineRepository balanceSheetLineRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(PdfParserApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
		new ParserService(balanceSheetRepository, balanceSheetLineRepository).importPdf();
	}

}
