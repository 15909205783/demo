package com.neo.luckmoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private LimitConfig limitConfig;

    @GetMapping("/hello")
    public String say() {
        return "说明："+limitConfig.getDecription();
    }
}
