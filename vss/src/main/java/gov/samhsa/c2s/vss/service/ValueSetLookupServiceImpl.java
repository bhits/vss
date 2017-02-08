package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.common.log.Logger;
import gov.samhsa.c2s.common.log.LoggerFactory;
import gov.samhsa.c2s.vss.domain.*;
import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import gov.samhsa.c2s.vss.service.dto.CodedConceptAndCodeSystemOidDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryMapDto;
import gov.samhsa.c2s.vss.service.exception.SensitivityPolicySearchFailedException;
import gov.samhsa.c2s.vss.service.exception.ValueSetCategoriesSearchFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class ValueSetLookupServiceImpl implements ValueSetLookupService {
    private Logger logger = LoggerFactory.getLogger(this);

    private ValueSetCategoryRepository valueSetCategoryRepository;

    private ValueSetRepository valueSetRepository;

    private CodedConceptRepository codedConceptRepository;

    private CodeSystemVersionRepository codeSystemVersionRepository;

    @Autowired
    public ValueSetLookupServiceImpl(ValueSetCategoryRepository valueSetCategoryRepository,
                                     ValueSetRepository valueSetRepository,
                                     CodedConceptRepository codedConceptRepository,
                                     CodeSystemVersionRepository codeSystemVersionRepository) {
        this.valueSetCategoryRepository = valueSetCategoryRepository;
        this.valueSetRepository = valueSetRepository;
        this.codedConceptRepository = codedConceptRepository;
        this.codeSystemVersionRepository = codeSystemVersionRepository;
    }

    @Override
    public List<ValueSetCategoryDto> lookupSensitivityPolicies() {
        List<ValueSetCategoryDto> sensitivityPolicyDto = new ArrayList<>();
        try {
            valueSetCategoryRepository.findAll()
                    .forEach(
                            valueSetCategory -> {
                                ValueSetCategoryDto sensitivityPolicyDtoItem =
                                        new ValueSetCategoryDto(valueSetCategory.getCodeName().getCode(),
                                                valueSetCategory.getCodeName().getName(),
                                                valueSetCategory.getDescription(),
                                                valueSetCategory.isFederal(),
                                                valueSetCategory.getDisplayOrder());
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
    public List<ValueSetCategoryMapDto> lookupValueSetCategories(List<CodedConceptAndCodeSystemOidDto> codedConceptAndCodeSystemOidDtos) {
        List<ValueSetCategoryMapDto> valueSetCategoryMapDtos;
        try {
            valueSetCategoryMapDtos = codedConceptAndCodeSystemOidDtos.stream()
                    .map(dto -> getValueSetCategoryMapDto(dto.getCodeConceptCode().trim(), dto.getCodeSystemOid().trim()))
                    .collect(toList());
        } catch (Exception e) {
            logger.error(() -> "ValueSetCategories search failed: " + e.getMessage());
            logger.debug(e::getMessage, e);
            throw new ValueSetCategoriesSearchFailedException();
        }
        return valueSetCategoryMapDtos;
    }

    private ValueSetCategoryMapDto getValueSetCategoryMapDto(String codeConceptCode, String codeSystemOid) {
        // 1.Get latest version of Code System version for the given code system oid
        Set<String> valueSetCategoryCodes = codeSystemVersionRepository
                .findTopByCodeSystemCodeSystemOidOrderByVersionOrderDesc(codeSystemOid).map(Stream::of).orElse(Stream.empty())
                .peek(codeSystemVersion -> logger.debug("The latest version of Code System version: " + codeSystemVersion.getVersionName()))
                // 2.Get the coded concept for the given code and the latest code system version
                .flatMap(codeSystemVersion -> codedConceptRepository.findByCodeSystemVersionIdAndCodeNameCode(codeSystemVersion.getId(), codeConceptCode).map(Stream::of).orElse(Stream.empty()))
                .peek(codedConcept -> logger.debug("The coded concept name: " + codedConcept.getCodeName().getName()))
                // 3.Get the value sets associated to the coded concept
                .flatMap(codedConcept -> valueSetRepository.findAllByCodedConceptsId(codedConcept.getId()).stream()).map(ValueSet::getValueSetCategory).map(ValueSetCategory::getCodeName).map(CodeName::getCode)
                .collect(toSet());
        return new ValueSetCategoryMapDto(codeConceptCode, codeSystemOid, valueSetCategoryCodes);
    }
}