package com.gongnaixiao.susu.example.jdbc;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("goods_basic")
public class GoodsBasic extends AbstractEntity {

	private String code;

	private String barcode;

	private String shortName;

	private String photos;

	private String properties;

	private String unit;

	private Integer state;

}
