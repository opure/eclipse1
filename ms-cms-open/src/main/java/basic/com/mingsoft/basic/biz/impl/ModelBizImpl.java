package com.mingsoft.basic.biz.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.basic.dao.IModelDao;
import com.mingsoft.basic.entity.ModelEntity;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.IModelBiz;
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
 * Comments:模块业务层实现类，继承BaseBizImpl，实现IModelBiz
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
@Service("modelBiz")
public class ModelBizImpl extends BaseBizImpl implements IModelBiz{

	/**
	 * 根据父模块Id查找子模块
	 */
	@Override
	public List<BaseEntity> queryChildList(int modelModelId) {
		// TODO Auto-generated method stub
		return modelDao.queryChildList(modelModelId);
	}
	
	/**
	 * 查找顶级模块
	 */
	@Override
	public List<BaseEntity> queryParent() {
		// TODO Auto-generated method stub
		return modelDao.queryParent();
	}
	
	/**
	 * 根据管理员ID查询模块集合
	 * @param managerId 管理员ID
	 * @return 模块集合
	 */
	public List<BaseEntity> queryModelByManagerId(int managerId,int modelId){
		return modelDao.queryModelByManagerId(managerId,modelId);
	}

	/**
	 * 查找管理员Id不为-1的模块
	 * @return
	 */
	@Override
	public List<BaseEntity> queryModelByManager() {
		// TODO Auto-generated method stub
		return modelDao.queryModelByManager();
	}
	
	/**
	 * 根据角色ID查询模块集合
	 * @param roleId 角色ID
	 * @return 模块集合
	 */
	public List<BaseEntity> queryModelByRoleId(int roleId){
		return modelDao.queryModelByRoleId(roleId);
	}

	/**
	 * 根据模块编号查询模块实体
	 * @param modelCode 模块编号
	 * @return 模块实体
	 */
	public ModelEntity getEntityByModelCode(ModelCode modelCode){
		return modelDao.getEntityByModelCode(modelCode.toString());
	}	
	
	/**
	 * 模块持久化层
	 */
    private IModelDao modelDao;
    
    /**
     * 获取modelDao
     * @return modelDao
     */
    public IModelDao getModelDao() {
        return modelDao;
    }

    /**
     * 设置modelDao
     * @param modelDao
     */
    @Autowired
    public void setModelDao(IModelDao modelDao) {
        this.modelDao = modelDao;
    }

    /**
     * 获取modelDao
     */
    @Override
    protected IBaseDao getDao() {
        // TODO Auto-generated method stub
        return modelDao;
    }

	@Override
	public ModelEntity getModel(String modelType,int modelId) {
		// TODO Auto-generated method stub
		return modelDao.getModel(modelType,modelId);
	}

}
