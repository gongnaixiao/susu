package com.gongnaixiao.susu.example.plugin;

import org.springframework.plugin.core.PluginRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
public class SmsController {

	@Resource
	PluginRegistry<SmsService, Req> pluginRegistry;

	@PostMapping("/sendMsg")
	public void sendMsg(@RequestBody Req req) {
		Optional<SmsService> pluginFor = pluginRegistry.getPluginFor(req);
		pluginFor.get().sendSms(req);
	}

}
