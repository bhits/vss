package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.service.dto.CodedConceptAndCodeSystemOidDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryFieldsDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryLookupDto;

import java.util.List;

public interface ValueSetLookupService {
    List<ValueSetCategoryLookupDto> lookupValueSetCategories(List<CodedConceptAndCodeSystemOidDto> codedConceptAndCodeSystemOidDtos);

    List<ValueSetCategoryFieldsDto> lookupSensitivityPolicies();
}