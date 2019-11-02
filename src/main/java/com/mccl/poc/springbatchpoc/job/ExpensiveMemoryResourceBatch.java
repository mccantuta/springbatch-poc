package com.mccl.poc.springbatchpoc.job;

import com.mccl.poc.springbatchpoc.model.ExpensiveMemoryResource;
import com.mccl.poc.springbatchpoc.reader.ExpenseMemoryResourceReader;
import com.mccl.poc.springbatchpoc.writer.ExpensiveMemoryResourceWriter;
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
public class ExpensiveMemoryResourceBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ExpenseMemoryResourceReader expenseMemoryResourceReader;

    @Autowired
    private ExpensiveMemoryResourceWriter expensiveMemoryResourceWriter;

    @Bean
    public Job simpleStringJob() {
        return jobBuilderFactory.get("expensiveMemoryResourceJob")
                .incrementer(new RunIdIncrementer())
                .flow(getExpensiveMemoryResourceStep())
                .end()
                .build();
    }

    private Step getExpensiveMemoryResourceStep() {
        return stepBuilderFactory.get("expensiveMemoryResourceStep")
                .<ExpensiveMemoryResource, ExpensiveMemoryResource>chunk(1)
                .reader(expenseMemoryResourceReader)
                .writer(expensiveMemoryResourceWriter)
                .build();
    }

    private ListItemReader<String> getListItemReader() {
        List<String> stringList = Arrays.asList("one", "two", "three");
        return new ListItemReader<>(stringList);
    }
}
