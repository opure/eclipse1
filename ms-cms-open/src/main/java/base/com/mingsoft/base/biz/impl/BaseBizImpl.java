package com.mingsoft.base.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.util.PageUtil;

/**
 * 
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
 * Comments:基础业务实现
 * </p>
 *  
 * <p>
 * Create Date:2014-6-27
 * </p>
 *
 * <p>
 * Modification history:暂无
 * </p>
 */

public abstract class BaseBizImpl implements IBaseBiz {

	protected final  Logger LOG = Logger.getLogger(this.getClass());
	@Override
	public int saveEntity(BaseEntity entity) {
		return getDao().saveEntity(entity);
	}

	@Override
	public void deleteEntity(int id) {
		// TODO Auto-generated method stub
		
		getDao().deleteEntity(id);
	}

	@Override
	public void updateEntity(BaseEntity entity) {
		
		// TODO Auto-generated method stub
		getDao().updateEntity(entity);
	}

	@Override
	public List<BaseEntity> queryAll() {
		// TODO Auto-generated method stub
		return getDao().queryAll();
	}
	
	


	@Override
    public List<BaseEntity> queryByPage(PageUtil page,String orderBy,boolean order) {
        // TODO Auto-generated method stub
        return getDao().queryByPage(page.getPageNo(),page.getPageSize(), orderBy, order);
    }

	/**
	 * 查询数据表中记录集合总数</br>
	 * 可配合分页使用</br>
	 * @return
	 */
	public int queryCount(){
		return getDao().queryCount();
	}
	
	/**
	 * 更具ID查询实体信息
	 * @param entity 实体
	 * @param id 实体ID
	 * @return
	 */
	@Override
	public BaseEntity getEntity(int id){
		return getDao().getEntity(id);
	}
	

   

	@Override
	public List queryBySQL(String table, List<String> fields,Map wheres,Integer begin,Integer end) {
		// TODO Auto-generated method stub
		return  getDao().queryBySQL(table, fields, wheres,begin,end,null);
	}

	@Override
	public int countBySQL(String table, Map wheres) {
		// TODO Auto-generated method stub
		return   getDao().countBySQL(table, wheres);
	}

	@Override
	public List queryBySQL(String table, List<String> fields, Map wheres) {
		// TODO Auto-generated method stub
		return  getDao().queryBySQL(table, fields, wheres,null,null,null);
	}

	@Override
	public void updateBySQL(String table, Map fields, Map wheres) {
		// TODO Auto-generated method stub
		getDao().updateBySQL(table, fields, wheres);
	}

	@Override
	public void deleteBySQL(String table, Map wheres) {
		// TODO Auto-generated method stub
		getDao().deleteBySQL(table, wheres);
	}

	@Override
	public void insertBySQL(String table, Map fields) {
		// TODO Auto-generated method stub
		getDao().insertBySQL(table, fields);
	}
	
	

	@Override
	public void createTable(String table, Map<Object, List> fileds) {
		// TODO Auto-generated method stub
		getDao().createTable(table, fileds);
	}

	@Override
	public void alterTable(String table, Map fileds,String type) {
		// TODO Auto-generated method stub
		getDao().alterTable(table, fileds,type);
	}

	@Override
	public void dropTable(String table) {
		// TODO Auto-generated method stub
		getDao().dropTable(table);
	}

	@Override
	public void excuteSql(String sql) {
		// TODO Auto-generated method stub
		
	}

	protected abstract IBaseDao getDao();

	/**
	 * 批量新增
	 * @param list 新增数据
	 */
	@Override
	public void saveBatch(List list){
		getDao().saveBatch(list);
	}
	

}
