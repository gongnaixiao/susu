package com.gongnaixiao.susu.form.editor.controller;

import cn.hutool.core.date.DateUtil;
import io.netty.util.HashedWheelTimer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class WheelController {

	@Autowired
	private HashedWheelTimer hashedWheelTimer;

	/**
	 * threadFactory：线程工厂，用于创建工作线程， 默认是Executors.defaultThreadFactory()
	 * tickDuration：tick的周期，即多久tick一次 unit: tick周期的单位 ticksPerWheel：时间轮的长度，一圈下来有多少格
	 * leakDetection：是否开启内存泄漏检测，默认是true
	 * maxPendingTimeouts：最多执行的任务数，默认是-1，即不限制。在高并发量情况下才会设置这个参数。
	 * @param args
	 */
	/**
	 * 超时任务1000毫秒，超时之后，由HashedWheelTimer类中的worker线程，执行超时之后的任务
	 * HashedWheelTimer有32个槽（相当于一个圈的32分之一）,每移动一个槽的时间是100毫秒。 任务需要经过的tick数为：1000 / 100 = 10次
	 * （等待时长 / tickDuration） 任务需要经过的轮数为：10次 / 32次/轮 = 0轮 （tick总次数 / ticksPerWheel）
	 * 因为任务超时后不能马上被worker线程执行，需要等到worker线程移到相应卡槽位置时才会执行，因此说执行时间不精确。
	 * HashWheelTimer的核心是worker线程，主要负责每过tickDuration时间就累加一次tick。
	 * 同时，也负责执行到期的timeout任务，此外，还负责添加timeout任务到指定的wheel中。
	 *
	 */
	@GetMapping("/delay")
	public String doSomethime() {
		log.info(DateUtil.now());
		hashedWheelTimer.newTimeout(timeout -> {
			System.out.println("====");
			log.info(DateUtil.now());
			System.out.println("====");
		}, 10000, TimeUnit.MILLISECONDS);
		log.info("=== delay 10000 ===");
		return "delay 10000";
	}

	/**
	 * 调用超时与重试处理
	 */
	public void failback() {

	}

	/**
	 * 定时心跳检测
	 */
	public void heartBeatDetection() {

	}
}
