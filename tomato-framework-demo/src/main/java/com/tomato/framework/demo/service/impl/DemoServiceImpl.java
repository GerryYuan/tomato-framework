package com.tomato.framework.demo.service.impl;

import com.tomato.framework.demo.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

//	@Autowired
//	private DemoDAO demoDAO;
	
	@Override
	public String ping() {
//		demoDAO.getDemoMango(1);
		return "cs";
	}

}
