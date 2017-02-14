package gov.samhsa.c2s.vss.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodedConceptAndCodeSystemOidDto {
    @NotEmpty
    private String codedConceptCode;
    @NotEmpty
    private String codeSystemOid;
}