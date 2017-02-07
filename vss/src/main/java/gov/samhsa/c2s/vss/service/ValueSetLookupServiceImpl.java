package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.common.log.Logger;
import gov.samhsa.c2s.common.log.LoggerFactory;
import gov.samhsa.c2s.vss.domain.*;
import gov.samhsa.c2s.vss.service.dto.CodedConceptAndCodeSystemOidDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryFieldsDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryLookupDto;
import gov.samhsa.c2s.vss.service.exception.SensitivityPolicySearchFailedException;
import gov.samhsa.c2s.vss.service.exception.ValueSetCategoriesSearchFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class ValueSetLookupServiceImpl implements ValueSetLookupService {
    private Logger logger = LoggerFactory.getLogger(this);

    @Autowired
    private ValueSetCategoryRepository valueSetCategoryRepository;

    @Autowired
    private ValueSetRepository valueSetRepository;

    @Autowired
    private CodedConceptRepository codedConceptRepository;

    @Autowired
    private CodeSystemVersionRepository codeSystemVersionRepository;

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

    @Override
    public List<ValueSetCategoryLookupDto> lookupValueSetCategories(List<CodedConceptAndCodeSystemOidDto> codedConceptAndCodeSystemOidDtos) {
        return codedConceptAndCodeSystemOidDtos.stream()
                .map(dto -> getValueSetCategoryLookupDto(dto.getCodeConceptCode(), dto.getCodeSystemOid()))
                .collect(toList());
    }

    private ValueSetCategoryLookupDto getValueSetCategoryLookupDto(String codeConceptCode, String codeSystemOid) {

        Set<String> valueSetCategoryCodes = new HashSet<>();

        try {
            // 1.Get latest version of Code System version for the given code system oid
            List<CodeSystemVersion> codeSystemVersions = codeSystemVersionRepository
                    .findAllByCodeSystemCodeSystemOidOrderByVersionOrderDesc(codeSystemOid.trim());
            // Get the latest version
            CodeSystemVersion codeSystemVersion = codeSystemVersions.get(0);

            // 2.Get the concept code for the given code and the latest code system version
            CodedConcept codedConcept = codedConceptRepository
                    .findByCodeSystemVersionIdAndCodeNameCode(codeSystemVersion.getId(),
                            codeConceptCode.trim());

            // 3.Get the value sets associated to the concept code
            valueSetRepository
                    .findAllByCodedConceptsId(codedConcept.getId())
                    .forEach(
                            valueSet -> valueSetCategoryCodes.add(valueSet.getValueSetCategory().getCodeName().getCode())
                    );
        } catch (Exception e) {
            logger.error(() -> "ValueSetCategories search failed: " + e.getMessage());
            logger.debug(e::getMessage, e);
            throw new ValueSetCategoriesSearchFailedException();
        }
        return new ValueSetCategoryLookupDto(valueSetCategoryCodes);
    }
}