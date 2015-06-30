package com.mingsoft.basic.dao;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
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
 * Comments:系统主题持久化
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
public interface ISystemSkinDao extends IBaseDao{

	/**
	 * 获取管理员对应的管理皮肤
	 * @param managerId　管理员编号
	 * @return　
	 */
	public SystemSkinEntity getByManagerId(@Param("managerId")int managerId);
	
	/**
	 *更新管理员的后台皮肤
	 * @param managerId　管理员编号
	 * @param systemSkinId　皮肤编号
	 */
	public void updateManagerSystemSkin(@Param("managerId")int managerId,@Param("systemSkinId")int systemSkinId);
	
	/**
	 *保存管理员的后台皮肤
	 * @param managerId　管理员编号
	 * @param systemSkinId　皮肤编号
	 */
	public void saveManagerSystemSkin(@Param("managerId")int managerId,@Param("systemSkinId")int systemSkinId);
}
