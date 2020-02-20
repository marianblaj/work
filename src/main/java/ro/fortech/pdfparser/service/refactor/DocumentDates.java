package ro.fortech.pdfparser.service.refactor;

import lombok.experimental.UtilityClass;

import java.util.Scanner;

@UtilityClass
public class DocumentDates {

    public static String getDate(String parsedText) {
        Scanner scanner = new Scanner(parsedText);

        String date = "";

        for(int i = 0; i < 3; i++) {
            scanner.nextLine();
            if(i == 2) {
                date = scanner.nextLine();
            }

        }

        return date;
    }


}

