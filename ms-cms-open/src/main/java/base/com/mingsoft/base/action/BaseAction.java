package com.mingsoft.base.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.entity.ResultJson;
import com.mingsoft.basic.biz.IAppBiz;
import com.mingsoft.basic.biz.IMailBiz;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.constant.e.BaseEnum;
import com.mingsoft.basic.constant.e.MailEnum;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.basic.entity.MailEntity;
import com.mingsoft.basic.entity.ManagerSessionEntity;
import com.mingsoft.basic.entity.ModelEntity;
import com.mingsoft.basic.entity.ModelTemplateEntity;
import com.mingsoft.basic.entity.PeopleEntity;
import com.mingsoft.cms.regex.RegexConstant;
import com.mingsoft.constant.Const;
import com.mingsoft.constant.CookieConst;
import com.mingsoft.constant.ModelCode;
import com.mingsoft.constant.SessionConst;
import com.mingsoft.util.Base64Util;
import com.mingsoft.util.FileUtil;
import com.mingsoft.util.MailUtil;
import com.mingsoft.util.StringUtil;

/**
 * 
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
 *          <p>
 *          版权所有
 *          </p>
 * 
 *          <p>
 *          Comments:控制
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-6-27
 *          </p>
 * 
 *          <p>
 *          Modification history:暂无
 *          </p>
 */
public abstract class BaseAction {

	protected final  Logger LOG = Logger.getLogger(this.getClass());

	/** Wap网关Via头信息中特有的描述信息 */
	private static String mobileGateWayHeaders[] = new String[] { "ZXWAP",// 中兴提供的wap网关的via信息，例如：Via=ZXWAP
																			// GateWayZTE
																			// Technologies，
			"chinamobile.com",// 中国移动的诺基亚wap网关，例如：Via=WTP/1.1
								// GDSZ-PB-GW003-WAP07.gd.chinamobile.com (Nokia
								// WAP Gateway 4.1 CD1/ECD13_D/4.1.04)
			"monternet.com",// 移动梦网的网关，例如：Via=WTP/1.1
							// BJBJ-PS-WAP1-GW08.bj1.monternet.com. (Nokia WAP
							// Gateway 4.1 CD1/ECD13_E/4.1.05)
			"infoX",// 华为提供的wap网关，例如：Via=HTTP/1.1 GDGZ-PS-GW011-WAP2 (infoX-WISG
					// Huawei Technologies)，或Via=infoX WAP Gateway V300R001
					// Huawei Technologies
			"XMS 724Solutions HTG",// 国外电信运营商的wap网关，不知道是哪一家
			"wap.lizongbo.com",// 自己测试时模拟的头信息
			"Bytemobile",// 貌似是一个给移动互联网提供解决方案提高网络运行效率的，例如：Via=1.1 Bytemobile OSN
							// WebProxy/5.1
	};
	/** 电脑上的IE或Firefox浏览器等的User-Agent关键词 */
	private static String[] pcHeaders = new String[] { "Windows 98", "Windows ME", "Windows 2000", "Windows XP", "Windows NT", "Ubuntu" };
	/** 手机浏览器的User-Agent里的关键词 */
	private static String[] mobileUserAgents = new String[] { "Nokia",// 诺基亚，有山寨机也写这个的，总还算是手机，Mozilla/5.0
																		// (Nokia5800
																		// XpressMusic)UC
																		// AppleWebkit(like
																		// Gecko)
																		// Safari/530
			"SAMSUNG",// 三星手机
						// SAMSUNG-GT-B7722/1.0+SHP/VPP/R5+Dolfin/1.5+Nextreaming+SMM-MMS/1.2.0+profile/MIDP-2.1+configuration/CLDC-1.1
			"MIDP-2",// j2me2.0，Mozilla/5.0 (SymbianOS/9.3; U; Series60/3.2
						// NokiaE75-1 /110.48.125 Profile/MIDP-2.1
						// Configuration/CLDC-1.1 ) AppleWebKit/413 (KHTML like
						// Gecko) Safari/413
			"CLDC1.1",// M600/MIDP2.0/CLDC1.1/Screen-240X320
			"SymbianOS",// 塞班系统的，
			"MAUI",// MTK山寨机默认ua
			"UNTRUSTED/1.0",// 疑似山寨机的ua，基本可以确定还是手机
			"Windows CE",// Windows CE，Mozilla/4.0 (compatible; MSIE 6.0;
							// Windows CE; IEMobile 7.11)
			"iPhone",// iPhone是否也转wap？不管它，先区分出来再说。Mozilla/5.0 (iPhone; U; CPU
						// iPhone OS 4_1 like Mac OS X; zh-cn) AppleWebKit/532.9
						// (KHTML like Gecko) Mobile/8B117
			"iPad",// iPad的ua，Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X;
					// zh-cn) AppleWebKit/531.21.10 (KHTML like Gecko)
					// Version/4.0.4 Mobile/7B367 Safari/531.21.10
			"Android",// Android是否也转wap？Mozilla/5.0 (Linux; U; Android
						// 2.1-update1; zh-cn; XT800 Build/TITA_M2_16.22.7)
						// AppleWebKit/530.17 (KHTML like Gecko) Version/4.0
						// Mobile Safari/530.17
			"BlackBerry",// BlackBerry8310/2.7.0.106-4.5.0.182
			"UCWEB",// ucweb是否只给wap页面？ Nokia5800
					// XpressMusic/UCWEB7.5.0.66/50/999
			"ucweb",// 小写的ucweb貌似是uc的代理服务器Mozilla/6.0 (compatible; MSIE 6.0;)
					// Opera ucweb-squid
			"BREW",// 很奇怪的ua，例如：REW-Applet/0x20068888 (BREW/3.1.5.20; DeviceId:
					// 40105; Lang: zhcn) ucweb-squid
			"J2ME",// 很奇怪的ua，只有J2ME四个字母
			"YULONG",// 宇龙手机，YULONG-CoolpadN68/10.14 IPANEL/2.0 CTC/1.0
			"YuLong",// 还是宇龙
			"COOLPAD",// 宇龙酷派YL-COOLPADS100/08.10.S100 POLARIS/2.9 CTC/1.0
			"TIANYU",// 天语手机TIANYU-KTOUCH/V209/MIDP2.0/CLDC1.1/Screen-240X320
			"TY-",// 天语，TY-F6229/701116_6215_V0230 JUPITOR/2.2 CTC/1.0
			"K-Touch",// 还是天语K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223
						// Release/30.07.2008 Browser/WAP2.0
			"Haier",// 海尔手机，Haier-HG-M217_CMCC/3.0 Release/12.1.2007
					// Browser/WAP2.0
			"DOPOD",// 多普达手机
			"Lenovo",// 联想手机，Lenovo-P650WG/S100 LMP/LML Release/2010.02.22
						// Profile/MIDP2.0 Configuration/CLDC1.1
			"LENOVO",// 联想手机，比如：LENOVO-P780/176A
			"HUAQIN",// 华勤手机
			"AIGO-",// 爱国者居然也出过手机，AIGO-800C/2.04 TMSS-BROWSER/1.0.0 CTC/1.0
			"CTC/1.0",// 含义不明
			"CTC/2.0",// 含义不明
			"CMCC",// 移动定制手机，K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223
					// Release/30.07.2008 Browser/WAP2.0
			"DAXIAN",// 大显手机DAXIAN X180 UP.Browser/6.2.3.2(GUI) MMP/2.0
			"MOT-",// 摩托罗拉，MOT-MOTOROKRE6/1.0 LinuxOS/2.4.20 Release/8.4.2006
					// Browser/Opera8.00 Profile/MIDP2.0 Configuration/CLDC1.1
					// Software/R533_G_11.10.54R
			"SonyEricsson",// 索爱手机，SonyEricssonP990i/R100 Mozilla/4.0
							// (compatible; MSIE 6.0; Symbian OS; 405) Opera
							// 8.65 [zh-CN]
			"GIONEE",// 金立手机
			"HTC",// HTC手机
			"ZTE",// 中兴手机，ZTE-A211/P109A2V1.0.0/WAP2.0 Profile
			"HUAWEI",// 华为手机，
			"webOS",// palm手机，Mozilla/5.0 (webOS/1.4.5; U; zh-CN)
					// AppleWebKit/532.2 (KHTML like Gecko) Version/1.0
					// Safari/532.2 Pre/1.0
			"GoBrowser",// 3g GoBrowser.User-Agent=Nokia5230/GoBrowser/2.0.290
						// Safari
			"IEMobile",// Windows CE手机自带浏览器，
			"WAP2.0"// 支持wap 2.0的
	};

	
	/**
	 * 获取整型值 
	 * @param request 对象
	 * @param param 参数名称
	 * @return null:表示没有找到
	 */
	protected Integer getInt(HttpServletRequest request, String param) {
		String value = request.getParameter(param);
		if (StringUtil.isInteger(value)) {
			return Integer.parseInt(value);
		} else {
			return null;
		}
	}
	
	/**
	 * 获取整型值 
	 * @param request 对象
	 * @param param 参数名称
	 * @param def 默认值，如果参数不存在或不符合规则就用默认值替代
	 * @return int
	 */
	protected Integer getInt(HttpServletRequest request, String param,int def) {
		String value = request.getParameter(param);
		if (StringUtil.isInteger(value)) {
			return Integer.parseInt(value);
		} else {
			return def;
		}
	}
	
	/**
	 * 获取base64机密的整型值 
	 * @param request 对象
	 * @param param 参数名称
	 * @return null:表示没有找到
	 */
	protected Integer getIntBase64(HttpServletRequest request, String param) {
		String value = request.getParameter(param);
		if (StringUtil.isInteger(value)) {
			return Integer.parseInt(new String(Base64Util.decode(value)));
		} else {
			return null;
		}
	}
	
	
	
	/**
	 * 获取布尔值
	 * @param request 对象
	 * @param param 参数名称
	 * @return null:表示没有找到
	 */
	protected Boolean getBoolean(HttpServletRequest request, String param) {
		String value = request.getParameter(param);
		try {
			return Boolean.parseBoolean(value);
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * 获取session的值
	 * 
	 * @param request
	 *            request对象
	 * @param key
	 *            枚举类中的值
	 * @return session中获取的对象
	 */
	protected Object getSession(HttpServletRequest request, SessionConst key) {
		return request.getSession().getAttribute(key.toString());
	}

	/**
	 * 设置session的值
	 * 
	 * @param key
	 *            枚举类中的值
	 * @return session中获取的对象
	 */
	protected void setSession(HttpServletRequest request, SessionConst key, Object value) {
		request.getSession().setAttribute(key.toString(), value);
	}

	/**
	 * 移除session的值
	 * 
	 * @param key
	 *            枚举类中的值
	 */
	protected void removeSession(HttpServletRequest request, SessionConst key) {
		request.getSession().removeAttribute(key.toString());
	}

	/**
	 * 获取Cookie的值
	 * 
	 * @param key
	 *            枚举类中的值
	 * @return Cookie中获取的对象
	 */
	protected String getCookie(HttpServletRequest request, CookieConst key) {
		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals(key.name())) {
					return c.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 设置Cookie的attr中的值
	 * 
	 * @param key
	 *            枚举类中的值
	 * @param value
	 *            存储对象
	 */
	protected void setCookie(HttpServletRequest request, HttpServletResponse response, CookieConst key, Object value) {
		request.getSession().setAttribute(key.toString(), value);
		Cookie cookie = new Cookie(key.name(), (String) value);
		cookie.setPath("/");
		cookie.setValue((String) value);
		response.addCookie(cookie);
	}

	/**
	 * 设置Cookie的attr中的值
	 * 
	 * @param key
	 *            枚举类中的值
	 * @param value
	 *            存储对象
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	protected void setCookie(HttpServletRequest request, HttpServletResponse response, CookieConst key, Object value, int maxAge) {
		request.getSession().setAttribute(key.toString(), value);
		Cookie cookie = new Cookie(key.name(), value.toString());
		cookie.setPath("/");
		cookie.setValue(value.toString());
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 输出json数据
	 * 
	 * @param code
	 *            模块编号<br/>
	 * @param flag
	 *            成功状态,true:成功、false:失败
	 * @param msg
	 *            提示信息
	 */
	protected void outJson(HttpServletResponse response, ModelCode code, boolean flag, String msg) {
		try {
			ResultJson result = new ResultJson();
			if (code != null) {
				result.setCode(code.toString());
			}
			result.setResult(flag);
			result.setResultMsg(msg);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(JSONObject.toJSON(result));
			LOG.debug(JSONObject.toJSON(result));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	/**
	 * 输出json数据
	 * 
	 * @param code
	 *            模块编号<br/>
	 * @param flag
	 *            成功状态,true:成功、false:失败
	 */
	protected void outJson(HttpServletResponse response, ModelCode code, boolean flag) {
		try {
			ResultJson result = new ResultJson();
			if (code != null) {
				result.setCode(code.toString());
			}
			result.setResult(flag);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(JSONObject.toJSON(result));
			LOG.debug(JSONObject.toJSON(result));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	/**
	 * 输出json数据
	 * 
	 * @param code
	 *            模块编号<br/>
	 * @param flag
	 *            成功状态,true:成功、false:失败
	 * @param msg
	 *            提示信息
	 * @param data
	 *            数据
	 */
	protected void outJson(HttpServletResponse response, ModelCode code, boolean flag, String msg, String data) {
		try {
			ResultJson result = new ResultJson();
			if (code != null) {
				result.setCode(code.toString());
			}
			result.setResult(flag);
			result.setResultMsg(msg);
			result.setResultData(data);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(JSONObject.toJSON(result));
			LOG.debug(JSONObject.toJSON(result));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	/**
	 * 输出json数据字符串
	 * 
	 * @param jsonDataStr
	 *            字符串
	 */
	protected void outJson(HttpServletResponse response, Object jsonDataStr) {
		try {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(jsonDataStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	/**
	 * 输出String数据字符串
	 * 
	 * @param dataStr
	 *            字符串
	 */
	protected void outString(HttpServletResponse response, Object dataStr) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(dataStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	/**
	 * 获取项目路径
	 * 
	 * @return 返回项目路径，例如：http://www.ip.com/projectName 后面没有反斜杠，后面接地址或参数必须加/
	 */
	protected String getUrl(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName();
		if (request.getServerPort() == 80) {
			basePath += path;
		} else {
			basePath += ":" + request.getServerPort() + path;
		}
		return basePath + "/";
	}

	/**
	 * 获取请求域名，域名不包括http请求协议头
	 * 
	 * @param request
	 * @return 域名地址
	 */
	protected String getDomain(HttpServletRequest request) {
		String path = request.getContextPath();
		String domain = request.getServerName();
		if (request.getServerPort() == 80) {
			domain += path;
		} else {
			domain += ":" + request.getServerPort() + path;
		}
		return domain;
	}

	/**
	 * 读取服务器主机信息
	 * 
	 * @param request
	 * @return
	 */
	protected String getHost(HttpServletRequest request) {
		String basePath = request.getServerName();
		if (request.getServerPort() != 80) {
			basePath += ":" + request.getServerPort();
		}
		return basePath;
	}
	
	/**
	 * 读取服务器主机ip信息
	 * 
	 * @param request
	 * @return 异常将会获取不到ip
	 */
	protected String getHostIp() {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			return addr.getHostAddress().toString();//获得本机IP
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 读取国际化资源文件
	 * 
	 * @param key
	 *            ，键值
	 * @return字符串
	 */
	protected String getResString(String key) {
		return Const.RESOURCES.getString(key);
	}

	/**
	 * 读取国际化资源文件
	 * 
	 * @param key
	 *            键值
	 * @param fullStrs
	 *            需填充的值
	 * @return 字符串
	 */
	protected String getResString(String key, String... fullStrs) {
		String temp = Const.RESOURCES.getString(key);
		for (int i = 0; i < fullStrs.length; i++) {
			temp = temp.replace("{" + i + "}", fullStrs[i]);
		}
		return temp;
	}

	/**
	 * 通过spring的webapplicationcontext上下文对象读取bean对象
	 * 
	 * @param sc
	 *            上下文servletConext对象
	 * @param beanName
	 *            要读取的bean的名称
	 * @return null:空
	 */
	protected Object getBean(ServletContext sc, String beanName) {
		return WebApplicationContextUtils.getWebApplicationContext(sc).getBean(beanName);
	}

	/**
	 * 返回重定向
	 * 
	 * @param request
	 * @param flag
	 *            true:提供给springMVC返回，false:只是获取地址
	 * @return 重定向后的地址
	 */
	protected String redirectBack(HttpServletRequest request, boolean flag) {
		if (flag) {
			return "redirect:" + this.getCookie(request, CookieConst.BACK_COOKIE);
		} else {
			return this.getCookie(request, CookieConst.BACK_COOKIE);
		}

	}

	/**
	 * 根据当前请求的特征，判断该请求是否来自手机终端，主要检测特殊的头信息，以及user-Agent这个header
	 * 
	 * @param request
	 *            http请求
	 * @return 如果命中手机特征规则，则返回对应的特征字符串
	 */
	public boolean isMobileDevice(HttpServletRequest request) {
		boolean b = false;
		boolean pcFlag = false;
		boolean mobileFlag = false;
		String via = request.getHeader("Via");
		String userAgent = request.getHeader("user-agent");
		for (int i = 0; via != null && !via.trim().equals("") && i < mobileGateWayHeaders.length; i++) {
			if (via.contains(mobileGateWayHeaders[i])) {
				mobileFlag = true;
				break;
			}
		}
		for (int i = 0; !mobileFlag && userAgent != null && !userAgent.trim().equals("") && i < mobileUserAgents.length; i++) {
			if (userAgent.contains(mobileUserAgents[i])) {
				mobileFlag = true;
				break;
			}
		}
		for (int i = 0; userAgent != null && !userAgent.trim().equals("") && i < pcHeaders.length; i++) {
			if (userAgent.contains(pcHeaders[i])) {
				pcFlag = true;
				break;
			}
		}
		if (mobileFlag == true && pcFlag == false) {
			b = true;
		}
		return b;// false pc true shouji

	}

	/**
	 * 读取用户sessoin
	 * 
	 * @return 获取不到就返回null
	 */
	protected PeopleEntity getPeopleBySession(HttpServletRequest request) {
		// 传入用户请求，读取用户的session || super,调用父类的protected属性的getSession方法
		Object obj = this.getSession(request, SessionConst.PEOPLE_SESSION);
		if (obj != null) {
			// 返回用户的所有信息
			return (PeopleEntity) obj;
		}
		return null;
	}

	/**
	 * 读取管理员session
	 * 
	 * @return 获取不到就返回null
	 */
	protected ManagerSessionEntity getManagerBySession(HttpServletRequest request) {
		// 传入用管理员请求，读取管理员的session || super,调用父类的protected属性的getSession方法
		Object obj = this.getSession(request, SessionConst.MANAGER_ESSION);
		if (obj != null) {
			// 返回管理员的所有信息
			return (ManagerSessionEntity) obj;
		}
		return null;
	}

	/**
	 * 读取管理员session
	 * 
	 * @return 获取不到就返回null
	 */
	protected String getCodeBySession(HttpServletRequest request) {
		// 传入用管理员请求，读取管理员的session || super,调用父类的protected属性的getSession方法
		Object obj = this.getSession(request, SessionConst.CODE_SESSION);
		if (obj != null) {
			// 返回管理员的所有信息
			return (String) obj;
		}
		return null;
	}

	/**
	 * 根据属性配置文件返回map
	 * 
	 * @return
	 */
	protected Map<String, String> getMapByProperties(String filePath) {
		if (StringUtil.isBlank(filePath)) {
			return null;
		}
		ResourceBundle rb = ResourceBundle.getBundle(filePath);
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> en = rb.getKeys();
		while (en.hasMoreElements()) {
			String key = en.nextElement();
			map.put(key, rb.getString(key));
		}
		return map;
	}

	/**
	 * 获取管理员id，规则：没有父ID就获取自身的ID
	 * 
	 * @return int 管理员编号
	 */
	protected int getManagerId(HttpServletRequest request) {
		int managerParent = getManagerBySession(request).getManagerParentID();

		if (managerParent == 0) {
			return getManagerBySession(request).getManagerId();
		} else {
			return managerParent;
		}
	}

	/**
	 * 判断当前管理员是否是系统平台管理员
	 * 
	 * @param request
	 *            　
	 * @return true:是　false:不是
	 */
	protected boolean isSystemManager(HttpServletRequest request) {
		ManagerSessionEntity manager = getManagerBySession(request);
		if (manager.getManagerRoleID() == Const.DEFAULT_SYSTEM_MANGER_ROLE_ID) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取当期项目物理路径
	 * 
	 * @param request
	 * @param path
	 *            　相对文件夹
	 * @return
	 */
	protected String getRealPath(HttpServletRequest request, String filePath) {
		return request.getServletContext().getRealPath(File.separator) + File.separator + filePath;
	}

	/**
	 * 获取当前模块编号
	 * 
	 * @param request
	 * @return 0:表示没有找到
	 */
	protected int getModelCodeId(HttpServletRequest request) {
		Object obj = this.getSession(request, SessionConst.MODEL_ID_SESSION);
		if (obj != null) {
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}

	/**
	 * 根据当前模块编码父模块编号
	 * 
	 * @param request
	 * @return 模块编号，如果没有返回0
	 */
	protected int getRootModelCodeId(HttpServletRequest request) {
		Object obj = this.getSession(request, SessionConst.MODEL_ID_SESSION);
		if (StringUtil.isInteger(obj)) {
			com.mingsoft.basic.biz.IModelBiz modelBiz = (com.mingsoft.basic.biz.IModelBiz) getBean(request.getServletContext(), "modelBiz");
			ModelEntity model = (ModelEntity) modelBiz.getEntity(Integer.parseInt(obj.toString()));
			return model.getModelModelId();
		} else {
			return 0;
		}
	}

	/**
	 * 根据当前的模块获取到当前模块的分类模块信息
	 * @param request
	 * @return 错误返回null
	 */
	protected ModelEntity getCategoryModelCode(HttpServletRequest request) {
		Object obj = this.getSession(request, SessionConst.MODEL_ID_SESSION);
		if (StringUtil.isInteger(obj)) {
			com.mingsoft.basic.biz.IModelBiz modelBiz = (com.mingsoft.basic.biz.IModelBiz) getBean(request.getServletContext(), "modelBiz");
			return modelBiz.getModel(IModelBiz.CATEGORY_MODEL, Integer.parseInt(obj.toString()));
		} else {
			return null;
		}
	}
	

	/**
	 * 根据当前的模块获取到当前模块的basic文章模块信息
	 * @param request
	 * @return 错误返回null
	 */
	protected ModelEntity getBasicModelCode(HttpServletRequest request) {
		Object obj = this.getSession(request, SessionConst.MODEL_ID_SESSION);
		if (StringUtil.isInteger(obj)) {
			com.mingsoft.basic.biz.IModelBiz modelBiz = (com.mingsoft.basic.biz.IModelBiz) getBean(request.getServletContext(), "modelBiz");
			return modelBiz.getModel(IModelBiz.BASIC_MODEL, Integer.parseInt(obj.toString()));
		} else {
			return null;
		}
	}
	
	/**
	 * 根据模块编码获得模块编号
	 * 
	 * @param request
	 * @param code
	 *            编码
	 * @return 模块编号，如果没有返回0
	 */
	protected int getModelCodeId(HttpServletRequest request, ModelCode code) {
		com.mingsoft.basic.biz.IModelBiz modelBiz = (com.mingsoft.basic.biz.IModelBiz) getBean(request.getServletContext(), "modelBiz");
		ModelEntity model = modelBiz.getEntityByModelCode(code);
		if (model != null) {
			return model.getModelId();
		}
		return 0;
	}

	/**
	 * 根据cookie获取历史页码
	 * 
	 * @param request
	 * @return　０:没有获取到
	 */
	protected int getHistoryPageNoByCookie(HttpServletRequest request) {
		if (Integer.valueOf(this.getCookie(request, CookieConst.PAGENO_COOKIE)) >= 1) {
			return Integer.valueOf(this.getCookie(request, CookieConst.PAGENO_COOKIE));
		}
		return 0;
	}

	/**
	 * 根据地址来查询对应的站点信息，依赖于websiteBiz
	 * 
	 * @param request
	 * @param websiteBiz
	 * @return 站点实体
	 */
	protected AppEntity getAppIdByUrl(HttpServletRequest request, IAppBiz websiteBiz) {
		String contentPath = request.getContextPath(); // 项目名称
		StringBuffer url = request.getRequestURL(); // 完整请求地址
		String websiteUrl = url.substring(0, url.indexOf("/"));
		if (!StringUtil.isBlank(contentPath)) {
			websiteUrl = url.substring(0, url.indexOf("/")) + "/" + contentPath;
		}
		// 查询数据库获取域名对应Id
		AppEntity website = websiteBiz.getByUrl(websiteUrl);
		if (website != null) {
			return website;
		}
		return null;
	}

	/**
	 * 获取当前模块对应的appid , appid主要根据用户的请求地址获得
	 * 
	 * @param request
	 * @return 0:表示没有相应app
	 */
	protected int getAppId(HttpServletRequest request) {

		// 获取用户所请求的域名地址
		IAppBiz websiteBiz = (IAppBiz) getBean(request.getServletContext(), "appBiz");
		AppEntity website = websiteBiz.getByUrl(this.getDomain(request));
		if (website == null) {
			return 0;
		}
		return website.getAppId();
	}

	/**
	 * 获取当前模块对应的appid , appid主要根据用户的请求地址获得
	 * 
	 * @param request
	 * @return 0:表示没有相应app
	 */
	protected AppEntity getApp(HttpServletRequest request) {
		AppEntity app = new AppEntity();
		// 获取用户所请求的域名地址
		IAppBiz appBiz = (IAppBiz) getBean(request.getServletContext(), "appBiz");
		AppEntity website = appBiz.getByUrl(this.getDomain(request));
		if (website == null) {
			return null;
		}
		BeanUtils.copyProperties(website, app);
		return app;
	}
	
	protected AppEntity getApp(String host,HttpServletRequest request) {
		AppEntity app = new AppEntity();
		// 获取用户所请求的域名地址
		IAppBiz appBiz = (IAppBiz) getBean(request.getServletContext(), "appBiz");
		AppEntity website = appBiz.getByUrl(this.getDomain(request));
		if (website == null) {
			return null;
		}
		BeanUtils.copyProperties(website, app);
		return app;
	}

	/**
	 * 获得当前应用的邮件服务器
	 * 
	 * @param request
	 * @return null 表示没有找到
	 */
	protected MailEntity getMail(HttpServletRequest request) {
		AppEntity app = getApp(request);
		if (app == null) {
			return null;
		}
		// 获取用户所请求的域名地址
		IMailBiz mailBiz = (IMailBiz) getBean(request.getServletContext(), "mailBiz");
		MailEntity mail = mailBiz.getByAppId(app.getAppId());
		if (mail == null) {
			return null;
		}
		return mail;
	}

	/**
	 * 发送邮件
	 * 
	 * @param request
	 * @param mailType
	 *            邮件类型 MailEntity.TEXT MailEntity.HTML
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param toUser
	 *            接收用户
	 */
	protected void sendMail(HttpServletRequest request, MailEnum mailType, String title, String content, String[] toUser) {
		MailEntity mail = getMail(request);
		if (mailType.toInt() == MailEnum.TEXT.toInt()) {
			MailUtil.sendText(mail.getMailServer(), mail.getMailPort(), mail.getMailName(), mail.getMailPassword(), title, content, toUser);
		} else if (mailType.toInt() == MailEnum.HTML.toInt()) {
			MailUtil.sendHtml(mail.getMailServer(), mail.getMailPort(), mail.getMailName(), mail.getMailPassword(), title, content, toUser);
		}
	}
	

	/**
	 * 动态生成页面，更具model_template表
	 * 
	 * @param key
	 *            对应表中的key值
	 * @param req
	 * @return　如果没有读到模版返回的是null，没读到的情况有两种原因一种是找不到相应的应用，一种是模版文件不存在
	 */
	protected String generaterPage(String key, HttpServletRequest req) {
		com.mingsoft.basic.biz.IModelTemplateBiz modelTemplateBiz = (com.mingsoft.basic.biz.IModelTemplateBiz) getBean(req.getServletContext(), "modelTemplateBiz");
		com.mingsoft.cms.regex.GeneraterFactory generaterFactory = (com.mingsoft.cms.regex.GeneraterFactory) getBean(req.getServletContext(), "generaterFactory");
		AppEntity app = this.getApp(req);
		if (app == null) {
			return null;
		}
		ModelTemplateEntity mte = modelTemplateBiz.getEntity(app.getAppId(), key);
		if (mte == null) {
			return null;
		}
		String templatePath = mte.getModelTemplatePath();
		String path = getRealPath(req, RegexConstant.REGEX_SAVE_TEMPLATE) + File.separator + app.getAppId() + File.separator + app.getAppStyle() + File.separator;
		String content = "";
		if (isMobileDevice(req) && !StringUtil.isBlank(app.getAppMobileStyle())) { // 移动端
			String htmlContent = FileUtil.readFile(path + app.getAppMobileStyle() + File.separator + templatePath); // 读取模版文件内容
			content = generaterFactory.builder(app, null, htmlContent, path, app.getAppMobileStyle());
		} else {
			String htmlContent = FileUtil.readFile(path + templatePath);
			content = generaterFactory.builder(app, null, htmlContent, path);
		}

		return content;

	}
	protected String generaterPage(String key, String host,HttpServletRequest req) {
		com.mingsoft.basic.biz.IModelTemplateBiz modelTemplateBiz = (com.mingsoft.basic.biz.IModelTemplateBiz) getBean(req.getServletContext(), "modelTemplateBiz");
		com.mingsoft.cms.regex.GeneraterFactory generaterFactory = (com.mingsoft.cms.regex.GeneraterFactory) getBean(req.getServletContext(), "generaterFactory");
		AppEntity app = this.getApp(host,req);
		if (app == null) {
			return null;
		}
		ModelTemplateEntity mte = modelTemplateBiz.getEntity(app.getAppId(), key);
		if (mte == null) {
			return null;
		}
		String templatePath = mte.getModelTemplatePath();
		String path = getRealPath(req, RegexConstant.REGEX_SAVE_TEMPLATE) + File.separator + app.getAppId() + File.separator + app.getAppStyle() + File.separator;
		String content = "";
		if (isMobileDevice(req) && !StringUtil.isBlank(app.getAppMobileStyle())) { // 移动端
			String htmlContent = FileUtil.readFile(path + app.getAppMobileStyle() + File.separator + templatePath); // 读取模版文件内容
			content = generaterFactory.builder(app, null, htmlContent, path, app.getAppMobileStyle());
		} else {
			String htmlContent = FileUtil.readFile(path + templatePath);
			content = generaterFactory.builder(app, null, htmlContent, path);
		}

		return content;

	}	
	

	/**
	 * 将请求的request的参数重新组装。主要是将空值的替换成null,因为requestMap空值是"",这样处理有利于外部判断,
	 * 同时将获取到的值映射到页面上
	 * 
	 * @param request
	 * @return　处理过后的数据
	 */
	protected Map<String, Object> assemblyRequestMap(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String[]> map = request.getParameterMap();
		Iterator<String> key = map.keySet().iterator();
		while (key.hasNext()) {
			String k = (String) key.next();
			String[] value = map.get(k);
			
			if (value.length == 1) {
				String temp = null;
				if (!StringUtil.isBlank(value[0])) {
					temp = value[0];
				}
				params.put(k, temp);
				request.setAttribute(k, temp);
			} else if (value.length == 0) {
				params.put(k, null);
				request.setAttribute(k, null);
			} else if (value.length > 1) {
				params.put(k, value);
				request.setAttribute(k, value);
			}
		}
		return params;
	}
	
	/**
	 * 获取请求的数据流，主要提供给微信平台接口使用
	 * @param request HttpServletRequest对象
	 * @return 字符串，例如：微信平台会返回JSON或xml字符串
	 */
	public String readStreamParameter(HttpServletRequest request) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
	

}
