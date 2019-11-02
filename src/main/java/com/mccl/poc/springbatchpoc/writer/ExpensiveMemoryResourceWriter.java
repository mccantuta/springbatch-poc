package com.mccl.poc.springbatchpoc.writer;

import com.mccl.poc.springbatchpoc.model.ExpensiveMemoryResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class ExpensiveMemoryResourceWriter implements ItemWriter<ExpensiveMemoryResource> {
    private static final Logger LOG = LoggerFactory.getLogger(ExpensiveMemoryResourceWriter.class);

    @Override
    public void write(List<? extends ExpensiveMemoryResource> list) throws Exception {
        int mb = 1024*1024;
        String usedMemory = new BigDecimal(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
                        .divide(new BigDecimal(mb))
                        .setScale(2, RoundingMode.CEILING).toString();
        LOG.info("Writing list of {} ExpensiveMemoryResourceWriter, used memory {}", list.size(), usedMemory);
        list.stream().forEach(i -> LOG.info(String.valueOf(i.getExpensiveElement().size())));
    }
}
