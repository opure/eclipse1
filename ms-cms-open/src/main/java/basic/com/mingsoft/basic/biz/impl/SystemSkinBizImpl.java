package com.mingsoft.basic.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.ISystemSkinBiz;
import com.mingsoft.basic.dao.ISystemSkinDao;
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
 * Comments:系统主题处理实现
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
@Service("systemSkinBiz")
public class SystemSkinBizImpl extends BaseBizImpl implements ISystemSkinBiz{

	@Autowired
	private ISystemSkinDao systemSkinDao;
	@Override
	public SystemSkinEntity getByManagerId(int managerId) {
		return systemSkinDao.getByManagerId(managerId);
	}
	
	/**
	 *设置管理员的后台皮肤
	 * @param managerId　管理员编号
	 * @param systemSkinId　皮肤编号
	 */
	public SystemSkinEntity updateManagerSystemSkin(int managerId,int systemSkinId) {
		SystemSkinEntity sse = systemSkinDao.getByManagerId(managerId);
		if (sse!=null) {
			systemSkinDao.updateManagerSystemSkin(managerId, systemSkinId);
		} else {
			systemSkinDao.saveManagerSystemSkin(managerId, systemSkinId);
		}
		return (SystemSkinEntity)systemSkinDao.getEntity(systemSkinId);
	
	}
	
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return systemSkinDao;
	}

}
