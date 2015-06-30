package com.mingsoft.basic.biz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.basic.entity.DiyFormEntity;
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
public interface IDiyFormFieldBiz extends IBaseBiz{
	/**
	 * 通过from的id获取实体
	 * @param diyFormId　自定义表单id 
	 * @return　实体
	 */
	List<DiyFormFieldEntity> queryByDiyFormId( int diyFormId);
	
	DiyFormFieldEntity  getByFieldName(Integer diyFormFieldFormId,String diyFormFieldFieldName);
	
}
