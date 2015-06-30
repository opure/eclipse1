package com.mingsoft.basic.biz;
import java.util.List;
import com.mingsoft.basic.entity.RoleEntity;
import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.base.entity.BaseEntity;
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
 * Comments:角色业务层，接口，继承IBaseBiz
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
public interface IRoleBiz extends IBaseBiz{
	
	/**
	 * 根据角色名称查询角色
	 * @param name 角色名称
	 * @param roleManagerId 角色管理员ID
	 * @return 角色实体
	 */
	public RoleEntity queryRoleByRoleName(String roleName,int roleManagerId);
	
	/**
	 * 根据管理员ID查询角色
	 * @param roleManagerId 管理员ID
	 * @return 实体
	 */
	public List<BaseEntity> queryRoleByManagerId(int roleManagerId);
	
	/**
	 * 统计该角色名称在数据库中的存在数
	 * @param roleName 角色实体
	 * @param roleName 角色实体
	 * @return 存在数量
	 */
	public int countRoleName(String roleName, int roleManagerId);
	
	/**
	 * 分页查询当前管理员所创建的角色
	 * @param entity 实体
	 * @param page Map对象
	 * @param order 排序方式,true:asc;fales:desc
	 * @return
	 */
	public List<BaseEntity> queryByPage(int roleManagerId, PageUtil page,String orderBy,boolean order);
}
