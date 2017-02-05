package gov.samhsa.c2s.vss.service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ValueSetCategoryMapDto {
    private String codeConceptCode;

    private String codeSystemOid;

    private Set<String> valueSetCategoryCodes;
}