package ro.fortech.pdfparser.entity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class FileAlreadyParsedException extends RuntimeException {
    public FileAlreadyParsedException(String id){
        super(id);
    }

}
