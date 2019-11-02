package com.mccl.poc.springbatchpoc.job;

import com.mccl.poc.springbatchpoc.writer.LogItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SimpleStringBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private LogItemWriter logItemWriter;

    @Bean
    public Job simpleStringJob() {
        return jobBuilderFactory.get("simpleStringJob")
                .incrementer(new RunIdIncrementer())
                .flow(getSimpleStringStep())
                .end()
                .build();
    }

    private Step getSimpleStringStep() {
        return stepBuilderFactory.get("simpleStringStep")
                .<String, String>chunk(10)
                .reader(getListItemReader())
                .writer(logItemWriter)
                .build();
    }

    private ListItemReader<String> getListItemReader() {
        List<String> stringList = Arrays.asList("one", "two", "three");
        return new ListItemReader<>(stringList);
    }
}
