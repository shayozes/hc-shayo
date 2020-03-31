package com.shayo.hcshayo;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.IMap;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.MapListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntryListener implements EntryAddedListener<Long, String>, MapListener {
    private Logger logger = LoggerFactory.getLogger(EntryListener.class);
    private IMap<Long, String> cache;

    public EntryListener(IMap<Long, String> cache) {
        super();
        this.cache = cache;
    }

    @Override
    public void entryAdded(EntryEvent<Long, String> event) {
        logger.info("event: " + event);
        cache.submitToKey(event.getKey(), new EntryProcessor());
    }
}
