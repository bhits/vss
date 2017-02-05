package gov.samhsa.c2s.vss.domain;

import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import gov.samhsa.c2s.vss.domain.valueobject.RevisionRecord;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"code",
        "code_system_version_id"}))
@Audited
@Data
public class CodedConcept {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToOne
    private CodeSystemVersion codeSystemVersion;

    @OneToMany(mappedBy = "codedConcept", fetch = FetchType.LAZY)
    @NotAudited
    private List<CodedConceptValueSet> valueSets;

    @Embedded
    private CodeName codeName;

    @Embedded
    private RevisionRecord revisionRecord;
}