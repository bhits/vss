package gov.samhsa.c2s.vss.domain;

import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.List;

@Entity
@Audited
@Data
public class ValueSetCategory {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 5000)
    private String description;

    private boolean isFederal;

    private int displayOrder;

    @OneToMany(mappedBy = "valueSetCategory")
    @NotAudited
    private List<ValueSet> valueSets;

    @Embedded
    private CodeName codeName;
}