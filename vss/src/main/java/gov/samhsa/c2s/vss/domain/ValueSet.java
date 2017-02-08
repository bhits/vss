package gov.samhsa.c2s.vss.domain;

import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
@Audited
@Data
public class ValueSet {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToOne
    private ValueSetCategory valueSetCategory;

    @ManyToMany(mappedBy = "valueSets")
    @NotAudited
    private List<CodedConcept> codedConcepts;

    @Embedded
    private CodeName codeName;
}