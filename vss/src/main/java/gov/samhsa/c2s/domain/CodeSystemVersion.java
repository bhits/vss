package gov.samhsa.c2s.domain;

import gov.samhsa.c2s.domain.valueobject.RevisionRecord;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Audited
@Data
public class CodeSystemVersion {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String description;

    @Embedded
    private RevisionRecord revisionRecord;

    @ManyToOne
    private CodeSystem codeSystem;
}