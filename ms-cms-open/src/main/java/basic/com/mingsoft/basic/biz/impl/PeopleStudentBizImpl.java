package com.mingsoft.basic.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.basic.biz.IPeopleUserBiz;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.IPeopleStudentBiz;
import com.mingsoft.basic.dao.IPeopleStudentDao;
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
 * @author 刘继平
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments: 用户信息业务层层实现类，继承PeopleBizImpl，实现IPeoplePrivateBiz接口
 * </p>
 *  
 * <p>
 * Create Date:2014-9-4
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Service("peopleStudentBiz")
public class PeopleStudentBizImpl extends PeopleBizImpl implements IPeopleStudentBiz {
	
	/**
	 * 学生信息持久化层注入
	 */
	@Autowired
	private IPeopleStudentDao peopleStudentDao;
	
	@Autowired
	private IPeopleUserBiz peopleUserBiz;
	
	

	@Override
	protected IBaseDao getDao() {
		return peopleStudentDao;
	}
	
	@Override
	public int savePeopleStudent(PeopleStudentEntity peopleStudentEntity) {
		peopleUserBiz.savePeople(peopleStudentEntity);
		return peopleStudentDao.saveEntity(peopleStudentEntity);
	}

	@Override
	public void updatePeopleStudent(PeopleStudentEntity peopleStudentEntity) {
		// TODO Auto-generated method stub
		peopleUserBiz.updateEntity(peopleStudentEntity);
		this.peopleStudentDao.updateEntity(peopleStudentEntity);
	}

	@Override
	public void deletePeopleStudent(int peopleId) {
		deletePeople(peopleId);
		this.peopleStudentDao.deleteEntity(peopleId);
	}

	@Override
	public PeopleStudentEntity getPeopleStudent(int peopleId) {
		// TODO Auto-generated method stub
		return this.peopleStudentDao.getPeopleStudent(peopleId);
	}

	@Override
	public List<PeopleStudentEntity> queryListPageByAppId(Integer appId,
			PageUtil page) {
		// TODO Auto-generated method stub
		return peopleStudentDao.queryPageListByAppId(appId, page);
	}
	
	

}
