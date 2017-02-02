package gov.samhsa.c2s.domain.valueobject;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Audited
@Data
public class CodeName {

    @Column(unique = true)
    @NotNull
    private String code;

    @Column
    @NotNull
    private String name;
}