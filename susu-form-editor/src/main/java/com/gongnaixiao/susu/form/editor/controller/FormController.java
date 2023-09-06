package com.gongnaixiao.susu.form.editor.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gongnaixiao.susu.engine.core.util.Req;
import com.gongnaixiao.susu.engine.core.util.Rsp;
import com.gongnaixiao.susu.form.editor.FormType;
import com.gongnaixiao.susu.form.editor.entity.Field;
import com.gongnaixiao.susu.form.editor.entity.Form;
import com.gongnaixiao.susu.form.editor.entity.FormSubmit;
import com.gongnaixiao.susu.form.editor.service.FieldService;
import com.gongnaixiao.susu.form.editor.service.FormService;
import com.gongnaixiao.susu.form.editor.service.FormSubmitService;
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
	public Rsp createForm(@RequestBody Req<FormType> req) {
		Form form = new Form();
		form.setName(req.getName());
		form.setContent(JSONUtil.toJsonStr(req.getContent()));
		form.setCreateBy("a");
		form.setUpdateBy("a");
		LocalDateTime now = LocalDateTime.now();
		form.setCreateTime(now);
		form.setUpdateTime(now);
		formService.save(form);

		return Rsp.ok(form);
	}

	@GetMapping("/form")
	public Rsp getAllFormData() {
		List<Form> list = formService.list();

		return Rsp.ok(list);
	}

	@GetMapping("/form/{formId}")
	public Rsp getFormDataByid(@PathVariable Long formId) {
		Form form = formService.getById(formId);

		return Rsp.ok(form);
	}

	@PutMapping("/form/{formId}")
	public Rsp modifyFormDataByid(@PathVariable Long formId, @RequestBody Req req) {
		Form form = new Form();
		form.setId(formId);
		form.setName(req.getName());
		form.setContent(JSONUtil.toJsonStr(req.getContent()));
		form.setUpdateBy("aa");
		form.setUpdateTime(LocalDateTime.now());
		formService.updateById(form);

		// TODO field

		return Rsp.ok(form);
	}

	@DeleteMapping("/form/{formId}")
	public Rsp delFormDataByid(@PathVariable Long formId) {
		formService.removeById(formId);

		return Rsp.ok();
	}

	@GetMapping("/form/{formId}/fields")
	public Rsp getFieldsByFormid(@PathVariable Long formId) {
		List<Field> list = fieldService.list(Wrappers.<Field>lambdaQuery().eq(Field::getFormId, formId));

		return Rsp.ok(list);
	}

	@PutMapping("/form/{formId}/fields")
	public Rsp modifyFieldsByFormid(@PathVariable Long formId, @RequestBody Req req) {
		fieldService.update(
				Wrappers.<Field>lambdaUpdate().eq(Field::getFormId, formId).set(Field::getContent, req.getContent()));

		return Rsp.ok();
	}

	@PostMapping("/form/{formId}/action/create")
	public Rsp createActionByObjid(@PathVariable Long formId, @RequestBody Req<String> req) {
		FormSubmit formSubmit = new FormSubmit();
		formSubmit.setFormId(formId);
		formSubmit.setContent(req.getContent());
		formSubmit.setCreateBy("aa");
		formSubmit.setUpdateBy("aa");
		LocalDateTime now = LocalDateTime.now();
		formSubmit.setCreateTime(now);
		formSubmit.setUpdateTime(now);

		formSubmitService.save(formSubmit);
		return Rsp.ok();
	}

	@PostMapping("/form/{formId}/action")
	public Rsp getAllActionData(@PathVariable Long formId) {
		List<FormSubmit> list = formSubmitService
			.list(Wrappers.<FormSubmit>lambdaQuery().eq(FormSubmit::getFormId, formId));

		return Rsp.ok(list);
	}

	@GetMapping("/form/{formId}/action/{id}")
	public Rsp getAllActionDataByid(@PathVariable Long formId, @PathVariable Long id) {
		List<FormSubmit> list = formSubmitService
			.list(Wrappers.<FormSubmit>lambdaQuery().eq(FormSubmit::getFormId, formId).eq(FormSubmit::getId, id));

		return Rsp.ok(list);
	}

	@PutMapping("/form/{formId}/action/{id}")
	public Rsp modifyActionByObjidAndId(@PathVariable Long formId, @PathVariable Long id, Req<String> req) {
		formSubmitService.update(Wrappers.<FormSubmit>lambdaUpdate()
			.eq(FormSubmit::getFormId, formId)
			.eq(FormSubmit::getId, id)
			.set(FormSubmit::getContent, req.getContent()));

		return Rsp.ok();
	}

	@DeleteMapping("/form/{formId}/action/{id}")
	public Rsp deleteActionByObjidAndId(@PathVariable Long formId, @PathVariable Long id) {
		formSubmitService
			.remove(Wrappers.<FormSubmit>lambdaQuery().eq(FormSubmit::getFormId, formId).eq(FormSubmit::getId, id));

		return Rsp.ok();
	}

}
