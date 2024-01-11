package com.gongnaixiao.susu.mybatis;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsBasic {
    private Long id;

    private Date created;

    private Date modified;

    private Long version;
    private String code;

    private String barcode;

    private String shortName;

    private String photos;

    private String properties;

    private String unit;

    private Integer state;

}
