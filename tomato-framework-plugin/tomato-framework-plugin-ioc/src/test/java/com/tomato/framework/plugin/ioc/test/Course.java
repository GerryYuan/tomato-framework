package com.tomato.framework.plugin.ioc.test;

import lombok.Data;

@Data
public class Course {
    
    private String name;
    
    private Integer age;
    
    private User user;
    
    public void init() {
        System.out.println("初始化Course.init方法");
    }
}
