/**
 * 
 */
package com.mingsoft.basic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingsoft.util.StringUtil;
import com.mingsoft.util.proxy.Header;
import com.mingsoft.util.proxy.Proxy;
import com.mingsoft.util.proxy.Result;

/**
 * 
 * <p>
 * <b>铭飞科技-代理(通用)</b>
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
 * Comments:发送请求的代理的servlet</br>
 * 					发送GET请求和POST请求,支持https</br>
 * 					携带参数说明</br>
 * 							url:请求地址(带http,必须);</br>
 * 							hostUrl：请求主机;</br>
 * 							reqCoding:发送请求的编码,默认为UTF8;</br>
 * 							headerCoding:头部请求编码,默认为UTF8;</br>
 * 							contentCoding:接收返回值的编码,默认为UTF-8;</br>
 * 							cookie:发送请求携带的COOKIE;</br>
 * 							headerStr:自定义头部携带参数(每组参数之间用"|"隔开,等号两边为参数名和参数值,如：name=cwx|age=20);</br>
 * 							params:自定义请求参数(每组参数之间用"|"隔开,等号两边为参数名和参数值,如：name=cwx|age=20);</br> 
 * </p>
 *
 * <p>
 * Create Date:2014-10-21
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@WebServlet(urlPatterns = "/proxy")
public class ProxyServlet extends BaseServlet{
	
	/**
	 * 代理发送GET请求
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.proxy(request, response, true);
	}
	
	/**
	 * 代理发送PSOT请求
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.proxy(request, response, false);
	}
	
	/**
	 * 发送代理请求</br>
	 * 请求参数:</br>
	 * 		url:请求地址(带http,必须);</br>
	 * 		hostUrl：请求主机;</br>
	 * 		charset:发送请求的编码,默认为UTF8;</br>
	 * 		cookie:发送请求携带的COOKIE;</br>
	 * 		headerStr:自定义头部携带参数(每组参数之间用"|"隔开,等号两边为参数名和参数值,如：name@cwx|age@20);</br>
	 * 		params:自定义请求参数(每组参数之间用"|"隔开,等号两边为参数名和参数值,如：name@cwx|age@20);</br>
	 * @param request
	 * @param response
	 * @param way 请求方式,true:发送GET请求,false:发送POST请求
	 * @throws IOException 
 	 */
	private void proxy(HttpServletRequest request, HttpServletResponse response,Boolean way) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try{
			//请求地址
			String url = request.getParameter("url");
			if(StringUtil.isBlank(url)){
				//请求地址不能为空
				out.write("url err");
				return;
			}
			
			//请求主机地址
			String hostUrl = request.getParameter("hostUrl");
			
			//头部请求编码默认为UTF8
			String charset = request.getParameter("charset");
			if(StringUtil.isBlank(charset)){
				charset = "utf-8";
			}
			
			//请求头部信息
			Header header = new Header(hostUrl,charset);
			
			//cookie
			String cookie = request.getParameter("cookie");
			if(!StringUtil.isBlank(cookie)){
				header.setCookie(cookie);
			}
			
			
			//自定义头部携带参数(每组参数之间用"|"隔开,等号两边为参数名和参数值,如：name=cwx|age=20)
			String headerStr = request.getParameter("headerStr");
			if(!StringUtil.isBlank(headerStr)){
				String[] _headerStr = headerStr.split("\\|");
				for(int i=0;i<_headerStr.length;i++){
					String str = _headerStr[i];
					if(StringUtil.isBlank(str.split("@")[0]) || StringUtil.isBlank(str.split("@")[0])){
						//返回自定义头部请求参数错误
						out.write("header parameter err");
						return ;
					}else{
						header.setHeader(str.split("@")[0], str.split("@")[1]);
					}
				}
			}
	
			//自定义请求参数(每组参数之间用"|"隔开,等号两边为参数名和参数值,如：name=cwx|age=20)
			String params = request.getParameter("params");
			Map<String,String> paramsMap = null;
			if(!StringUtil.isBlank(params)){
				paramsMap = new HashMap<String,String>();
				String[] _params = params.split("\\|");
				for(int i=0;i<_params.length;i++){
					String str = _params[i];
					if(StringUtil.isBlank(str.split("@")[0]) || StringUtil.isBlank(str.split("@")[0])){
						//返回自定义参数参数错误
						out.write("parameter err");
						return ;
					}else{
						paramsMap.put(str.split("@")[0], str.split("@")[1]);
					}
				}			
			}
			Result result   =  way?Proxy.get(url,header, paramsMap,charset): Proxy.post(url,header, paramsMap,charset);
			String resultContent = result.getContent(charset);
			if(result != null && !StringUtil.isBlank(resultContent)){
				out.print(resultContent);
			}
		}catch (Exception e) {
			out.write("request err");
			return ;			
		}
		
		out.flush();
		out.close();
	}
	
	
}
