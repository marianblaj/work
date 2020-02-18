package ro.fortech.pdfparser.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class BaseEntity {

    @Id
    private long id;

}
