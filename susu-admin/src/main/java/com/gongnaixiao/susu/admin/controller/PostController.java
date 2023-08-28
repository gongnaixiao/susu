package com.gongnaixiao.susu.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gongnaixiao.susu.engine.core.util.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/post")
@Slf4j
public class PostController {

	@GetMapping("/page")
	public Resp getSysPostPage(Page page ) {
		log.info("do call sysPost");
		return Resp.ok("hello");
	}

}
