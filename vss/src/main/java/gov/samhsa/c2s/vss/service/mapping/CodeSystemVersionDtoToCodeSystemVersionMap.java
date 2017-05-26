package gov.samhsa.c2s.vss.service.mapping;

import gov.samhsa.c2s.vss.domain.CodeSystem;
import gov.samhsa.c2s.vss.domain.CodeSystemRepository;
import gov.samhsa.c2s.vss.domain.CodeSystemVersion;
import gov.samhsa.c2s.vss.service.dto.CodeSystemVersionDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CodeSystemVersionDtoToCodeSystemVersionMap extends PropertyMap<CodeSystemVersionDto, CodeSystemVersion> {
    private final CodeSystemConverter codeSystemConverter;

    public CodeSystemVersionDtoToCodeSystemVersionMap(CodeSystemConverter codeSystemConverter) {
        this.codeSystemConverter = codeSystemConverter;
    }

    @Override
    protected void configure() {
        using(codeSystemConverter).map(source).setCodeSystem(null);
    }


    @Component
    private static class CodeSystemConverter extends AbstractConverter<CodeSystemVersionDto, CodeSystem> {
        private final CodeSystemRepository codeSystemRepository;

        private CodeSystemConverter(CodeSystemRepository codeSystemRepository) {
            this.codeSystemRepository = codeSystemRepository;
        }

        @Override
        protected CodeSystem convert(CodeSystemVersionDto source) {
            return codeSystemRepository.findByCodeName_Code(source.getCodeSystemCodeNameCode()).get();
        }
    }
}
