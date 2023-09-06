package com.gongnaixiao.susu.engine.async.timer;

import org.springframework.beans.factory.DisposableBean;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RetryTemplate implements DisposableBean {

	private final HashedWheelTimer retryTimer;

	/**
	 * The time in milliseconds the retryExecutor will wait
	 */
	private final long retryPeriod;

	public RetryTemplate(long retryPeriod) {
		this.retryPeriod = retryPeriod;
		// this.retryTimer = new HashedWheelTimer(new NamedThreadFactory("RetryTimer",
		// true), retryPeriod, TimeUnit.MILLISECONDS, 128);
		this.retryTimer = new HashedWheelTimer(Executors.defaultThreadFactory(), retryPeriod, TimeUnit.MILLISECONDS,
				128);
	}

	public void execute(AbstractRetryTask newTask) {
		retryTimer.newTimeout(newTask, newTask.getRetryPeriod(), TimeUnit.MILLISECONDS);
	}

	@Override
	public void destroy() throws Exception {
		if (retryTimer != null) {
			retryTimer.stop();
		}
	}

}
