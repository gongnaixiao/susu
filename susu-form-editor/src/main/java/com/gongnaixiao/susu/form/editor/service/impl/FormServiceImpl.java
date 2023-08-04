package com.gongnaixiao.susu.form.editor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gongnaixiao.susu.form.editor.service.FormService;
import com.gongnaixiao.susu.form.editor.entity.Form;
import com.gongnaixiao.susu.form.editor.mapper.FormMapper;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl extends ServiceImpl<FormMapper, Form> implements FormService {

}
