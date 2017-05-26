package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.domain.CodeSystem;
import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import gov.samhsa.c2s.vss.service.dto.CodeSystemDto;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CodeSystemDtoToCodeSystemMap extends PropertyMap<CodeSystemDto, CodeSystem> {
    @Override
    protected void configure() {
        map().getCodeName().setName(source.getName());
        map().getCodeName().setCode(source.getCode());
     }
}
