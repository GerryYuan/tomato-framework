package com.tomato.framework.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomato.framework.demo.dao.DemoDAO;
import com.tomato.framework.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDAO demoDAO;
	
	@Override
	public String ping() {
		demoDAO.getDemoMango(1);
		return "cs";
	}

}
