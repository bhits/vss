package gov.samhsa.c2s.vss.service.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class CodedConceptAndCodeSystemOidDto {
    @NotEmpty
    private String codeConceptCode;
    @NotEmpty
    private String codeSystemOid;
}