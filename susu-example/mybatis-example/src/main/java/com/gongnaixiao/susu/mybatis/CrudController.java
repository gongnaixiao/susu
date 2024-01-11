package com.gongnaixiao.susu.mybatis;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CrudController {
    @Autowired
    private GoodsBasicMapper goodsBasicMapper;

    @RequestMapping("/good/save")
    public void save() {
        GoodsBasic goodsBasic = new GoodsBasic();
        goodsBasic.setCreated(new Date());
        goodsBasic.setModified(new Date());
        goodsBasic.setVersion(1L);
        goodsBasic.setCode("abc");
        goodsBasic.setBarcode("abc");
        goodsBasic.setShortName("abc");
        goodsBasic.setPhotos(JSONUtil.toJsonStr("abc"));
        goodsBasic.setProperties("abc");
        goodsBasic.setUnit("abc");
        goodsBasic.setState(1);

        goodsBasicMapper.insert(goodsBasic);
    }
}
