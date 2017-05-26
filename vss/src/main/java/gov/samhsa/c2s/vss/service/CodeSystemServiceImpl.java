package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.domain.CodeSystem;
import gov.samhsa.c2s.vss.domain.CodeSystemRepository;
import gov.samhsa.c2s.vss.service.dto.CodeSystemDto;
import gov.samhsa.c2s.vss.service.exception.CodeSystemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CodeSystemServiceImpl implements CodeSystemService {

    private final CodeSystemRepository codeSystemRepository;

    private final ModelMapper modelMapper;

    public CodeSystemServiceImpl(CodeSystemRepository codeSystemRepository, ModelMapper modelMapper) {
        this.codeSystemRepository = codeSystemRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CodeSystemDto createCodeSystem(CodeSystemDto codeSystemDto) {

        CodeSystem codeSystem = codeSystemRepository.save(modelMapper.map(codeSystemDto, CodeSystem.class));
        CodeSystemDto created = modelMapper.map(codeSystem, CodeSystemDto.class);
        log.debug("Code System Created : " + created);
        return created;
    }

    @Override
    public CodeSystemDto deleteCodeSystem(Long codeSystemId) throws CodeSystemNotFoundException {
        return null;
    }

    @Override
    public List<CodeSystemDto> findAll() {
        return null;
    }

    @Override
    public CodeSystemDto findById(Long id) {
        return null;
    }

    @Override
    public CodeSystemDto updateCodeSystem(CodeSystemDto updated) throws CodeSystemNotFoundException {
        return null;
    }
}
