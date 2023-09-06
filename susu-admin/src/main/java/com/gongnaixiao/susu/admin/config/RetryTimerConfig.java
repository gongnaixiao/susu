package com.gongnaixiao.susu.admin.config;

import com.gongnaixiao.susu.engine.async.timer.RetryTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryTimerConfig {

	@Bean
	public RetryTemplate retryTemplate() {
		return new RetryTemplate(1000L);
	}

}
