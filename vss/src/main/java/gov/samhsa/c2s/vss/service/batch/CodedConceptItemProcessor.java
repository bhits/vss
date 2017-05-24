package gov.samhsa.c2s.vss.service.batch;

import gov.samhsa.c2s.vss.domain.CodeSystemVersionRepository;
import gov.samhsa.c2s.vss.domain.CodedConcept;
import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.domain.ValueSetCategoryRepository;
import gov.samhsa.c2s.vss.domain.valueobject.CodeName;
import gov.samhsa.c2s.vss.service.dto.ValueSetUploadDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodedConceptItemProcessor implements ItemProcessor<ValueSetUploadDto, CodedConcept>{
    @Autowired
    private CodeSystemVersionRepository codeSystemVersionRepository;

    @Override
    public CodedConcept process(ValueSetUploadDto valueSetUploadDto) throws Exception {
        CodedConcept codedConcept = new CodedConcept();
        codedConcept.setCodeName(new CodeName(valueSetUploadDto.getCode(), valueSetUploadDto.getName()));
        codedConcept.setDescription(valueSetUploadDto.getDescription());
/*        codedConcept.setCodeSystemVersion(codeSystemVersionRepository.findByCodeName_Code(valueSetUploadDto
                .getValueSetCatCode()).get());*/
        return codedConcept;
    }
}
