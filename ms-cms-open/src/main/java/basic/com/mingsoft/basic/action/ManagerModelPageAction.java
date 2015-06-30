package com.mingsoft.basic.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.IManagerModelPageBiz;
import com.mingsoft.basic.entity.DiyFormEntity;
import com.mingsoft.basic.entity.ManagerModelPageEntity;
import com.mingsoft.basic.entity.ManagerSessionEntity;

/**
 * 

 * <p>
 * <b>铭飞</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2013 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 史爱华
 * 
 * @version 100-000-000
 * 
 * <p>
 * 版权所有
 * </p>
 *  
 * <p>
 * Comments:管理员模块页面控制层||继承BaseAction
 * </p>
 *  
 * <p>
 * Create Date:2015-01-16
 * </p>
 *
 * <p>
 * Modification history:暂无
 * </p>
 */
@Controller
@RequestMapping("/manager/managerModelPage")
public class ManagerModelPageAction extends BaseAction{
	/**
	 * 注入业务层
	 */
	@Autowired
	private IManagerModelPageBiz managerModelPageBiz;
	
	/**
	 * 保存
	 * @param managerModelPage
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute ManagerModelPageEntity managerModelPage,HttpServletRequest request, HttpServletResponse response){
		//根据管理员id查找管理员模块页面实体对象
		ManagerModelPageEntity oldManagerModelPage =(ManagerModelPageEntity) managerModelPageBiz.getByManagerIdAndModelId(managerModelPage.getManagerModelPagemanagerId(),managerModelPage.getManagerModelPageModelId());
		//判断记录是否已经存在，如果存在则进行更新
		if(oldManagerModelPage!=null){
			managerModelPageBiz.updateEntity(managerModelPage);
			return;
		}
		managerModelPageBiz.saveEntity(managerModelPage);
		this.outJson(response, null, true);
	}
	
	/**
	 * 根据管理员id查找实体
	 * @param managerModelPagemanagerId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{managerModelPageModelId}/getEntity")
	public void getEntity(@PathVariable("managerModelPageModelId") int managerModelPageModelId,HttpServletRequest request, HttpServletResponse response){
		//获取
		ManagerSessionEntity managerSession = getManagerBySession(request);
		int managerId = managerSession.getManagerId();
		//根据管理员id查找管理员模块页面实体对象
		ManagerModelPageEntity managerModelPage =(ManagerModelPageEntity) managerModelPageBiz.getByManagerIdAndModelId(managerId,managerModelPageModelId);
		if(managerModelPage==null){
			this.outJson(response,null, false);
			return;
		}
		this.outJson(response,null, true,JSONObject.toJSONString(managerModelPage));
	}
}
