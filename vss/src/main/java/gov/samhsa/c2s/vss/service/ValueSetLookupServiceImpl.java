package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.common.log.Logger;
import gov.samhsa.c2s.common.log.LoggerFactory;
import gov.samhsa.c2s.vss.domain.ValueSetCategoryRepository;
import gov.samhsa.c2s.vss.service.dto.CodedConceptAndCodeSystemOidDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryFieldsDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryLookupDto;
import gov.samhsa.c2s.vss.service.exception.SensitivityPolicySearchFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValueSetLookupServiceImpl implements ValueSetLookupService {
    private Logger logger = LoggerFactory.getLogger(this);

    @Autowired
    private ValueSetCategoryRepository valueSetCategoryRepository;

    @Override
    public List<ValueSetCategoryLookupDto> lookupValueSetCategories(List<CodedConceptAndCodeSystemOidDto> codedConceptAndCodeSystemOidDtos) {
        return null;
    }

    @Override
    public List<ValueSetCategoryFieldsDto> lookupSensitivityPolicies() {
        List<ValueSetCategoryFieldsDto> sensitivityPolicyDto = new ArrayList<>();
        try {
            valueSetCategoryRepository.findAll()
                    .forEach(
                            valueSetCategoryDto -> {
                                ValueSetCategoryFieldsDto sensitivityPolicyDtoItem =
                                        new ValueSetCategoryFieldsDto(valueSetCategoryDto.getCodeName().getCode(),
                                                valueSetCategoryDto.getCodeName().getName(),
                                                valueSetCategoryDto.getDescription(),
                                                valueSetCategoryDto.isFederal(),
                                                valueSetCategoryDto.getDisplayOrder());
                                sensitivityPolicyDto.add(sensitivityPolicyDtoItem);
                            }
                    );
        } catch (Exception e) {
            logger.error(() -> "SensitivityPolicy search failed: " + e.getMessage());
            logger.debug(e::getMessage, e);
            throw new SensitivityPolicySearchFailedException();
        }
        return sensitivityPolicyDto;
    }
}