package com.gongnaixiao.susu.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@TableName("sys_post")
@EqualsAndHashCode(callSuper = true)
public class SysPost extends Model<SysPost> {

	private static final long serialVersionUID = 1L;

	/**
	 * 岗位ID
	 */
	@TableId(value = "post_id", type = IdType.ASSIGN_ID)
	private Long postId;

	/**
	 * 岗位编码
	 */
	@NotBlank(message = "岗位编码不能为空")
	private String postCode;

	/**
	 * 岗位名称
	 */
	@NotBlank(message = "岗位名称不能为空")
	private String postName;

	/**
	 * 岗位排序
	 */
	@NotNull(message = "排序值不能为空")
	private Integer postSort;

	/**
	 * 岗位描述
	 */
	private String remark;

	/**
	 * 创建人
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createBy;

	/**
	 * 修改人
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;

	/**
	 * 是否删除 -1：已删除 0：正常
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private String delFlag;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	private LocalDateTime updateTime;

}
