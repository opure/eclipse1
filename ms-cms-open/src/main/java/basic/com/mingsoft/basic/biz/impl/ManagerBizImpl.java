package com.mingsoft.basic.biz.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.IManagerBiz;
import com.mingsoft.basic.dao.IManagerDao;
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
 * Comments:管理员业务层实现类，继承BaseBizImpl，实现IManagerBiz
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
@Service("managerBiz")
public class ManagerBizImpl extends BaseBizImpl implements IManagerBiz {

	/**
	 * 管理员持久化层
	 */
    private IManagerDao managerDao;
    
    /**
	 * 获取managerDao
	 * @return managerDao
	 */
    public IManagerDao getManagerDao() {
        return managerDao;
    }

    /**
	 * 设置managerDao
	 * @param managerDao
	 */
    @Autowired
    public void setManagerDao(IManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    /**
     * 获取managerDao
     */
    @Override
    public IBaseDao getDao() {
        return managerDao;
    }

    /**
     * 通过账号密码查询管理员，主要用于登陆模块
     * @param managerName 帐号
     * @return  管理员实体
     */
    @Override
    public ManagerEntity queryManagerByManagerName(String managerName) {
        return managerDao.queryManagerByManagerName(managerName);
    }
    
    /**
	 * 根据用户名修改用户密码
	 * @param manager 管理员实体
	 */
	@Override
	public void updateUserPasswordByUserName(ManagerEntity manager) {
		managerDao.updateUserPasswordByUserName(manager);
	}
	
	/**
	 * 统计该管理员帐号在数据库中的存在数
	 * @param managerName 管理员帐号
	 * @return 存在数量
	 */
	public String countManagerName(String managerName){
		return managerDao.countManagerName(managerName);
	}
	
	/**
	 * 查询当前登录的管理员的所有子管理员
	 * @return 管理员集合
	 */
	public List<BaseEntity> queryAllChildManager(int managerId){
		return managerDao.queryAllChildManager(managerId);
	}
	
	/**
	 * 通过角色ID删除管理员实体
	 * @param managerRoleID 角色ID
	 */
	public void deleteManagerByRoleId(int managerRoleID){
		managerDao.deleteManagerByRoleId(managerRoleID);
	}

	/**
	 * 分页查询当前管理员所创建的角色
	 * @param entity 实体
	 * @param page Map对象
	 * @param order 排序方式,true:asc;fales:desc
	 * @return
	 */
	public List<BaseEntity> queryByPage(int managerId, PageUtil page,String orderBy,boolean order){
		return managerDao.queryByPage(managerId, page.getPageNo(),page.getPageSize(), orderBy, order);
	}

	
	
}
