package com.gongnaixiao.susu.multiDataSource.controller;

import com.gongnaixiao.susu.multiDataSource.repository.postgres.Blog;
import com.gongnaixiao.susu.multiDataSource.repository.postgres.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/blog")
    public String getBlog() {
        Optional<Blog> byId = blogRepository.findById(1L);
        return "blog";
    }

    @RequestMapping("/saveBlog")
    public String saveBlog() {
        Blog blog = new Blog();
        blog.setTitle("title");
        blog.setContent("content");
        blogRepository.save(blog);

        return "blog";
    }

    @RequestMapping("/blog2")
    public String getBlog2() {
        jdbcTemplate.queryForList("select * from blog");
        return "blog";
    }
}
