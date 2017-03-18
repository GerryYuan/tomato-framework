package com.tomato.framework.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomato.framework.demo.service.DemoService;
import com.tomato.framework.rest.helper.ViewModelHelper;
import com.tomato.framework.rest.result.ViewModelResult;

@RestController
@RequestMapping
public class DemoController {

	@Autowired
	private DemoService demoService;
	
	@RequestMapping("/ping")
	public ViewModelResult<?> ping() {
		return ViewModelHelper.OKViewModelResult(demoService.ping());
	}
}
