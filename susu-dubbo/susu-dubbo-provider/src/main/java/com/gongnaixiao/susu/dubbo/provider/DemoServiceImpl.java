package com.gongnaixiao.susu.dubbo.provider;

import com.gongnaixiao.susu.dubbo.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

}