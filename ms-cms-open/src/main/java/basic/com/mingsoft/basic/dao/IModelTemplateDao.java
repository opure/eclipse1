package com.mingsoft.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.entity.ModelTemplateEntity;
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
public interface IModelTemplateDao extends IBaseDao{
	/**
	 * 获取模版路径
	 * @param appId应用编号
	 * @param modelId　模块编号
	 * @param key　对应路径地址
	 * @return　模块模版
	 */
	ModelTemplateEntity getEntity(@Param("appId")int appId,@Param("modelId")int modelId,@Param("key")String key);
	
	/**
	 * 获取模版路径
	 * @param appId应用编号
	 * @param key　对应路径地址
	 * @return　模块模版
	 */
	ModelTemplateEntity getEntityByAppIdAndKey(@Param("appId")int appId,@Param("key")String key);
	
	/**
	 * 查询当前应用下面的所有自定义页面
	 * @param appId　应用编号
	 * @return　记录集合
	 */
	List queryByAppId(@Param("appId")int appId);
	
}
