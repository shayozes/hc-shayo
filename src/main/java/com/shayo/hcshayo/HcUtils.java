package com.shayo.hcshayo;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.stereotype.Component;

@Component
public class HcUtils {
    private static HazelcastInstance hz;
    private static IMap<Long, String> cache;

    public HazelcastInstance getHcInstance() {
        if (hz == null) {
            hz = Hazelcast.getHazelcastInstanceByName("shayos-instance");
        }
        return hz;
    }

    public IMap<Long, String> getCache() {
        if (cache == null) {
            cache = getHcInstance().getMap("test-cache");
            cache.addEntryListener(new EntryListener(cache), true);
        }
        return cache;
    }
}
