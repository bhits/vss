package gov.samhsa.c2s.vss.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueSetCategoryMapDto {
    private String codeConceptCode;
    private String codeSystemOid;
    private Set<String> valueSetCategoryCodes;
}