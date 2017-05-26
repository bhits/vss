package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.domain.CodeSystem;
import gov.samhsa.c2s.vss.domain.CodedConcept;
import gov.samhsa.c2s.vss.service.dto.CodeSystemDto;
import gov.samhsa.c2s.vss.service.dto.CodedConceptDto;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CodedConceptToCodedConceptDtoMap extends PropertyMap<CodedConcept, CodedConceptDto> {
    @Override
    protected void configure() {
        map().setName(source.getCodeName().getName());
        map().setCode(source.getCodeName().getCode());
        map().setId(source.getId());
        map().setCodeSystemVersionId(source.getCodeSystemVersion().getId());
    }
}
