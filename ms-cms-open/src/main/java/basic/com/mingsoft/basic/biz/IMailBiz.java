package com.mingsoft.basic.biz;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.basic.entity.MailEntity;

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
 * Comments:邮件
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
public interface IMailBiz extends IBaseBiz{

	/**
	 * 通过应用编号与模块编号获取邮件信息
	 * @param appId 应用编号 
	 * @param modelId 模块编号
	 * @return 邮件服务器信息
	 */
	MailEntity get(int appId,int modelId);
	
	/**
	 * 通过应用编号获取邮件信息
	 * @param appId 应用编号 
	 * @return 邮件服务器信息
	 */
	MailEntity getByAppId(int appId);
}
