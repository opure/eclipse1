package com.mingsoft.basic.dao;
import java.util.List;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.entity.RoleModelEntity;

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
 * @author 姓名：张敏
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:角色模块关联持久化层，接口，继承IBaseDao
 * </p>
 *  
 * <p>
 * Create Date:2014-7-13
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public interface IRoleModelDao extends IBaseDao{
	
	/**
	 * 保存该角色对应的模块集合
	 * @param roleModelList 集合
	 */
	public void saveEntity(List<RoleModelEntity> roleModelList);
	
	/**
	 * 更新该角色对应的模块集合
	 * @param roleModelList 集合
	 */
	public void updateEntity(List<RoleModelEntity> roleModelList);
}
