package com.gongnaixiao.susu.example.plugin;

import org.springframework.plugin.core.Plugin;

public interface SmsService extends Plugin<Req> {

	void sendSms(Req req);

}
