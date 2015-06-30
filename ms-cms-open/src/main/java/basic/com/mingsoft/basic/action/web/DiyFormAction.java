package com.mingsoft.basic.action.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.IDiyFormBiz;
import com.mingsoft.constant.SessionConst;
import com.mingsoft.util.Base64Util;

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
@Controller("webDiyForm")
@RequestMapping("/from")
public class DiyFormAction extends BaseAction{

	@Autowired
	IDiyFormBiz diyFormBiz;
	
	/**
	 * 保存
	 */
	@RequestMapping("{idBase64}")
	@ResponseBody
	public void save(@PathVariable("idBase64") String idBase64,HttpServletRequest request,HttpServletResponse response) {
			String temp = new String(Base64Util.decode(idBase64));
			String code = request.getParameter("code");
			Object obj = this.getSession(request, SessionConst.CODE_SESSION);
			if (obj!=null) {
				String _code = obj.toString();
				if (!_code.equalsIgnoreCase(code)) {
					this.outJson(response, null, false);
				}
			}
			int  formId = Integer.parseInt(temp);
			if (formId  !=0 ) {
				LOG.debug("fromId:" + formId);
				diyFormBiz.saveDiyFormData(formId,assemblyRequestMap(request));
				this.outJson(response, null, true);
			} 
	}
	
}
