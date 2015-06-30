/**
 * 
 */
package com.mingsoft.people.action.web;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.IPeopleBiz;
import com.mingsoft.basic.biz.IPeopleUserBiz;
import com.mingsoft.basic.constant.e.MailEnum;
import com.mingsoft.basic.entity.PeopleEntity;
import com.mingsoft.constant.CookieConst;
import com.mingsoft.constant.ModelCode;
import com.mingsoft.constant.SessionConst;
import com.mingsoft.people.constant.e.PeopleEnum;
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
 * @author 成卫雄 QQ:330216230
 * 
 *         <p>
 *         Comments:外部会员接口控制层
 *         </p>
 * 
 *         <p>
 *         Create Date:2014-10-31
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
@Controller("webPeople")
@RequestMapping("/")
public class PeopleAction extends BaseAction {

	/**
	 * 注入用户基础业务层
	 */
	@Autowired
	private IPeopleBiz peopleBiz;
	
	private IPeopleUserBiz peopleUserBiz;
	


	private String date = "{date/}";
	private String host = "{host/}";
	/**
	 * 前段会员中心所有页面都可以使用该方法 请求地址例如：　／{key}.do,例如登陆界面，与注册界面都可以使用
	 * 
	 * @param key
	 */
	@RequestMapping("/{key}.do")
	public void model(@PathVariable(value = "key") String key, HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> param = req.getParameterMap();
		String content = this.generaterPage(key, req);
		if (content == null) {
			this.outString(resp, this.getResString("err"));
			return;
		}

		// 将get或post提交过来的参数映射到界面上去
		for (Entry<String, String[]> entry : param.entrySet()) {
			String value = entry.getValue()[0]; // 处理由get方法请求中文乱码问题
			if (StringUtil.isBlank(value)) {
				continue;
			}
			if (req.getMethod().equals(RequestMethod.GET)) { // 如果是get方法需要将请求地址参数转吗
				value = StringUtil.isoToUTF8(value);
			}
			content = content.replace("{" + entry.getKey() + "/}", value);
		}
		//增加时间
		content =  content.replace(date, StringUtil.getDateSimpleStr());
		content =  content.replace(host, this.getApp(req).getAppHostUrl());
		this.outString(resp, content);
	}

	/**
	 * 用户注册
	 * 
	 * @param people
	 *            用户信息
	 * @param type
	 *            注册类型:</br> 1:根据用户名注册</br> 2:根据手机号注册</br> 3:根据邮箱注册</br>
	 * @param request
	 * @param response
	 */
	@RequestMapping("/{type}/register")
	public void register(@ModelAttribute PeopleEntity people, @PathVariable int type, HttpServletRequest request, HttpServletResponse response) {

		// 判断注册类型是否正确
		if (type < 1 || type > 3) {
			this.outJson(response, ModelCode.PEOPLE_REGISTER, false, this.getResString("people.register.msg.type.error"), this.getResString("people.register.data.type.error"));
			return;
		}

		// 判断用户信息是否为空
		if (people == null) {
			this.outJson(response, ModelCode.PEOPLE_REGISTER, false, this.getResString("people.msg.null.error"), this.getResString("people.msg.null.error"));
			return;
		}

		if (type == 1) {// 用户根据用户名注册
			// 验证用户名
			if (StringUtil.isBlank(people.getPeopleName()) || people.getPeopleName().length() < 3 || people.getPeopleName().length() > 12) {
				this.outJson(response, ModelCode.PEOPLE_REGISTER, false, this.getResString("people.msg.name.error"), this.getResString("people.data.name.error"));
				return;
			}

		} else if (type == 2) {// 用户根据手机号注册
			// 验证手机号
			if (StringUtil.isBlank(people.getPeoplePhone()) || !StringUtil.isMobile(people.getPeoplePhone())) {
				this.outJson(response, ModelCode.PEOPLE_REGISTER, false, this.getResString("people.msg.phone.error"), this.getResString("people.data.phone.error"));
				return;
			}

		} else if (type == 3) {// 用户根据邮箱注册
			// 验证邮箱
			if (StringUtil.isBlank(people.getPeopleMail()) || !StringUtil.isEmail(people.getPeopleMail())) {
				this.outJson(response, ModelCode.PEOPLE_REGISTER, false, this.getResString("people.msg.mail.error"), this.getResString("people.data.mail.error"));
				return;
			}
		}

		// 获取应用ID
		int appId = this.getAppId(request);

		// 验证用户信息,用户可使用:用户名,邮箱,手机号登录则必须保证这三个值在数据库中的唯一性
		if (!StringUtil.isBlank(people.getPeopleName())) {// 验证用户名
			PeopleEntity peopleName = this.peopleBiz.getEntityByUserName(people.getPeopleName(), appId);
			if (peopleName != null) {
				this.outJson(response, ModelCode.PEOPLE_REGISTER, false, this.getResString("people.register.msg.name.repeat.error"), this.getResString("people.register.data.name.repeat.error"));
				return;
			}
		}
		if (!StringUtil.isBlank(people.getPeoplePhone())) {// 验证手机号
			PeopleEntity peoplePhone = this.peopleBiz.getEntityByUserName(people.getPeoplePhone(), appId);
			if (peoplePhone != null) {
				this.outJson(response, ModelCode.PEOPLE_REGISTER, false, this.getResString("people.register.msg.phone.repeat.error"), this.getResString("people.register.data.phone.repeat.error"));
				return;
			}
		}
		if (!StringUtil.isBlank(people.getPeopleMail())) {// 验证邮箱
			PeopleEntity peopleMail = this.peopleBiz.getEntityByUserName(people.getPeopleMail(), appId);
			if (peopleMail != null) {
				this.outJson(response, ModelCode.PEOPLE_REGISTER, false, this.getResString("people.register.msg.mail.repeat.error"), this.getResString("people.register.data.mail.repeat.error"));
				return;
			}
		}

		// 验证密码
		if (StringUtil.isBlank(people.getPeoplePassword()) || people.getPeoplePassword().length() < 6 || people.getPeoplePassword().length() > 30) {
			this.outJson(response, ModelCode.PEOPLE_REGISTER, false, this.getResString("people.msg.password.error"), this.getResString("people.data.password.error"));
			return;
		}

		// 将密码使用MD5加密
		people.setPeoplePassword(StringUtil.Md5(people.getPeoplePassword()));
		people.setPeopleAppId(appId);
		people.setPeopleDateTime(new Date());
		this.peopleBiz.saveEntity(people);
		this.outJson(response, ModelCode.PEOPLE_REGISTER, true, this.getResString("people.register.msg.success"), this.getResString("people.register.data.success"));
	}

	/**
	 * 验证用户的登录信息
	 * 
	 * @param userName
	 *            用户名(可为:用户名称,手机,邮箱)
	 * @param passWord
	 *            密码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/checkLogin")
	public void checkLogin(@Param("userName") String userName, @Param("passWord") String passWord, HttpServletRequest request, HttpServletResponse response) {
		// 用户名和密码不能为空
		if (StringUtil.isBlank(userName) || StringUtil.isBlank(passWord)) {
			this.outJson(response, ModelCode.PEOPLE_LOGIN, false, this.getResString("people.msg.null.error"), this.getResString("people.data.null.error"));
			return;
		}

		// 根据应用ID和用户名查询用户密码
		int appId = this.getAppId(request);
		PeopleEntity peopleEntity = this.peopleBiz.getEntityByUserName(userName, appId);
		if (peopleEntity == null) {
			// 用户名或密码错误
			this.outJson(response, ModelCode.PEOPLE_LOGIN, false, this.getResString("people.msg.error"), this.getResString("people.data.error"));
			return;
		}
		// 将用户输入的密码用MD5加密再和数据库中的进行比对
		String peoplePassWord = StringUtil.Md5(passWord);
		if (peoplePassWord.equals(peopleEntity.getPeoplePassword())) {
			// 登录成功,压入用户session
			this.setSession(request, SessionConst.PEOPLE_SESSION, peopleEntity);
			// 构建返回给页面的json
			Map<String, String> mapJson = new HashMap<String, String>();
			mapJson.put("userName", StringUtil.encodeStringByUTF8(peopleEntity.getPeopleName()));
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

	@RequestMapping("/changePassWord")
	public void changePassword( @Param("passWord") String passWord, @Param("newPassWord") String newPassWord, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtil.isBlank(passWord) || StringUtil.isBlank(newPassWord)) {
			// 用户或密码不能为空
			this.outJson(response, ModelCode.PEOPLE, false, this.getResString("people.msg.null.error"), this.getResString("people.data.null.error"));
			return;
		}

		// 验证新密码的长度
		if (newPassWord.length() < 6 || newPassWord.length() > 30) {
			this.outJson(response, ModelCode.PEOPLE, false, this.getResString("people.msg.password.error"), this.getResString("people.data.password.error"));
			return;
		}

		//获取用户session
		PeopleEntity people =this.getPeopleBySession(request);
		if (people == null) {
			// 用户名或密码错误
			this.outJson(response, ModelCode.PEOPLE,false,this.getResString("people.session.msg.null.error"), this.getResString("people.session.data.null.error"));
			return;
		}
		// 将用户输入的原始密码用MD5加密再和数据库中的进行比对
		String peoplePassWord = StringUtil.Md5(passWord);
		if (people.getPeoplePassword().equals(peoplePassWord)) {// 验证通过执行修改
			// 将新密码用MD5加密
			newPassWord = StringUtil.Md5(newPassWord);
			// 执行修改
			people.setPeoplePassword(newPassWord);
			this.peopleBiz.updateEntity(people);
			this.outJson(response, ModelCode.PEOPLE, true, this.getResString("people.change.password.msg.success"), this.getResString("people.change.password.data.success"));
		} else {
			// 用户名或密码错误
			this.outJson(response, ModelCode.PEOPLE, false, this.getResString("people.msg.error"), this.getResString("people.data.error"));
		}
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/quit")
	public void quit(HttpServletRequest request, HttpServletResponse response) {
		// 移除当前用户session
		this.removeSession(request, SessionConst.PEOPLE_SESSION);
		this.setCookie(request, response, CookieConst.PEOPLE_COOKIE, null);
		this.outJson(response, ModelCode.PEOPLE, true, this.getResString("people.quit.msg.success"), this.getResString("people.quit.data.success"));
	}


	
	/**
	 * 给新手机号码发送验证码
	 * @param request
	 * @param response
	 * @param peoplePhone
	 */
	@RequestMapping("/sendNewPhone")
	public void sendNewPhone(HttpServletRequest request, HttpServletResponse response, @Param("peoplePhone") String peoplePhone){
		// 获取appID
		int appId = this.getAppId(request);
		//判断新手机是否注册过
		if(!StringUtil.isBlank(peoplePhone)){
			//根据手机号码查找实体
			PeopleEntity newPeople = this.peopleBiz.getEntityByUserName(peoplePhone,appId);
			if(newPeople != null){
				this.outJson(response, ModelCode.PEOPLE, false, this.getResString("people.register.msg.phone.repeat.error"),this.getResString("people.register.data.phone.repeat.error"));
				return ;
			}	
		}
		//获取用户session
	    PeopleEntity people =this.getPeopleBySession(request);
	    //如果用户还没有登录
		if(people==null){
				this.outJson(response, ModelCode.PEOPLE,false,this.getResString("people.session.msg.null.error"), this.getResString("people.session.data.null.error"));
				return;
		}

		String peopelCode = StringUtil.randomNumber(6);
		people.setPeopleCode(peopelCode);
		people.setPeoplePhoneCheck(PeopleEnum.PHONE_NO_CHECK);
		peopleBiz.updateEntity(people);
	
	}
	

	/**
	 * 通过用户输入的验证码和手机号码判断用户是否存在查找用户实体
	 * 
	 * @param request
	 * @param response
	 * @param peopleCode
	 *            验证码
	 */
	@RequestMapping("/getPeopleByCode")
	public void getPeopleByCode(HttpServletRequest request, HttpServletResponse response, @Param("peopleCode") String peopleCode, @Param("peoplePhone") String peoplePhone) {
		
		int appId = this.getAppId(request);
		PeopleEntity people = this.peopleBiz.getEntityByUserName(peoplePhone, appId);
		if (people == null) {
			this.outJson(response, ModelCode.PEOPLE, false,this.getResString("err.not.exist",this.getResString("people")));
			return;
		} 
		//判断用户输入的随机码是否正确
		if(!people.getPeopleCode().equals(peopleCode)){
			this.outJson(response, ModelCode.PEOPLE, false,this.getResString("err.error",this.getResString("peopleCode")));
			return;
		}
		people.setPeoplePhoneCheck(PeopleEnum.PHONE_CHECK);
		peopleBiz.updateEntity(people);
		this.outJson(response, ModelCode.PEOPLE, true);
	}

	/**
	 * 发送取回密码的连接地址
	 * 
	 * @param request
	 * @param peopleMail 用户接收邮箱地址
	 * @param url 取回密码的页面连接地址，
	 */
	@RequestMapping("/sendGetPasswordUrl")
	public void sendGetPasswordUrl(HttpServletRequest request, @Param("peopleMail") String peopleMail, @Param("url") String url, @Param("imgCode") String imgCode,HttpServletResponse response) {
		// 根据appId
		int appId = this.getAppId(request);
		//验证用户输入的验证码是否正确
	
		if(!StringUtil.isBlank(imgCode) && !imgCode.equalsIgnoreCase(this.getCodeBySession(request))){
			this.outJson(response, ModelCode.PEOPLE_REGISTER,false,this.getResString("err.error",getResString("peopleImageCode")));
			return;
		}
		//判断该邮箱是否注册过
		PeopleEntity people = peopleBiz.getEntityByUserName(peopleMail, appId);
		if(people==null){
			this.outJson(response, ModelCode.PEOPLE, false,this.getResString("err.not.exist",this.getResString("people")));
			return;
		}
		
		// 1生成session 值，
		String code = StringUtil.getDateSimpleStr();
		this.setSession(request, SessionConst.PEOPLE_GET_PASSWORD_SESSION, code);
		String content = StringUtil.buildUrl(url ,"code=" + code);
		this.sendMail(request, MailEnum.TEXT, this.getResString("people.get.password"), StringUtil.buildUrl(content ,"mail=" + peopleMail), new String[] { peopleMail });
		this.outJson(response, ModelCode.PEOPLE, true);
	}

	/**
	 * 用户找回密码时，修改密码
	 * 
	 * @param request
	 * @param response
	 * @param peopleMail
	 *            ：邮箱
	 */
	@RequestMapping("/setPassword")
	@ResponseBody
	public void setPassword(HttpServletRequest request, HttpServletResponse response, @Param("peopleMail") String peopleMail, @Param("passWord") String passWord) {
		String code = request.getParameter("code");
		String errUrl = request.getParameter("errUrl"); // 错误返回地址
		Object scode = this.getSession(request, SessionConst.PEOPLE_GET_PASSWORD_SESSION);
		if (scode == null || !code.equals(scode.toString())) {
			this.outJson(response, ModelCode.PEOPLE, false, null, errUrl);
			return;
		}
		// 根据邮箱获取用户实体
		int appId = this.getAppId(request);
		PeopleEntity people = this.peopleBiz.getEntityByUserName(peopleMail, appId);
		// 验证新密码的长度
		if (passWord.length() < 6 || passWord.length() > 30) {
			this.outJson(response, ModelCode.PEOPLE, false, this.getResString("people.msg.password.error"), this.getResString("people.data.password.error"));
			return;
		}

		// 将新密码用MD5加密
		passWord = StringUtil.Md5(passWord);
		if (people != null) {
			// 设置用户新密码
			people.setPeoplePassword(passWord);
			peopleBiz.updateEntity(people);
			this.outJson(response, ModelCode.PEOPLE, true, this.getResString("people.change.password.msg.success"), this.getResString("people.change.password.data.success"));
		} else {
			// 用户不存在
			this.outJson(response, ModelCode.PEOPLE, false,this.getResString("err.not.exist",this.getResString("people")));
			return;
		}
	}
	
	/**
	 * 更改手机号码
	 * @param peoplePhone
	 * @param request
	 * @param response
	 */
	@RequestMapping("/changePhone")
	public void changePhone(@Param("newPhone") String newPhone,@Param("peopleCode") String peopleCode,HttpServletRequest request,HttpServletResponse response){
		//验证原始帐号密码
		int appId = this.getAppId(request);
		//获取用户session
		PeopleEntity people =this.getPeopleBySession(request);
		//如果用户还没有登录
		if(people==null){
			this.outJson(response, ModelCode.PEOPLE,false,this.getResString("people.session.msg.null.error"), this.getResString("people.session.data.null.error"));
			return;
		}
		//验证用户输入的验证码是否正确
		String imgCode = request.getParameter("imgCode");
		if(!imgCode.equalsIgnoreCase(this.getCodeBySession(request))){
			this.outJson(response, ModelCode.PEOPLE_REGISTER,false,this.getResString("err.error",getResString("peopleImageCode")));
			return;
		}
		//判断手机是否已经存在
		if(!StringUtil.isBlank(people.getPeoplePhone()) && !people.getPeoplePhone().equals(newPhone)){
			//根据手机号码查找实体
			PeopleEntity newPeople = this.peopleBiz.getEntityByUserName(newPhone,appId);
			if(newPeople != null){
				this.outJson(response, ModelCode.PEOPLE_REGISTER, false, this.getResString("people.register.msg.phone.repeat.error"),this.getResString("people.register.data.phone.repeat.error"));
				return ;
			}	
		}
		//判断用户输入的验证是否正确
		if(!people.getPeopleCode().equals(peopleCode)){
			//返回错误信息
			this.outJson(response, ModelCode.PEOPLE_REGISTER, false,this.getResString("err.error", this.getResString("peopleCode")));
			return ;
		}
		people.setPeoplePhone(newPhone);
		peopleBiz.updateEntity(people);
		this.outJson(response, ModelCode.PEOPLE,true);
		
	}

}
