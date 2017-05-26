package gov.samhsa.c2s.vss.service.batch;

import gov.samhsa.c2s.vss.service.dto.ValueSetUploadDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@NoArgsConstructor
@StepScope
public class DataFileReader extends FlatFileItemReader<ValueSetUploadDto> {

    @Value("#{jobParameters['valueSetCsvdata']}")
    public void setValueSetCsvdata(String valueSetCsvdata) {
        if(null != valueSetCsvdata) {
            ByteArrayInputStream bais = new ByteArrayInputStream(valueSetCsvdata.getBytes());
            InputStreamResource resource = new InputStreamResource(bais);
            setResource(resource);
        }
    }

    @Value("#{jobParameters['codedConceptCsvdata']}")
    public void setCodedConceptCsvdata(String codedConceptCsvdata) {
        if(null != codedConceptCsvdata) {
            ByteArrayInputStream bais = new ByteArrayInputStream(codedConceptCsvdata.getBytes());
            InputStreamResource resource = new InputStreamResource(bais);
            setResource(resource);
        }
    }
}