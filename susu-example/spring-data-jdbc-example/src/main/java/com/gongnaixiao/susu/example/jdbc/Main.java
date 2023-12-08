package com.gongnaixiao.susu.example.jdbc;

import cn.hutool.json.JSONUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.core.type.StandardClassMetadata;

public class Main {
    public static void main(String[] args) {
        StandardClassMetadata standardClassMetadata = new StandardClassMetadata(SpringApplication.class);
        System.out.println(standardClassMetadata.getClassName());
        System.out.println(standardClassMetadata.getSuperClassName());
        System.out.println(JSONUtil.toJsonStr(standardClassMetadata.getInterfaceNames()));
        System.out.println(JSONUtil.toJsonStr(standardClassMetadata.getMemberClassNames()));

    }
}
