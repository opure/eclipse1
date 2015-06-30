/**
 *
 */
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
 * @author 成卫雄
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:角色与模块关联表
 * </p>
 *  
 * <p>
 * Create Date:2014-5-28
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public class RoleModelEntity extends BaseEntity {

	/**
	 * 模块编号
	 */
	private int modelId;
	
	/**
	 * 角色编号
	 */
	private int roleId;

	/**
	 *获取 modelId
	 * @return modelId
	 */
	public int getModelId() {
		return modelId;
	}

	/**
	 *设置modelId
	 * @param modelId
	 */
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	/**
	 *获取 roleId
	 * @return roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 *设置roleId
	 * @param roleId
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
