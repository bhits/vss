package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.domain.ValueSetRepository;
import gov.samhsa.c2s.vss.service.dto.ValueSetDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetUploadDto;
import gov.samhsa.c2s.vss.service.exception.ValueSetCategoryNotFoundException;
import gov.samhsa.c2s.vss.service.exception.ValueSetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ValueSetServiceImpl implements ValueSetService {

    private final ValueSetRepository valueSetRepository;
    private final ModelMapper modelMapper;


    public ValueSetServiceImpl(ValueSetRepository valueSetRepository, ModelMapper modelMapper) {
        this.valueSetRepository = valueSetRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ValueSetDto createValueSet(ValueSetDto valueSetDto) throws ValueSetCategoryNotFoundException {
        ValueSet valueSet = valueSetRepository.save(modelMapper.map(valueSetDto,
                ValueSet.class));
        ValueSetDto created = modelMapper.map(valueSet, ValueSetDto.class);
        log.debug("ValueSet Created " + created);
        return created;
    }

    @Override
    public ValueSet getValueSet(ValueSetUploadDto valueSetUploadDto) {
        ValueSetDto valueSetDto = modelMapper.map(valueSetUploadDto, ValueSetDto.class);
        return modelMapper.map(valueSetDto, ValueSet.class);
    }

    @Override
    public ValueSetDto deleteValueSet(Long valueSetId) throws ValueSetNotFoundException {
        return null;
    }

    @Override
    public List<ValueSetDto> findAll() {
        return null;
    }

    @Override
    public Map<String, Object> findAll(int pageNumber) {
        return null;
    }

    @Override
    public ValueSetDto findById(Long id) throws ValueSetNotFoundException {
        return null;
    }

    @Override
    public ValueSetDto updateValueSet(ValueSetDto updated) throws ValueSetNotFoundException, ValueSetNotFoundException {
        return null;
    }

    @Override
    public Map<String, Object> findAllByName(String searchTerm, String ValueSet, int pageNumber) {
        return null;
    }

    @Override
    public Map<String, Object> findAllByCode(String searchTerm, String ValueSet, int pageNumber) {
        return null;
    }

    @Override
    public ValueSetDto valueSetBatchUpload(ValueSetDto valueSetDto, MultipartFile file) throws Exception {
        return null;
    }

    @Override
    public List<ValueSetDto> findAllWithoutDeletable() {
        return null;
    }
}
