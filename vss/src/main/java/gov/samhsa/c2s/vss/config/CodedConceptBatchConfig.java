package gov.samhsa.c2s.vss.config;

import gov.samhsa.c2s.vss.domain.CodedConcept;
import gov.samhsa.c2s.vss.domain.CodedConceptRepository;
import gov.samhsa.c2s.vss.service.batch.CodedConceptDataFileReader;
import gov.samhsa.c2s.vss.service.batch.CodedConceptItemProcessor;
import gov.samhsa.c2s.vss.service.batch.DuplicateRecordSkipListener;
import gov.samhsa.c2s.vss.service.dto.CodedConceptUploadDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;

@Configuration
public class CodedConceptBatchConfig {

    private final JobBuilderFactory jobs;

    private final StepBuilderFactory steps;

    private final CodedConceptRepository codedConceptRepository;

    private final CodedConceptItemProcessor codedConceptItemProcessor;


    @Autowired
    public CodedConceptBatchConfig(JobBuilderFactory jobs, StepBuilderFactory steps, CodedConceptRepository
            codedConceptRepository, CodedConceptItemProcessor

            codedConceptItemProcessor) {
        this.jobs = jobs;
        this.steps = steps;
        this.codedConceptRepository = codedConceptRepository;
        this.codedConceptItemProcessor = codedConceptItemProcessor;
    }

    // tag::jobstep[]
    @Bean(name = "codedConceptJob")
    public Job CodedConceptJob() {
        return jobs.get("codedConceptJob")
                .incrementer(new RunIdIncrementer())
                .flow(CodedConceptStep())
                .end()
                .build();
    }


    @Bean
    public Step CodedConceptStep() {

        return steps.get("CodedConceptStep")
                .<CodedConceptUploadDto, CodedConcept>chunk(10)
                .reader(csvCodedConceptReader())
                .processor(codedConceptItemProcessor)
                .writer(codedConceptWriter())
                .faultTolerant()
                .skipPolicy(new DuplicateRecordSkipListener())
                .build();

    }
    // end::jobstep[]

    // tag::readerwriterprocessor[]

    @Bean
    @StepScope
    FlatFileItemReader<CodedConceptUploadDto> csvCodedConceptReader() {

        //To read lines from an input file
        FlatFileItemReader<CodedConceptUploadDto> csvFilereader = new CodedConceptDataFileReader();

        csvFilereader.setResource(new InputStreamResource(new ByteArrayInputStream(new byte[0])));
        // skip over headers
        csvFilereader.setLinesToSkip(1);  // header line
        // To transform lines into objects by using a LineMapper
        // Maps a line in the file to an object
        csvFilereader.setLineMapper(codedConeptUploadDtoLineMapper());
        return csvFilereader;

    }


    private LineMapper<CodedConceptUploadDto> codedConeptUploadDtoLineMapper() {
        DefaultLineMapper<CodedConceptUploadDto> codedConceptLineMapper = new DefaultLineMapper<>();
        // TODO:: need to generalize
        codedConceptLineMapper.setLineTokenizer(createcodedConceptLineTokenizer());
        codedConceptLineMapper.setFieldSetMapper(codedConceptFieldSetMapper());
        return codedConceptLineMapper;
    }


    private LineTokenizer createcodedConceptLineTokenizer() {
        DelimitedLineTokenizer codedConceptLineTokenizer = new DelimitedLineTokenizer();
        codedConceptLineTokenizer.setDelimiter(",");
        codedConceptLineTokenizer.setNames(new String[]{"code", "name", "description","codeSystemId",
                "codeSystemVersionId" ,"uploadValueSets"});
        return codedConceptLineTokenizer;
    }

    private FieldSetMapper<CodedConceptUploadDto> codedConceptFieldSetMapper() {
        BeanWrapperFieldSetMapper<CodedConceptUploadDto> codedConceptFieldMapper = new BeanWrapperFieldSetMapper<>();
        codedConceptFieldMapper.setTargetType(CodedConceptUploadDto.class);
        return codedConceptFieldMapper;
    }

    @Bean
    ItemWriter<CodedConcept> codedConceptWriter() {
        RepositoryItemWriter codedConceptWriter = new RepositoryItemWriter();
        codedConceptWriter.setRepository(codedConceptRepository);
        codedConceptWriter.setMethodName("save");
        return codedConceptWriter;

    }
    // end::readerwriterprocessor[]

}


