package gov.samhsa.c2s.vss.domain;

import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Audited
@Data
public class CodeSystem {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String codeSystemOid;

    private String displayName;

    @OneToMany(mappedBy = "codeSystem")
    @NotAudited
    private List<CodeSystemVersion> codeSystemVersions;

    @Embedded
    private CodeName codeName;
}