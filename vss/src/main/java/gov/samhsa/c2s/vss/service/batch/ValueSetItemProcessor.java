package gov.samhsa.c2s.vss.service.batch;

import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.domain.ValueSetCategoryRepository;
import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import gov.samhsa.c2s.vss.service.ValueSetCategoryService;
import gov.samhsa.c2s.vss.service.ValueSetCategoryServiceImpl;
import gov.samhsa.c2s.vss.service.dto.ValueSetUploadDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValueSetItemProcessor implements ItemProcessor<ValueSetUploadDto, ValueSet>{
    @Autowired
    private ValueSetCategoryRepository valueSetCategoryRepository;

    @Override
    public ValueSet process(ValueSetUploadDto valueSetUploadDto) throws Exception {
        ValueSet valueSet = new ValueSet();
        valueSet.setCodeName(new CodeName(valueSetUploadDto.getCode(), valueSetUploadDto.getName()));
        valueSet.setDescription(valueSetUploadDto.getDescription());
        valueSet.setValueSetCategory(valueSetCategoryRepository.findByCodeName_Code(valueSetUploadDto
                .getValueSetCatCode()).get());
        return valueSet;
    }
}
