package com.mingsoft.basic.biz.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.IRoleBiz;
import com.mingsoft.basic.dao.IRoleDao;
import com.mingsoft.basic.entity.RoleEntity;
import com.mingsoft.util.PageUtil;

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
 * Comments:角色业务层实现类，继承BaseBizImpl，实现IRoleBiz
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
@Service("roleBiz")
public class RoleBizImpl extends BaseBizImpl implements IRoleBiz {
	
	/**
	 * 角色持久化层
	 */
	@Autowired
	private IRoleDao roleDao;

	 /**
	  * 获取roleDao
	  */
	@Override
	public IBaseDao getDao() {
		return roleDao;
	}
	
	/**
	 * 根据角色名称查询角色
	 * @param name 角色名称
	 * @param roleManagerId 角色管理员ID
	 * @return 角色实体
	 */
	public RoleEntity queryRoleByRoleName(String roleName,int roleManagerId){
		return roleDao.queryRoleByRoleName(roleName, roleManagerId);
	}
	
	/**
	 * 根据管理员ID查询角色
	 * @param roleManagerId 管理员ID
	 * @return 实体
	 */
	public List<BaseEntity> queryRoleByManagerId(int roleManagerId){
		return roleDao.queryRoleByManagerId(roleManagerId);
	}

	/**
	 * 统计该角色名称在数据库中的存在数
	 * @param role 角色实体
	 * @return 存在数量
	 */
	public int countRoleName(String roleName, int roleManagerId){
		return roleDao.countRoleName(roleName,roleManagerId);
	}
	
	/**
	 * 分页查询当前管理员所创建的角色
	 * @param entity 实体
	 * @param page Map对象
	 * @param order 排序方式,true:asc;fales:desc
	 * @return
	 */
	public List<BaseEntity> queryByPage(int roleManagerId, PageUtil page,String orderBy,boolean order){
		return roleDao.queryByPage(roleManagerId, page.getPageNo(),page.getPageSize(), orderBy, order);
	}
}
