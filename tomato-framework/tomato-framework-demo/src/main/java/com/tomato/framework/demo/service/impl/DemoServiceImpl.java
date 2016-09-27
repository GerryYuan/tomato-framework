package com.tomato.framework.demo.service.impl;

import org.springframework.stereotype.Service;

import com.tomato.framework.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

	@Override
	public String ping() {
		return "cs";
	}

}
