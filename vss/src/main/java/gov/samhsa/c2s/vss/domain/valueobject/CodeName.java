package gov.samhsa.c2s.vss.domain.valueobject;

import lombok.Builder;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Audited
@Data
@Builder
public class CodeName {

    @Column(unique = true)
    @NotNull
    private String code;

    @Column
    @NotNull
    private String name;
}