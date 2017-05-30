package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.domain.CodeSystemVersion;
import gov.samhsa.c2s.vss.domain.CodeSystemVersionRepository;
import gov.samhsa.c2s.vss.domain.CodedConcept;
import gov.samhsa.c2s.vss.service.dto.CodedConceptDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CodedConceptDtoToCodedConceptMap extends PropertyMap<CodedConceptDto, CodedConcept> {
    private final CodeSystemVersionConverter CodeSystemVersionConverter;

    public CodedConceptDtoToCodedConceptMap(CodeSystemVersionConverter CodeSystemVersionConverter) {
        this.CodeSystemVersionConverter = CodeSystemVersionConverter;
    }

    @Override
    protected void configure() {
        using(CodeSystemVersionConverter).map(source).setCodeSystemVersion(null);
        map().getCodeName().setName(source.getName());
        map().getCodeName().setCode(source.getCode());
        map().setId(source.getId());
    }


    @Component
    private static class CodeSystemVersionConverter extends AbstractConverter<CodedConceptDto, CodeSystemVersion> {
        private final CodeSystemVersionRepository CodeSystemVersionRepository;

        private CodeSystemVersionConverter(CodeSystemVersionRepository CodeSystemVersionRepository) {
            this.CodeSystemVersionRepository = CodeSystemVersionRepository;
        }

        @Override
        protected CodeSystemVersion convert(CodedConceptDto source) {
            return CodeSystemVersionRepository.findOneById(source.getCodeSystemVersionId()).get();
        }
    }



}
