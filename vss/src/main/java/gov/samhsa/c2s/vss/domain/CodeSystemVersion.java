package gov.samhsa.c2s.vss.domain;

import gov.samhsa.c2s.vss.domain.valueobject.RevisionRecord;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(indexes = @Index(columnList = "versionOrder", name = "version_order_idx", unique = true))
@Audited
@Data
public class CodeSystemVersion {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String versionName;

    @NotNull
    @Min(1)
    private int versionOrder;

    private String description;

    @ManyToOne
    private CodeSystem codeSystem;

    @OneToMany(mappedBy = "codeSystemVersion")
    @NotAudited
    private List<CodedConcept> codedConcepts;

    @Embedded
    private RevisionRecord revisionRecord;
}