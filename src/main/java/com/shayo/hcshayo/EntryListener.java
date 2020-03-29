package com.shayo.hcshayo;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.MapListener;

public class EntryListener implements EntryAddedListener<Long, String>, MapListener {
    @Override
    public void entryAdded(EntryEvent<Long, String> event) {
        System.out.println("event: " + event);
        HcUtils.getCache().submitToKey(event.getKey(), new EntryProcessor());
    }
}
