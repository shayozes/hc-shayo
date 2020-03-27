package com.shayo.hcshayo;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.MapListener;

import static com.shayo.hcshayo.MainController.hz;

public class EntryListener implements EntryAddedListener<Long, String>, MapListener {
    @Override
    public void entryAdded(EntryEvent<Long, String> event) {
        System.out.println("event: " + event);
        IExecutorService executor = hz.getExecutorService("test-exec");
        hz.getMap("test-cache").executeOnKey(event.getKey(), new EntryProcessor());
    }
}
