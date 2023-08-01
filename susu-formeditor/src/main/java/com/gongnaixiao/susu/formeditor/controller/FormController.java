package com.gongnaixiao.susu.formeditor.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gongnaixiao.susu.engine.core.util.Req;
import com.gongnaixiao.susu.engine.core.util.Resp;
import com.gongnaixiao.susu.formeditor.FormType;
import com.gongnaixiao.susu.formeditor.entity.Field;
import com.gongnaixiao.susu.formeditor.entity.Form;
import com.gongnaixiao.susu.formeditor.entity.FormSubmit;
import com.gongnaixiao.susu.formeditor.service.FieldService;
import com.gongnaixiao.susu.formeditor.service.FormService;
import com.gongnaixiao.susu.formeditor.service.FormSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Everright-api/lowCode")
public class FormController {

	@Autowired
	private FormService formService;

	@Autowired
	private FieldService fieldService;

	@Autowired
	private FormSubmitService formSubmitService;

	@PostMapping("/form")
	public Resp createForm(Req<FormType> req) {
		Form form = new Form();
		form.setName(req.getName());
		form.setContent(JSONUtil.toJsonStr(req.getContent()));
		form.setCreateBy("a");
		form.setUpdateBy("a");
		LocalDateTime now = LocalDateTime.now();
		form.setCreateTime(now);
		form.setUpdateTime(now);
		formService.save(form);

		return Resp.ok(form);
	}

	@GetMapping("/form")
	public Resp getAllFormData() {
		List<Form> list = formService.list();

		return Resp.ok(list);
	}

	@GetMapping("/form/{formId}")
	public Resp getFormDataByid(@PathVariable Long formId) {
		Form form = formService.getById(formId);

		return Resp.ok(form);
	}

	@PostMapping("/form/{formId}")
	public Resp modifyFormDataByid(@PathVariable Long formId, Req req) {
		Form form = new Form();
		form.setId(formId);
		form.setName(req.getName());
		form.setContent(JSONUtil.toJsonStr(req.getContent()));
		form.setUpdateBy("aa");
		form.setUpdateTime(LocalDateTime.now());
		formService.updateById(form);

		// TODO field

		return Resp.ok(form);
	}

	@GetMapping("/form/{formId}/fields")
	public Resp getFieldsByFormid(@PathVariable Long formId) {
		List<Field> list = fieldService.list(Wrappers.<Field>lambdaQuery().eq(Field::getFormId, formId));

		return Resp.ok(list);
	}

	@PostMapping("/form/{formId}/fields")
	public Resp modifyFieldsByFormid(@PathVariable Long formId, Req req) {
		fieldService.update(
				Wrappers.<Field>lambdaUpdate().eq(Field::getFormId, formId).set(Field::getContent, req.getContent()));

		return Resp.ok();
	}

	@PostMapping("/form/{formId}/action/create")
	public Resp createActionByObjid(@PathVariable Long formId, Req<String> req) {
		FormSubmit formSubmit = new FormSubmit();
		formSubmit.setFormId(formId);
		formSubmit.setContent(req.getContent());
		formSubmit.setCreateBy("aa");
		formSubmit.setUpdateBy("aa");
		LocalDateTime now = LocalDateTime.now();
		formSubmit.setCreateTime(now);
		formSubmit.setUpdateTime(now);

		formSubmitService.save(formSubmit);
		return Resp.ok();
	}

	@PostMapping("/form/{formId}/action")
	public Resp getAllActionData(@PathVariable Long formId) {
		List<FormSubmit> list = formSubmitService
			.list(Wrappers.<FormSubmit>lambdaQuery().eq(FormSubmit::getFormId, formId));

		return Resp.ok(list);
	}

	@GetMapping("/form/{formId}/action/{id}")
	public Resp getAllActionDataByid(@PathVariable Long formId, @PathVariable Long id) {
		List<FormSubmit> list = formSubmitService
			.list(Wrappers.<FormSubmit>lambdaQuery().eq(FormSubmit::getFormId, formId).eq(FormSubmit::getId, id));

		return Resp.ok(list);
	}

	@PostMapping("/form/{formId}/action/{id}")
	public Resp modifyActionByObjidAndId(@PathVariable Long formId, @PathVariable Long id, Req<String> req) {
		formSubmitService.update(Wrappers.<FormSubmit>lambdaUpdate()
			.eq(FormSubmit::getFormId, formId)
			.eq(FormSubmit::getId, id)
			.set(FormSubmit::getContent, req.getContent()));

		return Resp.ok();
	}

	@DeleteMapping("/form/{formId}/action/{id}")
	public Resp deleteActionByObjidAndId(@PathVariable Long formId, @PathVariable Long id) {
		formSubmitService
			.remove(Wrappers.<FormSubmit>lambdaQuery().eq(FormSubmit::getFormId, formId).eq(FormSubmit::getId, id));

		return Resp.ok();
	}

}
