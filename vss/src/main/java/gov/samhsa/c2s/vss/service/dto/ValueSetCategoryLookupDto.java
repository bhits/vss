package gov.samhsa.c2s.vss.service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ValueSetCategoryLookupDto {
    private Set<String> valueSetCategoryCodes;
}