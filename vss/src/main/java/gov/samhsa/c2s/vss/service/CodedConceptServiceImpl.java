package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.domain.CodeSystemVersion;
import gov.samhsa.c2s.vss.domain.CodeSystemVersionRepository;
import gov.samhsa.c2s.vss.domain.CodedConcept;
import gov.samhsa.c2s.vss.domain.CodedConceptRepository;
import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.domain.ValueSetRepository;
import gov.samhsa.c2s.vss.service.dto.CodedConceptDto;
import gov.samhsa.c2s.vss.service.dto.CodedConceptUploadDto;
import gov.samhsa.c2s.vss.service.exception.CodeSystemVersionNotFoundException;
import gov.samhsa.c2s.vss.service.exception.ValueSetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CodedConceptServiceImpl implements CodedConceptService{

    private final CodedConceptRepository codedConceptRepository;
    private final CodeSystemVersionRepository codeSystemVersionRepository;
    private final ValueSetRepository valueSetRepository;


    private final ModelMapper modelMapper;

    public CodedConceptServiceImpl(CodedConceptRepository codedConceptRepository, CodeSystemVersionRepository
            codeSystemVersionRepository, ValueSetRepository valueSetRepository, ModelMapper modelMapper) {
        this.codedConceptRepository = codedConceptRepository;
        this.valueSetRepository = valueSetRepository;
        this.modelMapper = modelMapper;
        this.codeSystemVersionRepository = codeSystemVersionRepository;
    }


    @Override
    public CodedConceptDto createCodedConcept(CodedConceptDto codedConceptDto) {

        CodedConcept codedConcept = convertCodedConcept(codedConceptDto);

        CodedConceptDto created = modelMapper.map(codedConcept, CodedConceptDto.class);
        return created;
    }

    @Override
    public CodedConcept getCodedConcept(CodedConceptUploadDto codedConceptUploadDto) {
        CodedConceptDto codedConceptDto = modelMapper.map(codedConceptUploadDto, CodedConceptDto.class);
        return convertCodedConcept(codedConceptDto);
    }

    private CodedConcept convertCodedConcept(CodedConceptDto codedConceptDto){
        // STEP:1 :- Get CodeSystemVersion Object from DB
        CodeSystemVersion selectedCodeSystemVersion = codeSystemVersionRepository.findOneById(codedConceptDto
                .getCodeSystemVersionId()).orElseThrow(CodeSystemVersionNotFoundException::new);
        Optional<CodedConcept> optCodedConcept = codedConceptRepository.findByCodeSystemVersion_IdAndCodeName_Code
                (selectedCodeSystemVersion.getId(), codedConceptDto.getCode());

        // STEP:2 :- Get ValueSet Objects from DB
         List<ValueSet> valueSets = codedConceptDto.getValueSetIds().stream().map(toValueSet()).collect(Collectors.toList());
/*        ValueSet valueSet = valueSetRepository.findById(Long.parseLong(codedConceptDto.getValueSetId())).orElseThrow
                (ValueSetNotFoundException::new);*/
        CodedConcept codedConcept = null;

        if (optCodedConcept.isPresent()) {
            codedConcept = optCodedConcept.get();
            // check if association between code and valueset exists


        } else {
            // New Concept Code
            codedConcept = modelMapper.map(codedConceptDto, CodedConcept.class);
            // set value sets relation
            codedConcept.setValueSets(valueSets);
        }
        return codedConcept;
    }



    private Function<Long, ValueSet> toValueSet(){

        return id -> valueSetRepository.findById(id).orElseThrow(ValueSetNotFoundException::new);

    }
}
