package gov.samhsa.c2s.vss.domain;

import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
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

    @ManyToMany
    @JoinTable(name = "coded_concept_value_set", joinColumns = @JoinColumn(name = "coded_concept_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "value_set_id", referencedColumnName = "id"))
    @NotAudited
    private List<ValueSet> valueSets;

    @Embedded
    private CodeName codeName;
}