package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.service.dto.CodedConceptAndCodeSystemOidDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryFieldsDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryMapDto;

import java.util.List;

public interface ValueSetLookupService {
    List<ValueSetCategoryMapDto> lookupValueSetCategories(List<CodedConceptAndCodeSystemOidDto> codedConceptAndCodeSystemOidDtos);

    List<ValueSetCategoryFieldsDto> lookupSensitivityPolicies();
}