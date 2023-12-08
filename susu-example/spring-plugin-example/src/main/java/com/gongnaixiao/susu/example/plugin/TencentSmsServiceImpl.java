package com.gongnaixiao.susu.example.plugin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TencentSmsServiceImpl implements SmsService {

	@Override
	public void sendSms(Req req) {
		log.info("phone:{} use tencent send msg {}", req.getMobilePhone(), req.getMsgContent());
	}

	@Override
	public boolean supports(Req req) {
		if (SmsType.TENCENT == req.getSmsType()) {
			return true;
		}
		return false;
	}

}
