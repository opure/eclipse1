package com.mingsoft.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.entity.DiyFormFieldEntity;

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
 * Comments:自定义表单字段
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
public interface IDiyFormFieldDao extends IBaseDao{

	/**
	 * 通过from的id获取实体
	 * @param diyFormId　自定义表单id 
	 * @return　实体
	 */
	List<DiyFormFieldEntity> queryByDiyFormId(@Param("diyFormFieldFormId") int diyFormId);
	
	DiyFormFieldEntity getByFieldName(@Param("diyFormFieldFormId") Integer diyFormId,@Param("diyFormFieldFieldName") String  diyFormFieldFieldName);
}
