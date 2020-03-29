package com.shayo.hcshayo;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public abstract class HcUtils {
    private static HazelcastInstance hz;
    private static IMap<Long, String> cache;

    public static HazelcastInstance getHcInstance() {
        if (hz == null) {
            hz = Hazelcast.newHazelcastInstance();
        }
        return hz;
    }

    public static IMap<Long, String> getCache() {
        if (cache == null) {
            cache = HcUtils.getHcInstance().getMap("test-cache");
            cache.addEntryListener(new EntryListener(), true);
        }
        return cache;
    }
}
