package com.mingsoft.base.entity;


/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技J2EE基础框架</b>
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
 * @author killfen
 * 
 * @version 100-000-000
 * 
 * <p>
 * 版权所有
 * </p>
 *  
 * <p>
 * Comments:json数据返回数据格式
 * </p>
 *  
 * <p>
 * Create Date:2013-9-2
 * </p>
 *
 * <p>
 * Modification history:暂无
 * </p>
 */
public class ResultJson {

	/**
	 * 模块编号
	 */
	private String code;
	
	/**
	 * 返回状态:true成功 false:失败
	 */
	private boolean result;
	
	/**
	 * 返回信息提示
	 */
	private String resultMsg;
	
	
	/**
	 * 返回数据
	 */
	private String resultData = "";

	/**
	 * 返回后跳的地址
	 */
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	
	
	public String getResultData() {
		return resultData;
	}

	public void setResultData(String resultData) {
		this.resultData = resultData;
	}

	
}
