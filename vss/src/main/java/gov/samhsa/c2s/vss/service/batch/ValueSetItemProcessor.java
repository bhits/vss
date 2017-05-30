package gov.samhsa.c2s.vss.service.batch;

import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import gov.samhsa.c2s.vss.service.ValueSetService;
import gov.samhsa.c2s.vss.service.dto.ValueSetUploadDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValueSetItemProcessor implements ItemProcessor<ValueSetUploadDto, ValueSet>{

    private final ValueSetService valueSetService;

    public ValueSetItemProcessor(ValueSetService valueSetService) {
        this.valueSetService = valueSetService;
    }


    @Override
    public ValueSet process(ValueSetUploadDto valueSetUploadDto) throws Exception {
 /*       ValueSet valueSet = new ValueSet();
        valueSet.setCodeName(new CodeName(valueSetUploadDto.getCode(), valueSetUploadDto.getName()));
        valueSet.setDescription(valueSetUploadDto.getDescription());
        valueSet.setValueSetCategory(valueSetCategoryRepository.findById(Long.parseLong(valueSetUploadDto
                .getValuesetCatId())).get());*/
        return valueSetService.getValueSet(valueSetUploadDto);
    }
}
