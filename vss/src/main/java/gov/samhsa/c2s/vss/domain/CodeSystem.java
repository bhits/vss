package gov.samhsa.c2s.vss.domain;

import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import gov.samhsa.c2s.vss.domain.valueobject.RevisionRecord;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"code_system_oid"}))
@Audited
@Data
public class CodeSystem {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "code_system_oid")
    private String codeSystemOid;

    private String displayName;

    @OneToMany(mappedBy = "codeSystem")
    @NotAudited
    private List<CodeSystemVersion> codeSystemVersions;

    @Embedded
    private CodeName codeName;

    @Embedded
    private RevisionRecord revisionRecord;
}