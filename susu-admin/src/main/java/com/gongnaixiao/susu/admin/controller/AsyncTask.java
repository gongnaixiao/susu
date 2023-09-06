package com.gongnaixiao.susu.admin.controller;

import cn.hutool.core.date.DateUtil;
import com.gongnaixiao.susu.engine.async.timer.AbstractRetryTask;
import com.gongnaixiao.susu.engine.async.timer.Timeout;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class AsyncTask extends AbstractRetryTask {

	public AsyncTask() {
		super("asyncTask", 5000L, 3);
	}

	@Override
	protected void doRetry(Timeout timeout) {
		log.info(DateUtil.now());
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("tttt");
	}

}
