package com.mingsoft.basic.biz;
import java.util.List;
import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.entity.ManagerEntity;
import com.mingsoft.basic.entity.SystemSkinEntity;
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
 * Comments:管理员业务层，接口，继承IBaseBiz
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
public interface IManagerBiz extends IBaseBiz {
	
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
	public List<BaseEntity> queryByPage(int managerId, PageUtil page,String orderBy,boolean order);

	
}
