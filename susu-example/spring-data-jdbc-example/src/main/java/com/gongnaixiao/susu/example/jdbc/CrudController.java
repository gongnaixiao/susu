package com.gongnaixiao.susu.example.jdbc;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class CrudController {
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/save")
    public void save() {
        GoodsBasic goodsBasic = new GoodsBasic();
        goodsBasic.setCode(UUID.randomUUID().toString());
        goodsBasic.setBarcode(UUID.randomUUID().toString());
        goodsBasic.setShortName("pen");
        goodsBasic.setPhotos("");
        goodsBasic.setProperties("");
        goodsBasic.setUnit("");
        goodsBasic.setState(1);
        goodsBasic.setCreated(new Date());
        goodsBasic.setModified(new Date());

        goodRepository.save(goodsBasic);
    }

    @GetMapping("details")
    public String details() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<GoodsBasic> all = goodRepository.findAll(pageRequest);
        return JSONUtil.toJsonStr(all);
    }

    @GetMapping("/blog/save")
    public String blog() {
        Blog blog = new Blog();
        blog.setTitle("jdbc");
        blog.setContent("jdbc教程");
        Blog blog1 = blogRepository.save(blog);

        return JSONUtil.toJsonStr(blog1);
    }

    @RequestMapping("/blog/list")
    public String getBlogs() {
        List<Blog> byTitle = blogRepository.findByTitle("jdbc");

        return JSONUtil.toJsonStr(byTitle);
    }

}
