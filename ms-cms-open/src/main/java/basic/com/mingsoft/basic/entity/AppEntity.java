package com.mingsoft.basic.entity;

import java.util.Date;

import com.mingsoft.base.entity.BaseEntity;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞Cms</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2013 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 史爱华
 * 
 * @version 100-000-000
 * 
 *          <p>
 *          版权所有
 *          </p>
 * 
 *          <p>
 *          Comments:网站基本信息实体类|
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-07-14
 *          </p>
 * 
 *          <p>
 *          Modification history:暂无
 *          </p>
 */
public class AppEntity extends BaseEntity {

	/**
	 * 应用名称
	 */
	private String appName;

	/**
	 * 应用描述
	 */
	private String appDescription;

	/**
	 * 应用logo
	 */
	private String appLogo;

	/**
	 * 应用编号
	 */
	private int appId;

	/**
	 * 网站采用的模板风格
	 */

	private String appStyle;
	
	/**
	 * 移动端样式目录
	 */
	private String appMobileStyle;


	/**
	 * 网站关键字
	 */
	private String appKeyword;

	/**
	 * 网站版权信息
	 */
	private String appCopyright;

	/**
	 * 网站域名
	 */
	private String appUrl;

	/**
	 * 管理站点的管理员id
	 */
	private int appManagerId;

	private Date appDatetime;

	public Date getAppDatetime() {
		return appDatetime;
	}

	public void setAppDatetime(Date appDatetime) {
		this.appDatetime = appDatetime;
	}

	/**
	 * 获取网站版权信息
	 * 
	 * @return
	 */
	public String getAppCopyright() {
		return appCopyright;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public int getAppId() {
		return appId;
	}

	/**
	 * 获取网站的关键字
	 * 
	 * @return
	 */
	public String getAppKeyword() {
		return appKeyword;
	}

	/**
	 * 获取管理员的id
	 * 
	 * @return
	 */
	public int getAppManagerId() {
		return appManagerId;
	}

	public String getAppName() {
		return appName;
	}

	/**
	 * 获取网站使用的模板风格
	 * 
	 * @return
	 */
	public String getAppStyle() {
		return appStyle;
	}

	/**
	 * 获取网站域名
	 */
	public String getAppUrl() {
		return appUrl;
	}
	
	/**
	 * 获取网站域名
	 */
	public String getAppHostUrl() {
		if (appUrl.indexOf("\n") > 0) { //存在多个域名绑定
			return appUrl.split("\n")[0].trim();
		}
		return appUrl;
	}

	public String getAppLogo() {
		return appLogo;
	}

	/**
	 * 设置网站版权信息
	 * 
	 * @param appCopyright
	 */
	public void setAppCopyright(String appCopyright) {
		this.appCopyright = appCopyright;
	}

	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	/**
	 * 设置网站关键字
	 * 
	 * @param appKeyword
	 */
	public void setAppKeyword(String appKeyword) {
		this.appKeyword = appKeyword;
	}

	/**
	 * 设置管理员的id
	 */
	public void setAppManagerId(int appManagerId) {
		this.appManagerId = appManagerId;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * 设置网站使用的模板风格
	 * 
	 * @param appStyle
	 */
	public void setAppStyle(String appStyle) {
		this.appStyle = appStyle;
	}

	/**
	 * 设置网站域名
	 * 
	 * @param appUrl
	 */
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	

	public String getAppMobileStyle() {
		return appMobileStyle;
	}

	public void setAppMobileStyle(String appMobileStyle) {
		this.appMobileStyle = appMobileStyle;
	}

	public void setAppLogo(String appLogo) {
		this.appLogo = appLogo;
	}

}
