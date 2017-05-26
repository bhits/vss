package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.domain.CodedConcept;
import gov.samhsa.c2s.vss.domain.CodedConceptRepository;
import gov.samhsa.c2s.vss.service.dto.CodedConceptDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CodedConceptServiceImpl implements CodedConceptService{
    private final CodedConceptRepository codedConceptRepository;
    private final ModelMapper modelMapper;

    public CodedConceptServiceImpl(CodedConceptRepository codedConceptRepository, ModelMapper modelMapper) {
        this.codedConceptRepository = codedConceptRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CodedConceptDto createCodedConcept(CodedConceptDto codedConceptDto) {
        CodedConcept valueSetCategory = codedConceptRepository.save(modelMapper.map(codedConceptDto,
                CodedConcept.class));
        CodedConceptDto created = modelMapper.map(valueSetCategory, CodedConceptDto.class);
        log.debug("ValueSet Category Created " + created);
        return created;
    }
}
