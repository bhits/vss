package gov.samhsa.c2s.vss.service.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DuplicateRecordSkipListener implements SkipPolicy{


    @Override
    public boolean shouldSkip(Throwable throwable, int i) throws SkipLimitExceededException {
        if(throwable instanceof DataIntegrityViolationException){
            DataIntegrityViolationException dive = (DataIntegrityViolationException) throwable;
            log.error("An Error Occured while processing the data " +  dive.getMessage());
            return true;
        } else
            return false;
    }
}
