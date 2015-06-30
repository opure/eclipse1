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
 * Comments:类别基础信息关联表
 * </p>
 *  
 * <p>
 * Create Date:2014-3-29
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public class BasicCategoryEntity extends BaseEntity {
	/**
	 * 类别编号
	 */
	private int bcCategoryId;
	
	/**
	 * 基本信息编号
	 */
	private int bcBasicId;

	/**
	 *获取 bcCategoryId
	 * @return bcCategoryId
	 */
	public int getBcCategoryId() {
		return bcCategoryId;
	}

	/**
	 *设置bcCategoryId
	 * @param bcCategoryId
	 */
	public void setBcCategoryId(int bcCategoryId) {
		this.bcCategoryId = bcCategoryId;
	}

	/**
	 *获取 bcBasicId
	 * @return bcBasicId
	 */
	public int getBcBasicId() {
		return bcBasicId;
	}

	/**
	 *设置bcBasicId
	 * @param bcBasicId
	 */
	public void setBcBasicId(int bcBasicId) {
		this.bcBasicId = bcBasicId;
	}
}
