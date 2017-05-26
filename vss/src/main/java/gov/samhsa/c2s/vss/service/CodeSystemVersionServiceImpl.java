package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.domain.CodeSystem;
import gov.samhsa.c2s.vss.domain.CodeSystemRepository;
import gov.samhsa.c2s.vss.domain.CodeSystemVersion;
import gov.samhsa.c2s.vss.domain.CodeSystemVersionRepository;
import gov.samhsa.c2s.vss.service.dto.CodeSystemDto;
import gov.samhsa.c2s.vss.service.dto.CodeSystemVersionDto;
import gov.samhsa.c2s.vss.service.exception.CodeSystemVersionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CodeSystemVersionServiceImpl implements CodeSystemVersionService {

    private final CodeSystemVersionRepository codeSystemVersionRepository;
    private final CodeSystemRepository codeSystemRepository;
    private final ModelMapper modelMapper;

    public CodeSystemVersionServiceImpl(CodeSystemVersionRepository codeSystemVersionRepository, CodeSystemRepository
            codeSystemRepository, ModelMapper modelMapper) {
        this.codeSystemVersionRepository = codeSystemVersionRepository;
        this.codeSystemRepository = codeSystemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CodeSystemVersionDto createCodeSystemVersion(CodeSystemVersionDto codeSystemVersionDto) {

        CodeSystemVersion codeSystemVersion = codeSystemVersionRepository.save(modelMapper.map(codeSystemVersionDto,
                CodeSystemVersion.class));
        CodeSystemVersionDto created = modelMapper.map(codeSystemVersion, CodeSystemVersionDto.class);
        log.debug("Code System Version Created" + created);
        return created;
    }

    @Override
    public CodeSystemVersionDto deleteCodeSystemVersion(Long CodeSystemVersionId) throws CodeSystemVersionNotFoundException {
        return null;
    }

    @Override
    public List<CodeSystemVersionDto> findAll() {
        return null;
    }

    @Override
    public CodeSystemVersionDto findById(Long id) {
        return null;
    }

    @Override
    public CodeSystemVersionDto updateCodeSystemVersion(CodeSystemVersionDto updated) throws CodeSystemVersionNotFoundException {
        return null;
    }

}
