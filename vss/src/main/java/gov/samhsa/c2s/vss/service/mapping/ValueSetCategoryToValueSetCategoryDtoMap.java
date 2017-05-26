package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.domain.ValueSetCategory;
import gov.samhsa.c2s.vss.domain.ValueSetCategorySystem;
import gov.samhsa.c2s.vss.domain.ValueSetCategorySystemRepository;
import gov.samhsa.c2s.vss.service.dto.ValueSetCategoryDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ValueSetCategoryToValueSetCategoryDtoMap extends PropertyMap<ValueSetCategory, ValueSetCategoryDto> {

    @Override
    protected void configure() {
        map().setCode(source.getCodeName().getCode());
        map().setName(source.getCodeName().getName());

    }

}
