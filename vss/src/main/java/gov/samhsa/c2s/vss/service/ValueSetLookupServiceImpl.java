package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.service.dto.CodedConceptAndCodeSystemOidDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryMapDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValueSetLookupServiceImpl implements ValueSetLookupService {
    @Override
    public List<ValueSetCategoryMapDto> lookupValueSetCategories(List<CodedConceptAndCodeSystemOidDto> codedConceptAndCodeSystemOidDtos) {
        return null;
    }
}