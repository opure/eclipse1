package com.mingsoft.basic.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.IModelTemplateBiz;
import com.mingsoft.basic.entity.ModelTemplateEntity;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 *
 * @author 王天培
 *                QQ:78750478
 *
 * <p>
 * Comments:模块自定义页面
 * </p>
 *
 * <p>
 * Create Date:2014-11-21
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller
@RequestMapping("/manager/modeltemplate/")
public class ModelTemplateAction extends BaseAction {
	
	/**
	 * appBiz业务层的注入
	 */
	@Autowired
	private IModelTemplateBiz modelTemplateBiz;

	@RequestMapping(value="/list")
	public String list(ModelMap mode,HttpServletRequest request){
		List list = modelTemplateBiz.queryByAppId(this.getAppId(request));
		mode.addAttribute("list", list);
		return "/manager/model_template/model_template_list";
	}
	@RequestMapping(value="/add")
	public String add(){
		return "model_template_add";
	}
	@RequestMapping(value="/save")
	public void save(@ModelAttribute ModelTemplateEntity modelTemplate,HttpServletResponse response,HttpServletRequest request){
		modelTemplate.setModelTemplateAppId(this.getAppId(request));
		modelTemplateBiz.saveEntity(modelTemplate);
		this.outJson(response, null, true);
	}	
	@RequestMapping(value="/{id}/edit")
	public void edit(@PathVariable int id,HttpServletResponse response){
		ModelTemplateEntity modelTemplate = (ModelTemplateEntity) modelTemplateBiz.getEntity(id);
		this.outJson(response, null, true, null, JSONObject.toJSONString(modelTemplate));
	}	
	@RequestMapping(value="/{id}/update")
	public void update(@PathVariable int id,@ModelAttribute ModelTemplateEntity modelTemplate,HttpServletResponse response){
		modelTemplate.setModelTemplateId(id);
		modelTemplateBiz.updateEntity(modelTemplate);
		this.outJson(response, null, true);
	}		
	@RequestMapping(value="/{id}/delete")
	public void delete(@PathVariable int id,HttpServletResponse response){
		modelTemplateBiz.deleteEntity(id);
		this.outJson(response, null, true);
	}			
}
