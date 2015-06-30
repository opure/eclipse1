package com.mingsoft.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.entity.ModelEntity;

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
 * @author wangtp
 * 
 * @version 100-000-000
 * 
 * <p>
 * 版权所有
 * </p>
 *  
 * <p>
 * Comments:模块dao
 * </p>
 *  
 * <p>
 * Create Date:2014-6-29
 * </p>
 *
 * <p>
 * Modification history:暂无
 * </p>
 */
public interface IModelDao extends IBaseDao{
	
	
	
	/**
	 * 根据父模块id查找子id
	 * @return 子模块
	 */
	List<BaseEntity> queryChildList(int modelModelId);
	
	/**
	 *查找顶级模块
	 * @return
	 */
	List<BaseEntity> queryParent();
	
	/**
	 * 根据管理员ID查询模块集合
	 * @param managerId 管理员ID
	 * @return 模块集合
	 */
	List<BaseEntity> queryModelByManagerId(@Param ("modelManagerId") int modelManagerId,@Param ("modelId") int modelId);
	
	/**
	 * 查找管理员Id不为-1的模块
	 * @return
	 */
	List<BaseEntity> queryModelByManager();
	
	/**
	 * 根据角色ID查询模块集合
	 * @param roleId 角色ID
	 * @return 模块集合
	 */
	List<BaseEntity> queryModelByRoleId(int roleId);
	
	/**
	 * 根据模块编号查询模块实体
	 * @param modelCode 模块编号
	 * @return 模块实体
	 */
	ModelEntity getEntityByModelCode(@Param("modelCode")String modelCode);

	/**
	 * 根据模块id获取当前项目中的分类模块id，规则根据modelcode定。**99******,只用是第３位与第４位９９
	 * @param modelCodeRegex,规则。详细见IModelBiz
	 * @see IModelBiz
	 * @param modelId,模块根id
	 * @return 分类模块编号
	 */
	ModelEntity getModel(@Param("modelCodeRegex") String modelCodeRegex,@Param("modelId") int modelId);
	
}
