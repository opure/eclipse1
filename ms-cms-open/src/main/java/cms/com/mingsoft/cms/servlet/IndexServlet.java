package com.mingsoft.cms.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingsoft.basic.biz.IAppBiz;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.basic.servlet.BaseServlet;
import com.mingsoft.cms.regex.RegexConstant;
import com.mingsoft.util.FileUtil;
import com.mingsoft.util.RegexUtil;
import com.mingsoft.util.StringUtil;

/**
 * CMS系统分发serlvet,根据地址栏判断网站来源,
 * 注意：每个网站的静态文件必须加上网站绝对地址。例如：http://www.abc.com/html/abc.html
 */

@WebServlet("/index")
public class IndexServlet extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String MOBILE_PATH = "m";
	
	/**
	 * 注入站点业务层
	 */
	private IAppBiz appBiz;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取用户所请求的域名地址

		appBiz = (IAppBiz) getBean(request.getServletContext(), "appBiz");
		//查询数据库获取域名对应Id
		int websiteId = 0;
		AppEntity website = appBiz.getByUrl(getDomain(request));
		if(website!=null){
			websiteId = website.getAppId();
		} else {
			this.outString(response, this.getResString("err"));
			return ;
		}
		String path = "";
		
		if (!StringUtil.isBlank(website.getAppMobileStyle())) {
			path = isMobileDevice(request)?MOBILE_PATH:""; //如果是手机访问就跳转到相应页面	
		}
		
		String defaultHtmlPath = this.getRealPath(request, RegexConstant.HTML_SAVE_PATH+File.separator+websiteId+File.separator+path+File.separator+"default.html");
		File file = new File(defaultHtmlPath);
		String indexPosition =RegexConstant.HTML_SAVE_PATH+File.separator+websiteId+File.separator+path+File.separator+"index.html";
		if (file.exists()) {
			 indexPosition =RegexConstant.HTML_SAVE_PATH+File.separator+websiteId+File.separator+path+File.separator+"default.html";
		}
		//转发到网站首页
		request.getRequestDispatcher(indexPosition).forward(request, response);
		
	}
	
}
