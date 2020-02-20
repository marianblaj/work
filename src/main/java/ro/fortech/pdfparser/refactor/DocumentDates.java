package ro.fortech.pdfparser.refactor;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@UtilityClass
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
