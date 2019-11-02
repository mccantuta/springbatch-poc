package com.mccl.poc.springbatchpoc.reader;

import com.mccl.poc.springbatchpoc.model.ExpensiveMemoryResource;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ExpenseMemoryResourceReader implements ItemReader<ExpensiveMemoryResource> {

    private int maxItems = 0;

    @Override
    public ExpensiveMemoryResource read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        ExpensiveMemoryResource expensiveMemoryResource = null;
        if (maxItems < 10) {
            expensiveMemoryResource = new ExpensiveMemoryResource();
            expensiveMemoryResource.setExpensiveElement(generateExpensiveMemoryResourceData());
        }
        maxItems++;

        return expensiveMemoryResource;
    }

    private List<String> generateExpensiveMemoryResourceData() {
        return Stream.iterate(0, i -> i++)
                .limit(1000000)
                .map(i -> i.toString())
                .collect(Collectors.toList());
    }
}
