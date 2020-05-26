package com.neo.goodskill.service;

public interface SkillService {
    String querySkillProductInfo(String productId);

    String skillProduct(String productId);

    String skillProductByRedis(String productId);
}
