package com.gongnaixiao.susu.example.plugin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AliSmsServiceImpl implements SmsService {

	@Override
	public void sendSms(Req req) {
		log.info("phone:{} use ali send msg {}", req.getMobilePhone(), req.getMsgContent());
	}

	@Override
	public boolean supports(Req req) {
		if (SmsType.ALI == req.getSmsType()) {
			return true;
		}
		return false;
	}

}
