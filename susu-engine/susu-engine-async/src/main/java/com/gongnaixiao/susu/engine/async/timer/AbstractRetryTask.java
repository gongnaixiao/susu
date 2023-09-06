package com.gongnaixiao.susu.engine.async.timer;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * AbstractRetryTask
 */
public abstract class AbstractRetryTask implements TimerTask {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * retry period
	 */
	private final long retryPeriod;

	/**
	 * define the most retry times
	 */
	private final int retryTimes;

	/**
	 * task name for this task
	 */
	private final String taskName;

	/**
	 * times of retry. retry task is execute in single thread so that the times is not
	 * need volatile.
	 */
	private int times = 1;

	private volatile boolean cancel;

	public AbstractRetryTask(String taskName, long retryPeriod, int retryTimes) {
		if (StrUtil.isBlank(taskName)) {
			throw new IllegalArgumentException();
		}
		this.taskName = taskName;
		this.cancel = false;
		this.retryPeriod = retryPeriod;
		this.retryTimes = retryTimes;
	}

	public long getRetryPeriod() {
		return retryPeriod;
	}

	public void cancel() {
		cancel = true;
	}

	public boolean isCancel() {
		return cancel;
	}

	protected void reput(Timeout timeout, long tick) {
		if (timeout == null) {
			throw new IllegalArgumentException();
		}

		Timer timer = timeout.timer();
		if (timer.isStop() || timeout.isCancelled() || isCancel()) {
			return;
		}
		times++;
		timer.newTimeout(timeout.task(), tick, TimeUnit.MILLISECONDS);
	}

	@Override
	public void run(Timeout timeout) throws Exception {
		if (timeout.isCancelled() || timeout.timer().isStop() || isCancel()) {
			// other thread cancel this timeout or stop the timer.
			return;
		}
		if (times > retryTimes) {
			// 1-13 - failed to execute the retrying task.

			logger.warn("Final failed to execute task " + taskName + ", retry " + retryTimes + " times.");

			return;
		}
		if (logger.isInfoEnabled()) {
			logger.info(taskName + " : ");
		}
		try {
			doRetry(timeout);
		}
		catch (Throwable t) { // Ignore all the exceptions and wait for the next retry

			// 1-13 - failed to execute the retrying task.

			logger.warn("Failed to execute task " + taskName + ", waiting for again, cause:" + t.getMessage(), t);

			// reput this task when catch exception.
			reput(timeout, retryPeriod);
		}
	}

	protected abstract void doRetry(Timeout timeout);

}
