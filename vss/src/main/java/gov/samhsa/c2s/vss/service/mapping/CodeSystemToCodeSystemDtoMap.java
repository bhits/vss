package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.domain.CodeSystem;
import gov.samhsa.c2s.vss.service.dto.CodeSystemDto;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CodeSystemToCodeSystemDtoMap extends PropertyMap<CodeSystem, CodeSystemDto> {
    @Override
    protected void configure() {
        map().setCodeSystemOid(source.getCodeSystemOid());
        map().setCode(source.getCodeName().getCode());
        map().setName(source.getCodeName().getName());
        map().setDisplayName(source.getDisplayName());
        map().setId(source.getId());
    }
}
