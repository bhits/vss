package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.service.dto.ValueSetDto;
import gov.samhsa.c2s.vss.service.dto.ValueSetUploadDto;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ValueSetUploadToValueSetDtoMap extends PropertyMap<ValueSetUploadDto, ValueSetDto> {

    @Override
    protected void configure() {
        map().setValueSetCatCode(source.getValueSetCatCode());

    }

}
