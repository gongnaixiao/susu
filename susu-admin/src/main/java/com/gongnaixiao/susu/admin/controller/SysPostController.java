package com.gongnaixiao.susu.admin.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gongnaixiao.susu.admin.entity.SysPost;
import com.gongnaixiao.susu.admin.service.SysPostService;
import com.gongnaixiao.susu.engine.core.util.Rsp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/post")
@Slf4j
public class SysPostController {

	private final SysPostService sysPostService;

	@GetMapping("/page")
	public Rsp getSysPostPage(Page page, SysPost sysPost) {
		return Rsp.ok(sysPostService.page(page, Wrappers.<SysPost>lambdaQuery()
			.like(StrUtil.isNotBlank(sysPost.getPostName()), SysPost::getPostName, sysPost.getPostName())));
	}

	/**
	 * 通过id查询岗位信息表
	 * @param postId id
	 * @return R
	 */
	@GetMapping("/details/{postId}")
	public Rsp getById(@PathVariable("postId") Long postId) {
		return Rsp.ok(sysPostService.getById(postId));
	}

	/**
	 * 查询岗位信息信息
	 * @param query 查询条件
	 * @return R
	 */
	@GetMapping("/details")
	public Rsp getDetails(SysPost query) {
		return Rsp.ok(sysPostService.getOne(Wrappers.query(query), false));
	}

	/**
	 * 新增岗位信息表
	 * @param sysPost 岗位信息表
	 * @return R
	 */
	@PostMapping
	public Rsp save(@RequestBody SysPost sysPost) {
		return Rsp.ok(sysPostService.save(sysPost));
	}

	/**
	 * 修改岗位信息表
	 * @param sysPost 岗位信息表
	 * @return R
	 */
	@PutMapping
	public Rsp updateById(@RequestBody SysPost sysPost) {
		return Rsp.ok(sysPostService.updateById(sysPost));
	}

	/**
	 * 通过id删除岗位信息表
	 * @param ids id 列表
	 * @return R
	 */
	@DeleteMapping
	public Rsp removeById(@RequestBody Long[] ids) {
		return Rsp.ok(sysPostService.removeBatchByIds(CollUtil.toList(ids)));
	}

}
