package com.mingsoft.basic.dao;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.entity.ManagerModelPageEntity;
/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技基础框架</b>
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
 * @version 100-000-000
 * 
 * <p>
 * 版权所有
 * </p>
 *  
 * <p>
 * Comments:管理员持久化
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
public interface IManagerModelPageDao extends IBaseDao {
		/**
		 * 根据模块id和管理员id查找实体信息
		 * @param managerModelPagemanagerId 管理员id
		 * @param managerModelPageModelId 模块id
		 * @return
		 */
		ManagerModelPageEntity getByManagerIdAndModelId(@Param("managerModelPagemanagerId")int managerModelPagemanagerId,@Param("managerModelPageModelId")int managerModelPageModelId);
}
