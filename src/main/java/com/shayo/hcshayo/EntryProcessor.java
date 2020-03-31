package com.shayo.hcshayo;

import com.hazelcast.core.Offloadable;
import com.hazelcast.map.AbstractEntryProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class EntryProcessor extends AbstractEntryProcessor<Long, String> implements Offloadable {
    private Logger logger = LoggerFactory.getLogger(EntryListener.class);

    @Override
    public Object process(Map.Entry<Long, String> entry) {
        String oldEntryValue = entry.getValue();
        String newEntryValue = oldEntryValue.concat("Modified!");
        logger.info("Old: {}, New: {}, Key: {}", oldEntryValue, newEntryValue, entry.getKey());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        entry.setValue(newEntryValue);
        logger.info("Entry has been set");
        return null;
    }

    @Override
    public String getExecutorName() {
        return "test-exec";
    }
}