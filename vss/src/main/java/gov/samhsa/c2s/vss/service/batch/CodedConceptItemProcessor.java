package gov.samhsa.c2s.vss.service.batch;

import gov.samhsa.c2s.vss.domain.CodedConcept;
import gov.samhsa.c2s.vss.service.CodedConceptService;
import gov.samhsa.c2s.vss.service.dto.CodedConceptUploadDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CodedConceptItemProcessor implements ItemProcessor<CodedConceptUploadDto, CodedConcept> {

    private final CodedConceptService codedConceptService;

    public CodedConceptItemProcessor(CodedConceptService codedConceptService) {
        this.codedConceptService = codedConceptService;
    }

    @Override
    public CodedConcept process(CodedConceptUploadDto codedConceptUploadDto) throws Exception {
        return codedConceptService.getCodedConcept(codedConceptUploadDto);

    }


}
