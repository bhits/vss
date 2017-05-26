package gov.samhsa.c2s.vss.service.batch;

import gov.samhsa.c2s.vss.service.batch.BatchJobManagerService;
import gov.samhsa.c2s.vss.service.dto.UploadRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("valueSetJobManager")
@Slf4j
public class ValueSetBatchJobManagerServiceImpl implements BatchJobManagerService {
    private final JobLauncher jobLauncher;

    @Autowired
    @Resource(name="valueSetJob")
    private Job job;

    public ValueSetBatchJobManagerServiceImpl(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    @Override
    public void batchUpload(UploadRequestDto uploadRequestDto) throws Exception {

        try {
            final JobParameters jobParameters =
                    new JobParametersBuilder()
                            .addLong("time",System.currentTimeMillis())
                            .addString("valueSetCsvdata", new String(uploadRequestDto.getDocument()))
                            .toJobParameters();
            log.debug("ValueSet Upload - Started" + jobParameters);
            final JobExecution execution = jobLauncher.run(job, jobParameters);

            log.debug("ValueSet Upload - Complete  Job Status: " + execution.getStatus());
        }  catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException |
                JobParametersInvalidException | JobRestartException e) {
            log.error("Failed to do Valueset Upload Job Status:" + e.getMessage());

        }

    }

}
