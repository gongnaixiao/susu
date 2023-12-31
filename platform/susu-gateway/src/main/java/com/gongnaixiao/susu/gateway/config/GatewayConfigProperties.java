package com.gongnaixiao.susu.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import java.util.List;

@Data
@RefreshScope
@ConfigurationProperties("gateway")
public class GatewayConfigProperties {

	/**
	 * 网关解密登录前端密码 秘钥
	 */
	private String encodeKey;

	/**
	 * 网关不需要校验验证码的客户端
	 */
	private List<String> ignoreClients;

}
