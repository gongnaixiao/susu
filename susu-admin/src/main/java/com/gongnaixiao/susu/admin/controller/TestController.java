package com.gongnaixiao.susu.admin.controller;

import cn.hutool.core.date.DateUtil;
import com.gongnaixiao.susu.engine.async.timer.AbstractRetryTask;
import com.gongnaixiao.susu.engine.async.timer.RetryTemplate;
import com.gongnaixiao.susu.engine.async.timer.Timeout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

	@Autowired
	private RetryTemplate retryTemplate;

	@GetMapping("/test")
	public void test() {
		log.info(DateUtil.now());
		retryTemplate.execute(new AbstractRetryTask("asyncTask1", 2000L, 3) {
			@Override
			protected void doRetry(Timeout timeout) {
				log.info(DateUtil.now());
				try {
					Thread.sleep(10000L);
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				throw new RuntimeException("tttt");
			}
		});
		retryTemplate.execute(new AbstractRetryTask("asyncTask2", 2000L, 3) {
			@Override
			protected void doRetry(Timeout timeout) {
				log.info(DateUtil.now());
				try {
					Thread.sleep(5000L);
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				throw new RuntimeException("tttt");
			}
		});
	}

}
