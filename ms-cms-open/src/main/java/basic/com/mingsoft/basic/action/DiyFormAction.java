package com.mingsoft.basic.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.IDiyFormBiz;
import com.mingsoft.basic.biz.IDiyFormFieldBiz;
import com.mingsoft.basic.entity.DiyFormEntity;
import com.mingsoft.basic.entity.ManagerEntity;
import com.mingsoft.constant.SessionConst;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

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
 * Comments:通用自定义表单
 * </p>
 *
 * <p>
 * Create Date:2015-1-1
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller("diyForm")
@RequestMapping("/manager/diy/form")
public class DiyFormAction extends BaseAction{
	
	/**
	 * 自定义表前缀
	 */
	private static final String TABLE_NAME_PREFIX = "diy_";
	
	/**
	 * 表名分隔符
	 */
	private static final String TABLE_NAME_SPLIT= "_";
	
	/**
	 * 
	 */
	@Autowired
	IDiyFormBiz diyFormBiz;
	
	/**
	 * 
	 */
	@Autowired
	IDiyFormFieldBiz diyFormFieldBiz;
	/**
	 * 保存
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request)  {
		List list = diyFormBiz.query(this.getAppId(request));
		request.setAttribute("list", list);
		return "/manager/diy/form/diy_form_list";
	}
	
	/**
	 * 自定义表单的数据列表
	 */
	@RequestMapping("/{diyFormId}/query")
	public String query(@PathVariable("diyFormId") int diyFormId,HttpServletRequest request)  {
		// 当前页面
		int pageNo = 1;
		// 获取页面的当页数
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		int appId = this.getAppId(request);
		int count = diyFormBiz.countDiyFormData(diyFormId, appId);
		PageUtil page = new PageUtil(pageNo, 30,count,"/manager/diy/form/"+diyFormId+"/data.do");
		Map map = diyFormBiz.queryDiyFormData(diyFormId, appId, page);
		if (map!=null) {
			if (map.get("fields") != null) {
				request.setAttribute("fields", map.get("fields"));
			}
			if (map.get("list") != null) {
				request.setAttribute("list", map.get("list"));
			}			
		}
		
		request.setAttribute("title", request.getParameter("title"));
		request.setAttribute("page",page);
		return "/manager/diy/form/diy_form_data_list";
	}
	
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/{diyFormId}/{id}/delete")
	@ResponseBody
	public void delete(@PathVariable("id") int id,@PathVariable("diyFormId") int diyFormId,HttpServletRequest request,HttpServletResponse response)  {
		diyFormBiz.deleteQueryDiyFormData(id, diyFormId);
		this.outJson(response, null, true);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request) {
		return "/manager/diy/form/diy_form";
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/{diyFormId}/edit")
	public String edit(@PathVariable int diyFormId,ModelMap model){
		DiyFormEntity diyForm = (DiyFormEntity) this.diyFormBiz.getEntity(diyFormId);
		model.addAttribute("diyForm", diyForm);
		
		return "/manager/diy/form/diy_form";
	}
	
	/**
	 * 
	 * @param diyForm
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void  save(@ModelAttribute DiyFormEntity diyForm,HttpServletRequest request, HttpServletResponse response){
		// 更新前判断数据是否合法
		if(!StringUtil.checkLength(diyForm.getDiyFormTableName(), 1,20)){
				this.outJson(response, null, false,getResString("err.length",this.getResString("fieldTipsName"),"1","20"));
				return ;
		}
		if(!StringUtil.checkLength(diyForm.getDiyFormTipsName(), 1,20)){
				this.outJson(response, null, false,getResString("err.length",this.getResString("fieldFieldName"),"1","20"));
				return ;
		}
		
		// 获取当前管理员实体
		ManagerEntity managerSession = (ManagerEntity) getSession(request, SessionConst.MANAGER_ESSION);
		//获取当前管理员Id
		int managerId = managerSession.getManagerId();
		//获取appId
		int appId = getManagerBySession(request).getBasicId();
		//设置appId
		diyForm.setDiyFormAppId(appId);
		//设置管理员id
		diyForm.setDiyFormManagerId(managerId);
		//设置自定义表单的表面
		String tableName = TABLE_NAME_PREFIX+managerId+TABLE_NAME_SPLIT+diyForm.getDiyFormTableName();
		diyForm.setDiyFormTableName(tableName);
		
		//在数据库中创建自定义表单的表
		this.diyFormBiz.createDiyFormTable(diyForm.getDiyFormTableName(), null);
		//保存自定义表单实体
		diyFormBiz.saveEntity(diyForm);
		int diyFormId =  diyFormBiz.getByTableName(tableName).getDiyFormId();
		this.outJson(response, null, true,String.valueOf(diyFormId));
	}
	
	/**
	 * 
	 * @param diyForm
	 * @param response
	 */
	@RequestMapping("/update")
	public void update(@ModelAttribute DiyFormEntity diyForm, HttpServletResponse response){
		// 更新前判断数据是否合法
		if(!StringUtil.checkLength(diyForm.getDiyFormTableName(), 1,20)){
				this.outJson(response, null, false,getResString("err.length",this.getResString("fieldTipsName"),"1","20"));
				return ;
		}
		if(!StringUtil.checkLength(diyForm.getDiyFormTipsName(), 1,20)){
				this.outJson(response, null, false,getResString("err.length",this.getResString("fieldFieldName"),"1","20"));
				return ;
		}
		//更新自定义表单实体
		diyFormBiz.updateEntity(diyForm);
		this.outJson(response, null, true);
	}
	
	/**
	 * 
	 * @param diyFormTableName
	 * @param request
	 * @return
	 */
	@RequestMapping("/{diyFormTableName}/checkTableNameExist")
	public void checkTable(@PathVariable String diyFormTableName, HttpServletRequest request, HttpServletResponse response){
		// 获取当前管理员实体
		ManagerEntity managerSession = (ManagerEntity) getSession(request, SessionConst.MANAGER_ESSION);
		//获取当前管理员Id
		int managerId = managerSession.getManagerId();
		//组装表名
		diyFormTableName =TABLE_NAME_PREFIX+managerId+TABLE_NAME_SPLIT+diyFormTableName;
		DiyFormEntity diyForm = this.diyFormBiz.getByTableName(diyFormTableName);
		if(diyForm==null){
			this.outJson(response, null, false);
		}
		this.outJson(response, null, true);
	}
	
	/**
	 * 删除自定义表单
	 * @param cmId
	 * @param response
	 */
	@RequestMapping("/{diyFormId}/delete")
	public void delete(@PathVariable int diyFormId, HttpServletResponse response){
		
		//根据表单id查找表单实体
		DiyFormEntity diyForm = (DiyFormEntity) this.diyFormBiz.getEntity(diyFormId);
		if(diyForm==null){
			this.outJson(response, null, false,this.getResString("err.not.exist",this.getResString("diy.form")));
			return;
		}
		diyFormBiz.dropTable(diyForm.getDiyFormTableName());
		diyFormBiz.deleteEntity(diyFormId);
		this.outJson(response, null, true);
	}
}
