package com.gongnaixiao.susu.formeditor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gongnaixiao.susu.formeditor.entity.Form;
import com.gongnaixiao.susu.formeditor.mapper.FormMapper;
import com.gongnaixiao.susu.formeditor.service.FormService;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl extends ServiceImpl<FormMapper, Form> implements FormService {

}
