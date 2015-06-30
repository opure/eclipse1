package com.mingsoft.basic.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.IManagerModelPageBiz;
import com.mingsoft.basic.dao.IManagerModelPageDao;
import com.mingsoft.basic.entity.ManagerModelPageEntity;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞基础框架</b>
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
 * @version 300-000-000
 * 
 * <p>
 * 版权所有
 * </p>
 *  
 * <p>
 * Comments:管理员模块页面业务层实现类||继承BasicBizImpl||实现IManagerModelPageBiz
 * </p>
 *  
 * <p>
 * Create Date:2015-01-16
 * </p>
 *
 * <p>
 * Modification history:暂无
 * </p>
 */
@Service
public class ManagerModelPageBizImpl extends BasicBizImpl implements  IManagerModelPageBiz{
	/**
	 * 声明持久化层
	 */
	@Autowired
	private IManagerModelPageDao managerModelPageDao;
	
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return managerModelPageDao;
	}

	@Override
	public ManagerModelPageEntity getByManagerIdAndModelId(
			int managerModelPagemanagerId, int managerModelPageModelId) {
		// TODO Auto-generated method stub
		return managerModelPageDao.getByManagerIdAndModelId(managerModelPagemanagerId, managerModelPageModelId);
	}
	
	
}
