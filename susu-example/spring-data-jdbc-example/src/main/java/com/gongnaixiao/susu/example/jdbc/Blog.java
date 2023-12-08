package com.gongnaixiao.susu.example.jdbc;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Blog {
    @Id
    private Long id;
    private String title;
    private String content;
}
