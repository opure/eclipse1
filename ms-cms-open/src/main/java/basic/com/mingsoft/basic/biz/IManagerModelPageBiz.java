package com.mingsoft.basic.biz;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.basic.entity.ManagerModelPageEntity;

/**
 * 
 * 
 * <p>
 * <b>铭飞CMS-铭飞内容管理系统</b>
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
 * @author 姓名：史爱华
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:管理员模块页面业务层，接口，继承IBaseBiz
 * </p>
 *  
 * <p>
 * Create Date:2015-01-16
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public interface IManagerModelPageBiz extends IBaseBiz {
	/**
	 * 根据管理员id和模块id查找管理主界面实体
	 * @param managerModelPagemanagerId 管理员id
	 * @param managerModelPageModelId 模块id
	 * @return
	 */
	ManagerModelPageEntity getByManagerIdAndModelId(int managerModelPagemanagerId,int managerModelPageModelId);
}
