package com.mingsoft.basic.biz;

import java.util.List;

import com.mingsoft.basic.entity.PeopleStudentEntity;
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
 * @author 史爱华
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments: 学生信息业务层接口，继承IPeopleBiz接口
 * </p>
 *  
 * <p>
 * Create Date:2014-12-19
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
public interface IPeopleStudentBiz  extends IPeopleBiz {
	/**
	 * 学生信息实体保存</br>
	 * 只能有子类继承时调用的</br>
	 * @param peopleStudentEntity 用户信息
	 * @return 新增成功后用户ID
	 */
	public int savePeopleStudent(PeopleStudentEntity peopleStudentEntity);
	
	/**
	 * 更新学生信息</br>
	 * 只能在有子类时调用</br>
	 * @param peopleStudentEntity 学生信息
	 */
	public void updatePeopleStudent(PeopleStudentEntity peopleStudentEntity);
	
	/**
	 * 删除学生信息</br>
	 * 只能在有子类时调用</br>
	 * @param peopleId 用户ID
	 */
	public void deletePeopleStudent(int peopleId);
	
	/**
	 * 查询学生信息
	 * @param peopleId
	 */
	public PeopleStudentEntity getPeopleStudent(int peopleId);
	
	/**
	 * 查询app下学生的信息
	 * @param appId
	 * @param page
	 * @return
	 */
	public List<PeopleStudentEntity> queryListPageByAppId(Integer appId ,PageUtil page);
}
