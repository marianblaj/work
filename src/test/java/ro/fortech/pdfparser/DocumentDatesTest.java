package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.refactor.DocumentDates;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentDatesTest {

    @Autowired
    private DocumentDates documentDates ;

    @Test
    public void whenStringParsedText_thenReturnDate(){
        String textDate = String.join("\n"
                , "Line 0,"
                , "Line 1,"
                , "Line 2,"
                , "Line 3,"
        );
        String date = documentDates.getDate(textDate);
        System.out.println(date);

    }
}
