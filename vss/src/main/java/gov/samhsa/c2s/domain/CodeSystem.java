package gov.samhsa.c2s.domain;

import gov.samhsa.c2s.domain.valueobject.CodeName;
import gov.samhsa.c2s.domain.valueobject.RevisionRecord;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private String codeSystemOId;

    private String displayName;

    @Embedded
    private CodeName codeName;

    @Embedded
    private RevisionRecord revisionRecord;
}