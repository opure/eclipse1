package com.mingsoft.basic.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.IBasicBiz;
import com.mingsoft.basic.dao.IBasicDao;
import com.mingsoft.basic.entity.BasicEntity;
import com.mingsoft.util.PageUtil;

/**
 * 
 * <p>
 * <b>铭飞基础框架</b>
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
 * @author 荣繁奎
 * 
 * @version 100-000-000
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:基本信息的业务层(实现类)
 * </p>
 *  
 * <p>
 * Create Date:2014-5-10
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Service("basicBiz")
public  class BasicBizImpl extends BaseBizImpl implements IBasicBiz {
	@Autowired
	private IBasicDao basicDao;

	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return basicDao;
	}

    @Override
    public int saveBasic(BasicEntity basic) {
        basicDao.saveEntity(basic);
        return saveEntity(basic);
    }
    
    @Override
    public void updateBasic(BasicEntity basic) {
        basicDao.updateEntity(basic);
        updateEntity(basic);
    }
    
    @Override
    public void deleteBasic(int basicId) {
        basicDao.deleteEntity(basicId);
        deleteEntity(basicId);
    }
    
    @Override
    public BasicEntity getBasicEntity(int basicId){
    	return (BasicEntity) basicDao.getEntity(basicId);
    }

	@Override
	public void updateHit(int basicId, Integer num) {
		// TODO Auto-generated method stub
		  basicDao.updateHit(basicId, num);
	}

	@Override
	public int count(int categoryId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count(int categoryId, String keyWord) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BasicEntity> query(int categoryId) {
		// TODO Auto-generated method stub
		return basicDao.query(null,categoryId, null, null, null, null, null,null,null);
	}

	@Override
	public List<BasicEntity> query(int categoryId, String keyWord) {
		// TODO Auto-generated method stub
		return basicDao.query(null,categoryId, keyWord, null, null, null, null,null,null);
	}

	@Override
	public List<BasicEntity> query(Integer appId,Integer categoryId, String keyWord, PageUtil page,Integer modelId,Map where) {
		// TODO Auto-generated method stub
		return basicDao.query(appId,categoryId, keyWord, page.getPageSize()*page.getPageNo(), page.getPageSize(), null, null,modelId,where);
	}

    
    
    
}
