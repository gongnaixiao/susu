package com.gongnaixiao.susu.engine.core.constant;

public interface SecurityConstants {

	/**
	 * 内部
	 */
	String FROM_IN = "Y";

	/**
	 * 标志
	 */
	String FROM = "from";

	/**
	 * 验证码有效期,默认 60秒
	 */
	long CODE_TIME = 60;

	/**
	 * 短信登录 参数名称
	 */
	String SMS_PARAMETER_NAME = "phone";

	/**
	 * 默认登录URL
	 */
	String OAUTH_TOKEN_URL = "/oauth2/token";

	/**
	 * grant_type
	 */
	String REFRESH_TOKEN = "refresh_token";
}
