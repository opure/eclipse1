package com.mingsoft.basic.entity;

import com.mingsoft.base.entity.BaseEntity;

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
 * Comments:自定义表单实体
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
public class DiyFormEntity extends BaseEntity{
	/**
	 * 自增长ID
	 */
	private int diyFormId;
	
	/**
	 * 表名提示文字
	 */
	private String diyFormTipsName;
	
	/**
	 * 表单名称
	 */
	private String diyFormTableName;
	
	/**
	 * 表单所属的管理员id
	 */
	private int diyFormManagerId;
	
	/**
	 * 表单所属的管理员id
	 */
	private int diyFormAppId;

	public int getDiyFormId() {
		return diyFormId;
	}

	public void setDiyFormId(int diyFormId) {
		this.diyFormId = diyFormId;
	}

	public String getDiyFormTipsName() {
		return diyFormTipsName;
	}

	public void setDiyFormTipsName(String diyFormTipsName) {
		this.diyFormTipsName = diyFormTipsName;
	}

	public String getDiyFormTableName() {
		return diyFormTableName;
	}

	public void setDiyFormTableName(String diyFormTableName) {
		this.diyFormTableName = diyFormTableName;
	}

	public int getDiyFormManagerId() {
		return diyFormManagerId;
	}

	public void setDiyFormManagerId(int diyFormManagerId) {
		this.diyFormManagerId = diyFormManagerId;
	}

	public int getDiyFormAppId() {
		return diyFormAppId;
	}

	public void setDiyFormAppId(int diyFormAppId) {
		this.diyFormAppId = diyFormAppId;
	}
	
	
}
