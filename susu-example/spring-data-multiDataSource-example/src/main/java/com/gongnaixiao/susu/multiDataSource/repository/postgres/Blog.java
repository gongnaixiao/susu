package com.gongnaixiao.susu.multiDataSource.repository.postgres;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Blog {
    @Id
    private String id;
    private String title;
    private String content;
}
