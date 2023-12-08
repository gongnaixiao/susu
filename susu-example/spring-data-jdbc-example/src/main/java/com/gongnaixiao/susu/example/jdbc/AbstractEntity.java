package com.gongnaixiao.susu.example.jdbc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.Date;

@Data
public abstract class AbstractEntity {

	@Id
	private Long id;

	private Date created;

	private Date modified;

	@Version
	private Long version;

}
