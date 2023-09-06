package com.gongnaixiao.susu.example.plugin;

import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@Configuration
@EnablePluginRegistries({SmsService.class})
public class Config {
}
