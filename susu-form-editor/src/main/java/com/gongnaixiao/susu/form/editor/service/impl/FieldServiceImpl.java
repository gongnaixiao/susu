package com.gongnaixiao.susu.form.editor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gongnaixiao.susu.form.editor.entity.Field;
import com.gongnaixiao.susu.form.editor.mapper.FieldMapper;
import com.gongnaixiao.susu.form.editor.service.FieldService;
import org.springframework.stereotype.Service;

@Service
public class FieldServiceImpl extends ServiceImpl<FieldMapper, Field> implements FieldService {

}
