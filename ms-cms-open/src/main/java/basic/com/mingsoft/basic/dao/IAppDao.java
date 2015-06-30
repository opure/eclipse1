package com.mingsoft.basic.dao;


import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;


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
 * Comments:网站基本信息持久化层||继承IBaseDao
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
public interface IAppDao extends IBaseDao{
	/**
	 * 根据域名查找相同域名的个数
	 * @return
	 */
	int countByUrl(String websiteUrl);
	
	/**
	 * 根据域名查找站点实体
	 * @param url:域名
	 * @return
	 */
	BaseEntity getByUrl(String websiteUrl);
	
	/**
	 * 更据站点管理员id查找站点
	 * @return
	 */
	BaseEntity getByManagerId(int managerId);
}
