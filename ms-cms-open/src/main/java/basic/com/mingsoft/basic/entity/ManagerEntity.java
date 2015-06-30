package com.mingsoft.basic.entity;
import java.util.Date;
import com.mingsoft.base.entity.BaseEntity;

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
 * @author wangtp
 * 
 * @version 300-001-001
 * 
 *          <p>
 *          版权所有 铭飞科
 *          </p>
 * 
 *          <p>
 *          Comments: 管理员实体类
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-5-14
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
public class ManagerEntity extends BaseEntity {

    /**
     * 自增长编号
     */
    private int managerId;

    /**
     * 帐号
     */
    private String managerName;

    /**
     * 昵称
     */
    private String managerNickName;

    /**
     * 密码
     */
    private String managerPassword;

    /**
     * 角色ID
     */
    private int managerRoleID;

    /**
     * 用户ID
     */
    private int managerPeopleID;

    /**
     * 添加时间
     */
    private Date managerTime = new Date();

    /**
     * 获取managerTime
     * @return managerTime
     */
    public Date getManagerTime() {
        return managerTime;
    }

    /**
     * 设置managerTime
     * @param managerTime
     */
    public void setManagerTime(Date managerTime) {
        this.managerTime = managerTime;
    }

    /**
     * 获取managerId
     * @return managerId
     */
    public int getManagerId() {
        return managerId;
    }

    /**
     * 设置managerId
     * @param managerId
     */
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    /**
     * 获取managerName
     * @return managerName
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * 设置managerName
     * @param managerName
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * 获取managerPassword
     * @return managerPassword
     */
    public String getManagerPassword() {
        return managerPassword;
    }

    /**
     * 设置managerPassword
     * @param managerPassword
     */
    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    /**
     * 获取managerPeopleID
     * @return managerPeopleID
     */
    public int getManagerPeopleID() {
        return managerPeopleID;
    }

    /**
     * 设置managerPeopleID
     * @param managerPeopleID
     */
    public void setManagerPeopleID(int managerPeopleID) {
        this.managerPeopleID = managerPeopleID;
    }

    /**
     * 获取managerNickName
     * @return managerNickName
     */
    public String getManagerNickName() {
        return managerNickName;
    }

    /**
     * 设置managerNickName
     * @param managerNickName
     */
    public void setManagerNickName(String managerNickName) {
        this.managerNickName = managerNickName;
    }

    /**
     * 获取managerRoleID
     * @return managerRoleID
     */
	public int getManagerRoleID() {
		return managerRoleID;
	}

	/**
	 * 设置managerRoleID
	 * @param managerRoleID
	 */
	public void setManagerRoleID(int managerRoleID) {
		this.managerRoleID = managerRoleID;
	}	
	
}
