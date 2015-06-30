/**
 * 
 */
package com.mingsoft.people.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.IPeopleBiz;
import com.mingsoft.basic.biz.IPeopleUserBiz;
import com.mingsoft.basic.entity.PeopleEntity;
import com.mingsoft.basic.entity.PeopleUserEntity;
import com.mingsoft.constant.ModelCode;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

/**
 * 
 * <p>
 * <b>铭飞科技-会员系统</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 *
 * @author 成卫雄
 *                QQ:330216230
 *
 * <p>
 * Comments: 普通用户信息
 * </p>
 *
 * <p>
 * Create Date:2014-10-31
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller
@RequestMapping("/manager/people/user")
public class PeopleUserAction extends BaseAction{
	
	/**
	 * 注入普通用户控制层
	 */
	@Autowired
	private IPeopleUserBiz peopleUserBiz;
	
	/**
	 * 注入用户基础信息控制层
	 */
	@Autowired
	private IPeopleBiz peopleBiz;

	/**
	 * 用户列表
	 * @param mode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String list(ModelMap mode,HttpServletRequest request,HttpServletResponse response){
		//获取应用ID
		int appId = this.getAppId(request);
		//查询用户总数
		int peopleCount = this.peopleBiz.queryCountByAppId(appId);
		int pageNo = 1;
		if(request.getParameter("pageNo")!=null){
			pageNo=Integer.parseInt(request.getParameter("pageNo").toString());
		}
		//分页通用类
		PageUtil page=new PageUtil(pageNo,peopleCount,getUrl(request)+"/manager/people/user/list.do");
		
		List<PeopleEntity> listPeople = this.peopleBiz.queryPageListByAppId(appId,page);
		mode.addAttribute("listPeople", listPeople);
		mode.addAttribute("page", page);
		return "/manager/people/user/people_user_list";
	}
	
	/**
	 * 新增用户信息
	 * @return 新增用户页面
	 */
	@RequestMapping("/add")
	public String add(){
		return "/manager/people/user/people_user_form";
	}
	
	
	/**
	 * 获取用户详细信息
	 * @param peopleId 用户ID
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getEntity")
	public void getEntity(@Param("peopleId") String peopleId,HttpServletRequest request,HttpServletResponse response){
		if(StringUtil.isBlank(peopleId) || !StringUtil.isInteger(peopleId)){
			this.outJson(response, ModelCode.PEOPLE_USER,false);
			return ;
		}
		PeopleUserEntity peopleUser = (PeopleUserEntity) this.peopleUserBiz.getEntity(Integer.parseInt(peopleId));
		if(peopleUser == null){
			this.outJson(response, ModelCode.PEOPLE_USER,false);
			return ;
		}
		this.outJson(response, ModelCode.PEOPLE_USER,true,null,JSONObject.toJSONString(peopleUser));
	}
	
}
