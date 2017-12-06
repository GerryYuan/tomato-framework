package com.tomato.framework.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tomato.framework.demo.service.DemoRemoteService;

import java.util.Date;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2017-04-09-20:33
 */
@Service(interfaceClass = DemoRemoteService.class)
public class DemoRemoteServiceImpl implements DemoRemoteService {
    @Override
    public String getDate(String name, Integer value) {
        return name + new Date().getTime() + value;
    }
}
