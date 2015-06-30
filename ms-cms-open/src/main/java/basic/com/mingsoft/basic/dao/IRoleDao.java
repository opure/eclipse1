package com.mingsoft.basic.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.mingsoft.basic.entity.RoleEntity;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;

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
 * Comments:角色持久化层，接口
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
public interface IRoleDao extends IBaseDao{
	
	/**
	 * 根据角色名称查询角色
	 * @param name 角色名称
	 * @param roleManagerId 角色管理员ID
	 * @return 角色实体
	 */
	public RoleEntity queryRoleByRoleName(@Param("roleName")String roleName,@Param("roleManagerId")int roleManagerId);
	
	/**
	 * 根据管理员ID查询角色
	 * @param roleManagerId 管理员ID
	 * @return 实体
	 */
	public List<BaseEntity> queryRoleByManagerId(int roleManagerId);
	
	/**
	 * 查询该角色名称在数据库中的存在数
	 * @param roleName 角色实体
	 * @return 存在数量
	 */
	public int countRoleName(@Param("roleName")String roleName, @Param("roleManagerId")int roleManagerId);
	
	/**
	 * 分页查询当前管理员所创建的角色
	 * @param entity 实体
	 * @param page Map对象
	 * @param order 排序方式,true:asc;fales:desc
	 * @return
	 */
	public List<BaseEntity> queryByPage(@Param("roleManagerId")int roleManagerId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("orderBy")String orderBy,@Param("order") boolean order);

}
