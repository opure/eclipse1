package com.mingsoft.basic.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.entity.ManagerEntity;
import com.mingsoft.basic.entity.SystemSkinEntity;

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
 * Comments:管理员持久化
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
public interface IManagerDao extends IBaseDao {

	/**
     * 通过账号查询管理员，主要用于登陆模块
     * @param managerName 帐号
     * @return  管理员实体
     */
	public ManagerEntity queryManagerByManagerName(String managerName);
	
	/**
	 * 根据用户名修改用户密码
	 * @param manager 管理员实体
	 */
	public void updateUserPasswordByUserName(ManagerEntity manager);	
	
	/**
	 * 统计该管理员帐号在数据库中的存在数
	 * @param managerName 管理员帐号
	 * @return 存在数量
	 */
	public String countManagerName(String managerName);
	
	/**
	 * 查询当前登录的管理员的所有子管理员
	 * @return 管理员集合
	 */
	public List<BaseEntity> queryAllChildManager(int managerId);
	
	/**
	 * 通过角色ID删除管理员实体
	 * @param managerRoleID 角色ID
	 */
	public void deleteManagerByRoleId(int managerRoleID);
	
	/**
	 * 分页查询当前管理员所创建的角色
	 * @param entity 实体
	 * @param page Map对象
	 * @param order 排序方式,true:asc;fales:desc
	 * @return
	 */
	public List<BaseEntity> queryByPage(@Param("managerId")int managerId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("orderBy")String orderBy,@Param("order") boolean order);


	
}
