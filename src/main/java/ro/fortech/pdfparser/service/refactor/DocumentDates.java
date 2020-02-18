package ro.fortech.pdfparser.service.refactor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Scanner;

@Service
public class DocumentDates {


    public String getDate(String parsedText){

        Scanner scanner = new Scanner(parsedText);

                String date = "";

                for (int i = 0; i < 3; i++) {
                    scanner.nextLine();
                    if (i == 2) {

                        date = scanner.nextLine();
                    }
                }
                return date;

    }
}
