package com.gongnaixiao.susu.formeditor;

import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FormType {

	@Getter
	@Setter
	private int layoutType;

	@Getter
	@Setter
	private List list;

	private String config;

	private List fields;

	private String data;

	private String logic;

}
