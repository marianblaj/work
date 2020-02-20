package ro.fortech.pdfparser.entity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class FileAlreadyParsedException extends Exception {
    public FileAlreadyParsedException(String message){
        super(message);
    }

}
