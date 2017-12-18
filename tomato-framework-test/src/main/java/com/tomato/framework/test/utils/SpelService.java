package com.tomato.framework.test.utils;

import org.springframework.stereotype.Component;

@Component
public class SpelService {

    @SpelCache(key = "cache")
    public String getName(String name) {
        return name;
    }
}

