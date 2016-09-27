package com.tomato.framework.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomato.framework.demo.dao.DemoDAO;
import com.tomato.framework.demo.model.DemoMango;
import com.tomato.framework.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDAO demoDAO;
	
	@Override
	public String ping() {
		DemoMango demoMango = new DemoMango();
		demoMango.setId(1);
		demoMango.setName("name1");
		demoDAO.addDemoMango(demoMango);
		return "cs";
	}

}
