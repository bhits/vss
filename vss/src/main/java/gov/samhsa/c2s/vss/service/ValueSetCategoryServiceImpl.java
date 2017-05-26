package gov.samhsa.c2s.vss.service;

import gov.samhsa.c2s.vss.domain.ValueSetCategory;
import gov.samhsa.c2s.vss.domain.ValueSetCategoryRepository;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
import gov.samhsa.c2s.vss.service.exception.ValueSetCategoryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ValueSetCategoryServiceImpl implements ValueSetCategoryService{

    private final ValueSetCategoryRepository valueSetCategoryRepository;
    private final ModelMapper modelMapper;

    public ValueSetCategoryServiceImpl(ValueSetCategoryRepository valueSetCategoryRepository, ModelMapper modelMapper) {
        this.valueSetCategoryRepository = valueSetCategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ValueSetCategoryDto createValueSetCategory(ValueSetCategoryDto valueSetCategoryDto) {
        ValueSetCategory valueSetCategory = valueSetCategoryRepository.save(modelMapper.map(valueSetCategoryDto,
                ValueSetCategory.class));
        ValueSetCategoryDto created = modelMapper.map(valueSetCategory, ValueSetCategoryDto.class);
        log.debug("ValueSet Category Created " + created);
        return created;
    }

    @Override
    public ValueSetCategoryDto deleteValueSetCategory(Long ValueSetCategoryId) throws ValueSetCategoryNotFoundException {
        return null;
    }

    @Override
    public List<ValueSetCategoryDto> findAll() {
        return null;
    }

    @Override
    public ValueSetCategoryDto findById(Long id) {
        return null;
    }


    @Override
    public ValueSetCategoryDto findByCode(String code) throws ValueSetCategoryNotFoundException{
        ValueSetCategoryDto valueSetCategoryDto;

        Optional<ValueSetCategory> valueSetCategoryOptional = valueSetCategoryRepository.findByCodeName_Code(code);
        if(valueSetCategoryOptional.isPresent()){
            return modelMapper.map(valueSetCategoryOptional.get() , ValueSetCategoryDto.class);
        } else {
            throw new ValueSetCategoryNotFoundException();
        }
    }

    @Override
    public ValueSetCategoryDto updateValueSetCategory(ValueSetCategoryDto updated) throws ValueSetCategoryNotFoundException {
        return null;
    }

}
