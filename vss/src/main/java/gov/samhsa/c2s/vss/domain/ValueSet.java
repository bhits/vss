package gov.samhsa.c2s.vss.domain;

import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import gov.samhsa.c2s.vss.domain.valueobject.RevisionRecord;
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

    @OneToMany(mappedBy = "valueSet", fetch = FetchType.LAZY)
    @NotAudited
    private List<CodedConceptValueSet> codedConcepts;

    @Embedded
    private CodeName codeName;

    @Embedded
    private RevisionRecord revisionRecord;
}