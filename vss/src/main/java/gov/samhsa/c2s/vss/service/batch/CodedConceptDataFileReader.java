package gov.samhsa.c2s.vss.service.batch;

import gov.samhsa.c2s.vss.service.dto.CodedConceptUploadDto;
import gov.samhsa.c2s.vss.service.dto.UploadResponseDto;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;

@NoArgsConstructor
@StepScope
public class CodedConceptDataFileReader extends FlatFileItemReader<CodedConceptUploadDto> {

    @Value("#{jobParameters['codedConceptCsvdata']}")
    public void setCodedConceptCsvdata(String codedConceptCsvdata) {
        if(null != codedConceptCsvdata) {
            ByteArrayInputStream bais = new ByteArrayInputStream(codedConceptCsvdata.getBytes());
            InputStreamResource resource = new InputStreamResource(bais);
            setResource(resource);
        }
    }
}