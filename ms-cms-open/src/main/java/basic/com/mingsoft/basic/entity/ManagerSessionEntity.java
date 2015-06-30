package com.mingsoft.basic.entity;
import java.util.List;

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
 * Comments:Session管理员实体，继承ManagerEntity
 * </p>
 *  
 * <p>
 * Create Date:2014-8-1
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public class ManagerSessionEntity extends ManagerEntity {
	
    /**
     * 父管理员ID
     */
    private int managerParentID;
    
    /**
     * 子管理员集合
     */
    private List<BaseEntity> managerChildIDs;
    
    /**
     * 站点ID
     */
    private int basicId;
    
    /**
     * 当前应用使用的默认风格
     */
    private String style;
    
    
    public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/**
     * 管理员的后台皮肤
     */
    private SystemSkinEntity systemSkin;

	/**
     * 获取managerParentID
     * @return managerParentID
     */
	public int getManagerParentID() {
		return managerParentID;
	}

	/**
	 * 设置managerParentID
	 * @param managerParentID
	 */
	public void setManagerParentID(int managerParentID) {
		this.managerParentID = managerParentID;
	}

	/**
	 * 获取managerChildIDs
	 * @return managerChildIDs
	 */
	public List<BaseEntity> getManagerChildIDs() {
		return managerChildIDs;
	}

	/**
	 * 设置managerChildIDs
	 * @param managerChildIDs
	 */
	public void setManagerChildIDs(List<BaseEntity> managerChildIDs) {
		this.managerChildIDs = managerChildIDs;
	}

	/**
	 * 获取basicId
	 * @return basicId
	 */
	public int getBasicId() {
		return basicId;
	}

	/**
	 * 设置basicId
	 * @param basicId
	 */
	public void setBasicId(int basicId) {
		this.basicId = basicId;
	}
	
    public SystemSkinEntity getSystemSkin() {
		return systemSkin;
	}

	public void setSystemSkin(SystemSkinEntity systemSkin) {
		this.systemSkin = systemSkin;
	}
    
    
}
