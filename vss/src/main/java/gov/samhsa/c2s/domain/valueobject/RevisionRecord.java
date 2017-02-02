package gov.samhsa.c2s.domain.valueobject;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Embeddable
@Audited
@Data
public class RevisionRecord {

    @Column
    @CreatedDate
    private LocalDateTime createdTime;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedTime;

    @Column
    @NotNull
    private String userName;
}