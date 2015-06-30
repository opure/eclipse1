package com.mingsoft.basic.biz;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.basic.entity.SystemSkinEntity;

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
 * Comments:系统皮肤业务处理层
 * </p>
 *
 * <p>
 * Create Date:2015-1-10
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public interface ISystemSkinBiz extends IBaseBiz{

	/**
	 * 获取管理员对应的管理皮肤
	 * @param managerId　管理员编号
	 * @return　
	 */
	SystemSkinEntity getByManagerId(int managerId);
	
	/**
	 *设置管理员的后台皮肤
	 * @param managerId　管理员编号
	 * @param systemSkinId　皮肤编号
	 */
	SystemSkinEntity updateManagerSystemSkin(int managerId,int systemSkinId);
	
	
}
