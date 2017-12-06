package com.tomato.framework.demo.client.controller;

import com.tomato.framework.rest.helper.ViewModelHelper;
import com.tomato.framework.rest.result.ViewModelResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {

    //    @RemoteReference(url = "/demoRemoteService", serviceInterface = DemoRemoteService.class)
//    @Autowired
//    private DemoRemoteService demoRemoteService;

    @RequestMapping("/get/{id}")
    public ViewModelResult<?> get(@PathVariable(name = "id") Integer id) {
//        DemoRemoteService demoRemoteService = SpringApplicationContext.getBean(DemoRemoteService.class);
//        return ViewModelHelper.OKViewModelResult(demoRemoteService.getDate("getId", id));
        return ViewModelHelper.OKViewModelResult();
    }

}
