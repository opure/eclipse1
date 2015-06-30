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
 * Comments:管理员角色表
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
public class RoleEntity extends BaseEntity {

	/**
	 * 管理员角色自增长Id
	 */
	private int roleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 该角色的创建者ID
	 */
	private int roleManagerId;


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

	/**
	 *获取 roleName
	 * @return roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 *设置roleName
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * 获取rolePeopleId
	 * @return rolePeopleId
	 */
	public int getRoleManagerId() {
		return roleManagerId;
	}

	/**
	 * 设置rolePeopleId
	 * @param rolePeopleId
	 */
	public void setRoleManagerId(int rolePeopleId) {
		this.roleManagerId = rolePeopleId;
	}

}
