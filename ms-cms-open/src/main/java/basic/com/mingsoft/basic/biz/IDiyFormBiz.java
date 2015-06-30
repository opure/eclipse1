package com.mingsoft.basic.biz;

import java.util.List;
import java.util.Map;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.basic.entity.DiyFormEntity;
import com.mingsoft.util.PageUtil;

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
public interface IDiyFormBiz extends IBaseBiz{

	/**
	 * 保存自定义表单的数据
	 * @param formId 表单编号
	 * @param params　参数值集合
	 */
	void saveDiyFormData( int formId,Map params);
	
	/**
	 * 查询自定义表单的数据
	 * @param diyForm　自定义表单
	 * @param fields　字段
	 * @return map fileds:字段列表 list:记录集合
	 */
	Map queryDiyFormData(int diyFormId,int appId,PageUtil page);
	
	/**
	 * 删除记录
	 * @param id　记录编号
	 * @param diyFormId 表单编号
	 */
	void deleteQueryDiyFormData(int id,int diyFormId);
	/**
	 *查询总数
	 */
	int countDiyFormData(int diyFormId,int appId);
	/**
	 * 查询列表
	 * @param appId　应用编号
	 * @return
	 */
	List query(int appId);
	
	/**
	 * 
	 * @param diyFormTableName
	 * @return
	 */
	DiyFormEntity  getByTableName(String diyFormTableName);
	
	/**
	 * 创建表
	 * @param table 表名称
	 * @param fileds key:字段名称  list[0] 类型  list[1]长度 list[2]默认值 list[3]是否不填
	 */
	@SuppressWarnings("rawtypes")
	void createDiyFormTable(String table,Map<Object,List> fileds);
}
