package com.mccl.poc.springbatchpoc.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogItemWriter implements ItemWriter<String> {
    private static final Logger LOG = LoggerFactory.getLogger(LogItemWriter.class);

    @Override
    public void write(List<? extends String> list) throws Exception {
        LOG.info("Writing list of {} items", list.size());
        list.stream().forEach(i -> LOG.info(i));
    }
}
