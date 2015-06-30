package com.mingsoft.basic.biz.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.basic.dao.IModelDao;
import com.mingsoft.basic.dao.IModelTemplateDao;
import com.mingsoft.basic.entity.ModelTemplateEntity;
import com.mingsoft.basic.entity.ModelEntity;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.biz.IModelTemplateBiz;
import com.mingsoft.constant.ModelCode;

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
 * Comments:模块模版
 * </p>
 *  
 * <p>
 * Create Date:2014-7-14
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Service("modelTemplateBiz")
public class ModelTemplateBizImpl extends BaseBizImpl implements IModelTemplateBiz{

	@Autowired
	private IModelTemplateDao modelTemplateDao;
	@Override
	public ModelTemplateEntity getEntity(int appId, int modelId,String key) {
		return modelTemplateDao.getEntity(appId, modelId,key);
	}

	@Override
	public ModelTemplateEntity getEntity(int appId, String key) {
		return modelTemplateDao.getEntityByAppIdAndKey(appId, key);
	}

	@Override
	protected IBaseDao getDao() {
		return modelTemplateDao;
	}

	@Override
	public List queryByAppId(int appId) {
		// TODO Auto-generated method stub
		return modelTemplateDao.queryByAppId(appId);
	}

	
}
