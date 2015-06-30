package com.mingsoft.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
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
 * Comments:学生信息持久化层接口，继承IBaseDao
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
public interface IPeopleStudentDao  extends IBaseDao {
		
		/**
		 * 获取用户实体信息
		 * @param peopleId
		 * @return
		 */
		PeopleStudentEntity getPeopleStudent(int peopleId);
		
		/**
		 * 查询app下学生的信息
		 * @param appId
		 * @param page
		 * @return
		 */
		List<PeopleStudentEntity> queryPageListByAppId(@Param("appId") Integer appId,@Param("page" ) PageUtil page);
}
