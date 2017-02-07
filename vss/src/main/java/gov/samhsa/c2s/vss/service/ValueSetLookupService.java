package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.service.dto.CodedConceptAndCodeSystemOidDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryMapDto;

import java.util.List;

public interface ValueSetLookupService {
    List<ValueSetCategoryDto> lookupSensitivityPolicies();

    List<ValueSetCategoryMapDto> lookupValueSetCategories(List<CodedConceptAndCodeSystemOidDto> codedConceptAndCodeSystemOidDtos);
}