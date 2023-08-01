package com.gongnaixiao.susu.engine.core.util;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Req<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private T content;

}
