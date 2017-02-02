package gov.samhsa.c2s.domain;

import gov.samhsa.c2s.domain.valueobject.CodeName;
import gov.samhsa.c2s.domain.valueobject.RevisionRecord;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Data
public class ValueSetCategory {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private boolean isFederal;

    private Long displayOrder;

    @Embedded
    private CodeName codeName;

    @Embedded
    private RevisionRecord revisionRecord;
}