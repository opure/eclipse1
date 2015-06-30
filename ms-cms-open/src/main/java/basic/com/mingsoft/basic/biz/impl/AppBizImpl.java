package com.mingsoft.basic.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.IAppBiz;
import com.mingsoft.basic.dao.IAppDao;
import com.mingsoft.basic.entity.AppEntity; 
/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞Cms</b>
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
 * @version 100-000-000
 * 
 * <p>
 * 版权所有
 * </p>
 *  
 * <p>
 * Comments:网站基本信息业务层实现类||继承BasicBizImpl||实现IWebsiteBiz
 * </p>
 *  
 * <p>
 * Create Date:2014-07-14
 * </p>
 *
 * <p>
 * Modification history:暂无
 * </p>
 */
@Service("appBiz")
public class AppBizImpl extends BasicBizImpl implements IAppBiz{
	
	/**
	 * 声明website持久化层
	 */
	@Autowired
	private IAppDao appDao;
	
	
	@Override
	public AppEntity getByManagerId(int managerId) {
		// TODO Auto-generated method stub
		return (AppEntity) appDao.getByManagerId(managerId);
	}

	/**
	 * 获取websiteDao持久化对象
	 */
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return appDao;
	}
	
	/**
	 * 判断域名是否存在
	 */
	@Override
	public int countByUrl(String websiteUrl) {
		
		
		// TODO Auto-generated method stub
		return appDao.countByUrl(websiteUrl);
	}
	/**
	 * 根据域名查找站点实体
	 */
	@Override
	public AppEntity getByUrl(String url) {
		// TODO Auto-generated method stub
		return (AppEntity) appDao.getByUrl(url);
	}
	
}
