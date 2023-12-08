package com.gongnaixiao.susu.example.plugin;

import lombok.Data;

@Data
public class Req {
    private SmsType smsType;
    private String mobilePhone;
    private String msgContent;

}
