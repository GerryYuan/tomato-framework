package com.tomato.framework.plugin.ioc.reource;

import java.io.InputStream;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-07-08-21:54
 */
public class ClassPathResource implements Resource {
    
    private String location;
    
    public ClassPathResource(String location) {
        this.location = location;
    }
    
    @Override
    public InputStream getInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:", ""));
    }
}
