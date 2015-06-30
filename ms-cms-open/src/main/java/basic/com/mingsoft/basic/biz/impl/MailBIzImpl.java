package com.mingsoft.basic.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.basic.dao.IMailDao;
import com.mingsoft.basic.entity.MailEntity;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.IMailBiz;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 *
 * @author 王天培
 *                QQ:78750478
 *
 * <p>
 * Comments: 邮件
 * </p>
 *
 * <p>
 * Create Date:2014-11-9
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Service(value="mailBiz")
public class MailBIzImpl extends BaseBizImpl implements IMailBiz {

	@Autowired
	private IMailDao mailDao;
	
	@Override
	protected IBaseDao getDao() {
		return null;
	}

	@Override
	public MailEntity get(int appId, int modelId) {
		// TODO Auto-generated method stub
		return mailDao.get(appId, modelId) ;
	}
	
	@Override
	public MailEntity getByAppId(int appId) {
		// TODO Auto-generated method stub
		return mailDao.get(appId, null) ;
	}
	

}
