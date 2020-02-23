package ro.fortech.pdfparser.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public abstract class BaseEntity {

    @Id
    private long id;
    
}
