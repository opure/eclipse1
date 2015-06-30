package com.mingsoft.people.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.IPeopleStudentBiz;
import com.mingsoft.basic.entity.PeopleEntity;
import com.mingsoft.basic.entity.PeopleStudentEntity;
import com.mingsoft.constant.ModelCode;
import com.mingsoft.util.PageUtil;
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
 * @author 史爱华
 *             
 *
 * <p>
 * Comments: 学生信息管理
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
@RequestMapping("/manager/people/student")
public class PeopleStudentAction extends BaseAction{
	
	/**
	 * 
	 */
	@Autowired
	private IPeopleStudentBiz peopleStudentBiz;
	
	@RequestMapping("/list")
	public String list(ModelMap mode,HttpServletRequest request,HttpServletResponse response){
		//获取应用ID
		int appId = this.getAppId(request);
		//查询用户总数
		int peopleCount = this.peopleStudentBiz.queryCountByAppId(appId);
		int pageNo = 1;
		if(request.getParameter("pageNo")!=null){
			pageNo=Integer.parseInt(request.getParameter("pageNo").toString());
		}
		//分页通用类
		PageUtil page=new PageUtil(pageNo,peopleCount,getUrl(request)+"/manager/people/student/list.do");
		List<PeopleStudentEntity> listPeopleStudent = this.peopleStudentBiz.queryListPageByAppId(appId, page);
		mode.addAttribute("listPeopleStudent", listPeopleStudent);
		mode.addAttribute("page", page);
		return "/manager/people/student/people_student_list";
	}
	
	/**
	 * 获取用户详细信息
	 * @param peopleId 用户ID
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{peopleId}/query")
	public void query(@PathVariable int peopleId,HttpServletRequest request,HttpServletResponse response){
		
		
		//获取用户实体信息
		PeopleStudentEntity peopleStudent = peopleStudentBiz.getPeopleStudent(peopleId);
		//判断用户是否存在
		if(peopleStudent==null){
				//返回错误信息
				this.outJson(response,ModelCode.PEOPLE,false,this.getResString("people.session.msg.null.error"));
				return ;
		}
		//返回用户详细信息
		this.outJson(response, ModelCode.PEOPLE_USER,true,null,JSONObject.toJSONString(peopleStudent));
	}
	/**
	 * 编辑用户信息
	 * @param peopleId 用户ID
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{peopleId}/edit")
	public String edit(@PathVariable int peopleId,HttpServletRequest request,HttpServletResponse response){
		//获取用户实体信息
		PeopleStudentEntity peopleStudent = peopleStudentBiz.getPeopleStudent(peopleId);
		request.setAttribute("peopleStudent", peopleStudent);
		return "/manager/people/student/people_student_edit";
	}	
	
	@RequestMapping("/update") 
	@ResponseBody
	public void update(@ModelAttribute PeopleStudentEntity peopleStudent,HttpServletRequest request,HttpServletResponse response){
		peopleStudentBiz.updatePeopleStudent(peopleStudent);
		this.outJson(response, true);
	}	
}
