/**
 * 
 */
package com.mingsoft.people.action.people;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.IPeopleBiz;
import com.mingsoft.basic.biz.IPeopleUserBiz;
import com.mingsoft.basic.entity.PeopleEntity;
import com.mingsoft.basic.entity.PeopleUserEntity;
import com.mingsoft.constant.ModelCode;
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
 * Comments:普通用户详细信息控制层(外部请求接口)
 * </p>
 *
 * <p>
 * Create Date:2014-11-1
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller("webPeopleUser")
@RequestMapping("/people/user")
public class PeopleUserAction extends BaseAction{
	
	/**
	 * 注入用户详细信息业务层
	 */
	@Autowired
	private IPeopleUserBiz peopleUserBiz;
	
	/**
	 * 注入用户业务层
	 */
	@Autowired
	private IPeopleBiz peopleBiz;
	
	/**
	 * 新增用户详细信息
	 * @param peopleUser 用户信息实体
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute PeopleUserEntity peopleUser,HttpServletRequest request,HttpServletResponse response){
		//获取用户session
		PeopleEntity people = this.getPeopleBySession(request);
		if(people == null){
			//当session不存在返回错误信息
			this.outJson(response,ModelCode.PEOPLE_USER,false,this.getResString("people.session.msg.null.error"), this.getResString("people.session.data.null.error"));
			return ;
		}
		
		if(peopleUser == null){
			//未填写信息返回错误信息
			this.outJson(response, ModelCode.PEOPLE_USER,false,this.getResString("people.user.msg.null.error"), getResString("people.user.data.null.error"));
			return ;
		}
		peopleUser.setPeopleAppId(people.getPeopleAppId());
		peopleUser.setPeopleId(people.getPeopleId());
		this.peopleUserBiz.saveEntity(peopleUser);
		//更新手机和电子邮件
		if(!StringUtil.isBlank(peopleUser.getPeopleMail())){
			people.setPeopleMail(peopleUser.getPeopleMail());
		}
		if(!StringUtil.isBlank(peopleUser.getPeoplePhone())){
			people.setPeoplePhone(peopleUser.getPeoplePhone());
		}
		this.peopleBiz.updateEntity(people);
		//返回用户添加成功
		this.outJson(response, ModelCode.PEOPLE_USER,true,this.getResString("people.user.save.msg.success"), getResString("people.user.save.data.success"));
	}
	
	/**
	 * 获取用户详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getEntity")
	public void getEntity(HttpServletRequest request,HttpServletResponse response){
		//获取用户session
		PeopleEntity people = this.getPeopleBySession(request);
		if(people == null){
			//当session不存在返回错误信息
			this.outJson(response,ModelCode.PEOPLE_USER,false,this.getResString("people.session.msg.null.error"), this.getResString("people.session.data.null.error"));
			return ;
		}
		
		PeopleUserEntity peopleUser = (PeopleUserEntity) this.peopleUserBiz.getEntity(people.getPeopleId());
		if(peopleUser == null){
			//没用用户详细信息
			this.outJson(response, ModelCode.PEOPLE_USER,false,this.getResString("people.user.msg.null.error"), getResString("people.user.data.null.error"));
			return ;
		}
		//返回用户详细信息
		this.outJson(response, ModelCode.PEOPLE_USER,true,null,JSONObject.toJSONString(peopleUser));
	}
	
	/**
	 * 用户更新详细信息
	 * @param peopleUser 用户信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/update")
	public void update(@ModelAttribute PeopleUserEntity peopleUser,HttpServletRequest request,HttpServletResponse response){
		//获取用户session
		PeopleEntity people = this.getPeopleBySession(request);
		if(people == null){
			//当session不存在返回错误信息
			this.outJson(response,ModelCode.PEOPLE_USER,false,this.getResString("people.session.msg.null.error"), this.getResString("people.session.data.null.error"));
			return ;
		}
		
		if(peopleUser == null){
			//未填写信息返回错误信息
			this.outJson(response, ModelCode.PEOPLE_USER,false,this.getResString("people.user.msg.null.error"), getResString("people.user.data.null.error"));
			return ;
		}
		
		peopleUser.setPeopleId(people.getPeopleId());
		this.peopleUserBiz.updatePeople(peopleUser);
		//返回更新成功
		this.outJson(response, ModelCode.PEOPLE_USER,true,this.getResString("people.user.update.msg.success"), getResString("people.user.update.data.success"));
	}
	
	
}
