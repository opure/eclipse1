package com.mingsoft.people.action.people;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mingsoft.base.action.BaseAction;
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
 * Comments:前端会员主界面
 * </p>
 *
 * <p>
 * Create Date:2014-11-10
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller("webPeopleMain")
@RequestMapping("/people")
public class PeopleAction extends BaseAction{

	/**
	 * 便于页面随机与时间控制
	 */
	private String date = "{date/}"; 
	
	/**
	 * 用户编号
	 */
	private static final String PEOPLEID = "{peopleId/}";
	private static final String HOST = "{host/}";
	/**
	 * 前段会员中心所有页面都可以使用该方法
	 * 支持参数传递与解析，例如页面中有参数id=10 传递过来，调整页面可以使用{id/}获取该参数
	 * 请求地址例如：　／people/info.do;people/password.do
	 * @param key
	 */
	@RequestMapping("/{key}.do")
	public void model(@PathVariable(value="key")String key,HttpServletRequest req,HttpServletResponse resp) {
		Map<String, String[]> param = req.getParameterMap();
		String content=  this.generaterPage("people/"+key, req);
		if (content == null) {
			this.outString(resp, this.getResString("err"));
			return;
		}
		
		//将get或post提交过来的参数映射到界面上去
		for (Entry<String, String[]> entry : param.entrySet())  {
			String value = entry.getValue()[0]; // 处理由get方法请求中文乱码问题
			if (StringUtil.isBlank(value)) {
				continue;
			}
			if (req.getMethod().equals(RequestMethod.GET)) { // 如果是get方法需要将请求地址参数转吗
				value = StringUtil.isoToUTF8(value);
			}
			content = content.replace("{"+entry.getKey()+"/}", value);
		}
		
		//增加时间
		content =  content.replace(date, StringUtil.getDateSimpleStr());
		try {
			content =  content.replace(PEOPLEID,this.getPeopleBySession(req).getPeopleId()+"");
		} catch(NullPointerException e) {
			LOG.error("user is not login", e);
		}
		content =  content.replace(HOST, this.getApp(req).getAppHostUrl());

		this.outString(resp, content);
	}
	
	
	
	
	
}
