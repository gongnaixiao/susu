package com.gongnaixiao.susu.formeditor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gongnaixiao.susu.formeditor.entity.Field;
import com.gongnaixiao.susu.formeditor.mapper.FieldMapper;
import com.gongnaixiao.susu.formeditor.service.FieldService;
import org.springframework.stereotype.Service;

@Service
public class FieldServiceImpl extends ServiceImpl<FieldMapper, Field> implements FieldService {

}
