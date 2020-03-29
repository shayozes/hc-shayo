package com.shayo.hcshayo;

import com.hazelcast.core.IMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MainController {
    @RequestMapping("/")
    public String index() {
        return "Up!";
    }

    @RequestMapping("/set")
    public String set() {
        IMap<Long, String> testCache = HcUtils.getCache();

        for (long index = 0; index < 1000; index++) {
            testCache.set(index, String.format("Shayo #%d", index));
        }

        return "Hazelcast now processes";
    }

    @RequestMapping("/get")
    public String get() {
        StringBuilder output = new StringBuilder("<h1>/get</h1><br>");
        for (String value : HcUtils.getCache().values()) {
            output.append(value).append("<br>");
        }
        return output.toString();
    }
}