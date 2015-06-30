package com.mingsoft.basic.biz.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.IRoleModelBiz;
import com.mingsoft.basic.dao.IRoleModelDao;
import com.mingsoft.basic.entity.RoleModelEntity;

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
 * Comments:角色模块关联业务层实现类，继承BaseBizImpl，实现IRoleModelBiz
 * </p>
 *  
 * <p>
 * Create Date:2014-7-13
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Service("roleModelBiz")
public class RoleModelBizImpl extends BaseBizImpl implements IRoleModelBiz {
	
	/**
	 * 角色模块关联持久化层
	 */
	@Autowired
	private IRoleModelDao roleModelDao;

	 /**
	  * 获取roleModelDao
	  */
	@Override
	public IBaseDao getDao() {
		return roleModelDao;
	}
	
	/**
	 * 保存该角色对应的模块集合
	 * @param roleModelList 集合
	 */
	public void saveEntity(List<RoleModelEntity> roleModelList){
		roleModelDao.saveEntity(roleModelList);
	}
	
	/**
	 * 更新该角色对应的模块集合
	 * @param roleModelList 集合
	 */
	public void updateEntity(List<RoleModelEntity> roleModelList){
		roleModelDao.updateEntity(roleModelList);
	}
}
