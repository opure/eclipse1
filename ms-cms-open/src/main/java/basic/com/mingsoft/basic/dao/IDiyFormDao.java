package com.mingsoft.basic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.entity.DiyFormEntity;

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
 * Comments:自定义表单
 * </p>
 *
 * <p>
 * Create Date:2015-1-1
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public interface IDiyFormDao extends IBaseDao{
	/**
	 * 查询列表
	 * @param appId　应用编号
	 * @return
	 */
	List query(@Param("diyFormAppId")int appId);
	
	/**
	 * 根据表名查找自定义表单实体
	 * @param diyFormTableName
	 * @return
	 */
	DiyFormEntity getByTableName(@Param("diyFormTableName")String diyFormTableName);
	
	/**
	 * 为自定义表单创建表
	 * @param table
	 * @param fileds
	 */
	void createDiyFormTable(@Param("table")String table,@Param("fileds")Map<Object,List> fileds);
}
