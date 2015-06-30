package com.mingsoft.basic.entity;

import com.mingsoft.base.entity.BaseEntity;

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
 * @author 史爱华
 * 
 * @version 300-001-001
 * 
 *          <p>
 *          版权所有 铭飞科
 *          </p>
 * 
 *          <p>
 *          Comments: 管理页面实体类
 *          </p>
 * 
 *          <p>
 *          Create Date:2015-01-16
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
public class ManagerModelPageEntity extends BaseEntity {
	
	/**
	 * 管理员id
	 */
	private int managerModelPagemanagerId;
	
	/**
	 * 后台显示界面地址
	 */
	private String managerModelPageUrl;
	
	/**
	 * 模块编号
	 */
	private int managerModelPageModelId;

	public int getManagerModelPagemanagerId() {
		return managerModelPagemanagerId;
	}

	public void setManagerModelPagemanagerId(int managerModelPagemanagerId) {
		this.managerModelPagemanagerId = managerModelPagemanagerId;
	}

	public String getManagerModelPageUrl() {
		return managerModelPageUrl;
	}

	public void setManagerModelPageUrl(String managerModelPageUrl) {
		this.managerModelPageUrl = managerModelPageUrl;
	}

	public int getManagerModelPageModelId() {
		return managerModelPageModelId;
	}

	public void setManagerModelPageModelId(int managerModelPageModelId) {
		this.managerModelPageModelId = managerModelPageModelId;
	}
	
	
}
