package gov.samhsa.c2s.vss.service.batch;

import gov.samhsa.c2s.vss.service.dto.UploadRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

@Service("codedConceptJobManager")
@Data
@Slf4j
public class CodedConceptBatchJobManagerServiceImpl implements BatchJobManagerService {
    private final JobLauncher jobLauncher;

    @Autowired
    @Resource(name="codedConceptJob")
    private Job job;

    public CodedConceptBatchJobManagerServiceImpl(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }


    @Override
    public void batchUpload(UploadRequestDto uploadRequestDto) throws Exception {

        try {
            final JobParameters jobParameters =
                    new JobParametersBuilder()
                            .addLong("time",System.currentTimeMillis())
                            .addString("codedConceptCsvdata", new String(uploadRequestDto.getDocument()))
                            .toJobParameters();
            log.debug("CodedConcept Upload - Started" + jobParameters);
            final JobExecution execution = jobLauncher.run(job, jobParameters);

            log.debug("Coded Concept Upload - Complete  Job Status: " + execution.getStatus());
        }  catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException |
                JobParametersInvalidException | JobRestartException e) {
            log.error("Failed to do Coded Concept Upload Job Status:" + e.getMessage());

        }

    }

}
