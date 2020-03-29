package com.shayo.hcshayo;

import com.hazelcast.core.Offloadable;
import com.hazelcast.map.AbstractEntryProcessor;

import java.util.Map;

public class EntryProcessor extends AbstractEntryProcessor<Long, String> implements Offloadable {
    @Override
    public Object process(Map.Entry<Long, String> entry) {
        String oldEntryValue = entry.getValue();
        String newEntryValue = oldEntryValue.concat("Modified!");
        System.out.printf("Old: %s, New: %s, Key: %s\n", oldEntryValue, newEntryValue, entry.getKey());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        entry.setValue(newEntryValue);
        System.out.println("Entry has been set");
        return null;
    }

    @Override
    public String getExecutorName() {
        return "test-exec";
    }
}