package com.mingsoft.basic.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mingsoft.constant.Const;
import com.mingsoft.constant.SessionConst;
import com.mingsoft.util.StringUtil;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技基础框架</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author wangtp
 * 
 * @version 100-000-000
 * 
 * <p>
 * 版权所有
 * </p>
 *  
 * <p>
 * Comments:所有action的拦截器，主要是设置base与basepath
 * </p>
 *  
 * <p>
 * Create Date:2014-6-29
 * </p>
 *
 * <p>
 * Modification history:暂无
 * </p>
 */
public class ManagerInterceptor extends  HandlerInterceptorAdapter {
    
    protected Logger logger = Logger.getLogger(this.getClass());
    

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    	String modelId = request.getParameter("modelId"); //获取模块编号
//    	if (StringUtil.isInteger(modelId)) {
//    		request.getSession().setAttribute(SessionConst.MODEL_ID_SESSION.toString(), modelId);
//    		request.getSession().setAttribute(SessionConst.MODEL_TITLE_SESSION.toString(), request.getParameter("modelTitle"));
//    	}
//        request.setAttribute(BASE, Const.BASE);
//        request.setAttribute(BASE_PATH,request.getScheme() + "://"+ request.getServerName() + (request.getServerPort()==80?"":":"+request.getServerPort())+ Const.BASE);
        return true; 
	}
	
	

}
