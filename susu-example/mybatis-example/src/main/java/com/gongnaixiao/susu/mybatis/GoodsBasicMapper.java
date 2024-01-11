package com.gongnaixiao.susu.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsBasicMapper {
    @Insert("GoodsBasicMapper/insert.ftl")
    public int insert(@Param("r") GoodsBasic goodsBasic);
}
