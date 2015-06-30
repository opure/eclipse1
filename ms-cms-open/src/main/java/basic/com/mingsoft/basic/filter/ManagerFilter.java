package com.mingsoft.basic.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingsoft.base.filter.BaseFilter;
import com.mingsoft.constant.SessionConst;

/**
 * 
 * <p>
 * <b>铭飞基础框架</b>
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
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科
 * </p>
 *  
 * <p>
 * Comments: 后台过滤器
 * </p>
 *  
 * <p>
 * Create Date:2014-5-16
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@WebFilter(filterName="managerFilter",urlPatterns="/manager/*")
public class ManagerFilter extends BaseFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Object mangerObj = httpRequest.getSession().getAttribute(SessionConst.MANAGER_ESSION.toString());
		try {
			if (mangerObj!=null) {
				chain.doFilter(request, response);
			} else {
				httpResponse.sendRedirect(request.getScheme() + "://"+ request.getServerName() + (request.getServerPort()==80?"":":"+request.getServerPort())+ httpRequest.getContextPath() + "/msadmin/login.do");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}


}
