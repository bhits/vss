package gov.samhsa.c2s.vss.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Audited
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeName {

    @Column(unique = true)
    @NotNull
    private String code;

    @Column(columnDefinition = "mediumtext")
    @NotNull
    private String name;
}