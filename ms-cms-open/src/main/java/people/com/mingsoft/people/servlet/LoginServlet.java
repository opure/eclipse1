package com.mingsoft.people.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.mingsoft.basic.biz.IPeopleBiz;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.basic.biz.IAppBiz;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.biz.IModelTemplateBiz;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.basic.entity.ModelTemplateEntity;
import com.mingsoft.basic.entity.PeopleEntity;
import com.mingsoft.basic.servlet.BaseServlet;
import com.mingsoft.cms.regex.GeneraterFactory;
import com.mingsoft.cms.regex.RegexConstant;
import com.mingsoft.constant.Const;
import com.mingsoft.constant.CookieConst;
import com.mingsoft.constant.ModelCode;
import com.mingsoft.constant.SessionConst;
import com.mingsoft.util.FileUtil;
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
 * @author 王天培 QQ:78750478
 * 
 *         <p>
 *         Comments:用户登陆servlet
 *         </p>
 * 
 *         <p>
 *         Create Date:2014-11-10
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
@WebServlet(urlPatterns = "/servlet/login")
public class LoginServlet extends BaseServlet {
	/**
	 * 生成器
	 */
	@Autowired
	private GeneraterFactory generaterFactory;

	/**
	 * 应用
	 */
	private IAppBiz appBiz;

	/**
	 * 模块模版
	 */
	private IModelTemplateBiz modelTemplateBiz;

	/**
	 * 模块R
	 */
	private IModelBiz modelBiz;
	
	/**
	 * 用户
	 */
	private IPeopleBiz peopleBiz;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding(Const.UTF8);
		appBiz = (IAppBiz) this.getBean(req.getServletContext(), "appBiz");
		modelTemplateBiz = (IModelTemplateBiz) this.getBean(req.getServletContext(), "modelTemplateBiz");
		modelBiz = (IModelBiz) this.getBean(req.getServletContext(), "modelBiz");
		generaterFactory = (GeneraterFactory) this.getBean(req.getServletContext(), "generaterFactory");

		AppEntity app = this.getApp(req, appBiz);
		if (app == null) {
			this.outString(resp, this.getResString("err"));
			return;
		}
		ModelTemplateEntity mte = modelTemplateBiz.getEntity(app.getAppId(), modelBiz.getEntityByModelCode(ModelCode.PEOPLE_LOGIN).getModelId(),"login");
		if (mte == null) {
			return;
		}
		String templatePath = mte.getModelTemplatePath();
		String path = getRealPath(req, RegexConstant.REGEX_SAVE_TEMPLATE) + File.separator + app.getAppId() + File.separator + app.getAppStyle() + File.separator;
		String content = "";
		if (this.isMobileDevice(req)) { // 移动端
			path = path + app.getAppMobileStyle() + File.separator;
		}
		String htmlContent = FileUtil.readFile(path + templatePath); // 读取模版文件内容
		content = generaterFactory.builder(app, null, htmlContent, path);
		this.outString(resp, content);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		appBiz = (IAppBiz) this.getBean(request.getServletContext(), "appBiz");
		String  userName = request.getParameter("userName");
		String  passWord = request.getParameter("passWord");
		
		// 根据应用ID和用户名查询用户密码
		AppEntity app = this.getApp(request, appBiz);
		if (app==null) {
			this.outString(response,this.getResString("err"));
		}
		// 用户名和密码不能为空
		if (StringUtil.isBlank(userName)) {
			this.outJson(response,ModelCode.PEOPLE_LOGIN, false, this.getResString("err.empty",this.getResString("people.username")));
			return;
		}
		if (StringUtil.isBlank(passWord)) {
			this.outJson(response,ModelCode.PEOPLE_LOGIN, false, this.getResString("err.empty",this.getResString("people.password")));
		}
	
		PeopleEntity peopleEntity = this.peopleBiz.getEntityByUserName(userName, app.getAppId());
		if (peopleEntity == null) {
			// 用户名或密码错误
			this.outJson(response,ModelCode.PEOPLE_LOGIN, false, this.getResString("err.not.exist","people"));
			return;
		}
		// 将用户输入的密码用MD5加密再和数据库中的进行比对
		String peoplePassWord = StringUtil.Md5(passWord);
		if (peoplePassWord.equals(peopleEntity.getPeoplePassword())) {
			// 登录成功,压入用户session
			this.setSession(request, SessionConst.PEOPLE_SESSION, peopleEntity);
			// 构建返回给页面的json
			Map<String, String> mapJson = new HashMap<String, String>();
			mapJson.put("userName", peopleEntity.getPeopleName());
			mapJson.put("phone", peopleEntity.getPeoplePhone());
			mapJson.put("mail", peopleEntity.getPeopleMail());
			// 将用户信息压入到cookie中
			this.setCookie(request, response, CookieConst.PEOPLE_COOKIE, JSONObject.toJSONString(mapJson));
			this.outJson(response, ModelCode.PEOPLE_LOGIN, true, JSONObject.toJSONString(mapJson), this.getResString("people.check.login.data.success"));
		} else {
			// 用户名或密码错误
			this.outJson(response, ModelCode.PEOPLE_LOGIN, false, this.getResString("people.msg.error"), this.getResString("people.data.error"));
		}
	}
}
