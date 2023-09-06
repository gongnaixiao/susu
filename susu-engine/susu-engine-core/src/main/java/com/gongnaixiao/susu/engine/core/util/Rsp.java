package com.gongnaixiao.susu.engine.core.util;

import com.gongnaixiao.susu.engine.core.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Rsp<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String msg;

	@Getter
	@Setter
	private T data;

	public static <T> Rsp<T> ok() {
		return restResult(null, CommonConstants.SUCCESS, null);
	}

	public static <T> Rsp<T> ok(T data) {
		return restResult(data, CommonConstants.SUCCESS, null);
	}

	public static <T> Rsp<T> ok(T data, String msg) {
		return restResult(data, CommonConstants.SUCCESS, msg);
	}

	public static <T> Rsp<T> failed() {
		return restResult(null, CommonConstants.FAIL, null);
	}

	public static <T> Rsp<T> failed(String msg) {
		return restResult(null, CommonConstants.FAIL, msg);
	}

	public static <T> Rsp<T> failed(T data) {
		return restResult(data, CommonConstants.FAIL, null);
	}

	public static <T> Rsp<T> failed(T data, String msg) {
		return restResult(data, CommonConstants.FAIL, msg);
	}

	public static <T> Rsp<T> result(T data, int code, String msg) {
		return restResult(data, code, msg);
	}

	public static <T> Rsp<T> restResult(T data, int code, String msg) {
		Rsp<T> apiResult = new Rsp<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

	public Boolean isSuccess() {
		return (this.code == CommonConstants.SUCCESS);
	}

}
