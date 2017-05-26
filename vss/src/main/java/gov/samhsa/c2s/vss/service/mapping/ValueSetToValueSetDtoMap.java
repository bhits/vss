package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.domain.ValueSetCategory;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetDto;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ValueSetToValueSetDtoMap extends PropertyMap<ValueSet, ValueSetDto> {

    @Override
    protected void configure() {
        map().setCode(source.getCodeName().getCode());
        map().setName(source.getCodeName().getName());
        map().setId(source.getId());
        map().setValueSetCatCode(source.getValueSetCategory().getCodeName().getCode());

    }

}
