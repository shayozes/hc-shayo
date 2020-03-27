package com.shayo.hcshayo;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MainController {
    public static HazelcastInstance hz = Hazelcast.newHazelcastInstance();
    // todo: in MSL, we use executor or interceptor?

    @RequestMapping("/")
    public String index() {
        return "Up!";
    }

    @RequestMapping("/set")
    public String set() {
        IMap<Long, String> testCache = hz.getMap("test-cache");
        testCache.addEntryListener(new EntryListener(), true);

        for (long index = 0; index < 1000; index++) {
            testCache.set(index, String.format("Shayo #%d", index));
        }

        return "done.";
    }

    @RequestMapping("/get")
    public String get() {
        return hz.getMap("test-cache").toString();
    }
}