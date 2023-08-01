package com.gongnaixiao.susu.gateway.common.util;

import cn.hutool.core.codec.Base64;
import com.gongnaixiao.susu.gateway.common.exception.CheckedException;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

@Slf4j
@UtilityClass
public class WebUtils {

	private static final String BASIC_ = "Basic ";

	/**
	 * 从request 获取CLIENT_ID
	 * @return
	 */
	@SneakyThrows
	public String getClientId(ServerHttpRequest request) {
		String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		return splitClient(header)[0];
	}

	@NotNull
	private static String[] splitClient(String header) {
		if (header == null || !header.startsWith(BASIC_)) {
			throw new CheckedException("请求头中client信息为空");
		}
		byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		}
		catch (IllegalArgumentException e) {
			throw new CheckedException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, StandardCharsets.UTF_8);

		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new CheckedException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}

}
