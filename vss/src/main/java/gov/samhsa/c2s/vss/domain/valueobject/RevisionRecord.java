package gov.samhsa.c2s.vss.domain.valueobject;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Embeddable
@Audited
@Data
public class RevisionRecord {

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @CreatedDate
    private Date createdTime;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @LastModifiedDate
    private Date modifiedTime;

    @Column
    @NotNull
    private String userName;
}