package gov.samhsa.c2s.vss.config;

import gov.samhsa.c2s.vss.domain.ValueSet;
import gov.samhsa.c2s.vss.domain.ValueSetRepository;
import gov.samhsa.c2s.vss.service.batch.DuplicateRecordSkipListener;
import gov.samhsa.c2s.vss.service.batch.ValueSetItemProcessor;
import gov.samhsa.c2s.vss.service.batch.DataFileReader;
import gov.samhsa.c2s.vss.service.dto.ValueSetUploadDto;
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
public class ValuesetBatchConfig {

    private final JobBuilderFactory jobs;

    private final StepBuilderFactory steps;

    private final ValueSetRepository valueSetRepository;

    private final ValueSetItemProcessor valueSetItemProcessor;


    @Autowired
    public ValuesetBatchConfig(JobBuilderFactory jobs, StepBuilderFactory steps, ValueSetRepository valueSetRepository,
                               ValueSetItemProcessor valueSetItemProcessor) {
        this.jobs = jobs;
        this.steps = steps;
        this.valueSetRepository = valueSetRepository;
        this.valueSetItemProcessor = valueSetItemProcessor;
    }

    // tag::jobstep[]
    @Bean(name = "valueSetJob")
    public Job valueSetJob() {
        return jobs.get("valueSetJob")
                .incrementer(new RunIdIncrementer())
                .flow(valueSetStep())
                .end()
                .build();
    }


    @Bean
    public Step valueSetStep() {

        return steps.get("valueSetStep")
                .<ValueSetUploadDto, ValueSet>chunk(10)
                .reader(csvValueSetReader())
                .processor(valueSetItemProcessor)
                .writer(valueSetWriter())
                .faultTolerant()
                .skipPolicy(new DuplicateRecordSkipListener())
                .build();

    }
    // end::jobstep[]

    // tag::readerwriterprocessor[]

    @Bean
    @StepScope
    FlatFileItemReader<ValueSetUploadDto> csvValueSetReader() {

        //To read lines from an input file
        FlatFileItemReader<ValueSetUploadDto> csvFilereader = new DataFileReader();

        csvFilereader.setResource(new InputStreamResource(new ByteArrayInputStream(new byte[0])));
        // skip over headers
        csvFilereader.setLinesToSkip(1);  // header line
        // To transform lines into objects by using a LineMapper
        // Maps a line in the file to an object
        csvFilereader.setLineMapper(valueSetUploadDtoLineMapper());
        return csvFilereader;

    }


    private LineMapper<ValueSetUploadDto> valueSetUploadDtoLineMapper() {
        DefaultLineMapper<ValueSetUploadDto> valueSetLineMapper = new DefaultLineMapper<>();
        // TODO:: need to generalize
        valueSetLineMapper.setLineTokenizer(createValueSetLineTokenizer());
        valueSetLineMapper.setFieldSetMapper(valueSetFieldSetMapper());
        return valueSetLineMapper;
    }


    private LineTokenizer createValueSetLineTokenizer() {
        DelimitedLineTokenizer valueSetLineTokenizer = new DelimitedLineTokenizer();
        valueSetLineTokenizer.setDelimiter(",");
        valueSetLineTokenizer.setNames(new String[]{"code", "name", "description", "valueSetCatId"});
        return valueSetLineTokenizer;
    }

    private FieldSetMapper<ValueSetUploadDto> valueSetFieldSetMapper() {
        BeanWrapperFieldSetMapper<ValueSetUploadDto> valueSetFieldMapper = new BeanWrapperFieldSetMapper<>();
        valueSetFieldMapper.setTargetType(ValueSetUploadDto.class);
        return valueSetFieldMapper;
    }

    @Bean
    ItemWriter<ValueSet> valueSetWriter() {
        RepositoryItemWriter valueSetWriter = new RepositoryItemWriter();
        valueSetWriter.setRepository(valueSetRepository);
        valueSetWriter.setMethodName("save");
        return valueSetWriter;

    }
    // end::readerwriterprocessor[]

}


