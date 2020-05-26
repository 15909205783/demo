package com.neo.goodskill.controller;

import com.neo.goodskill.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillController {
    @Autowired
    private SkillService skillService;

    @RequestMapping("/skill")
    public String skill(String product) {
        return skillService.skillProduct(product);
    }

    @GetMapping(value = "/skill-redis")
    @ResponseBody
    public String skillRedis(String product) {
        return skillService.skillProductByRedis(product);
    }

    @GetMapping(value = "/skill/query")
    @ResponseBody
    public String querySkillProductInfo(String product) {
        return skillService.querySkillProductInfo(product);
    }
}
