package com.mingsoft.basic.entity;

import com.mingsoft.base.entity.BaseEntity;

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
 * @author 王天培 QQ:78750478
 * 
 *         <p>
 *         Comments:邮件
 *         </p>
 * 
 *         <p>
 *         Create Date:2014-11-9
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
public class MailEntity extends BaseEntity {

	private int mailAppId;

	private int mailModelId;

	private String mailName;
	private String mailPassword;
	private int mailPort;
	private String mailServer;
	private String mailContent;
	private String mailReceive;

	public int getMailAppId() {
		return mailAppId;
	}

	public String getMailContent() {
		return mailContent;
	}

	public int getMailModelId() {
		return mailModelId;
	}

	public String getMailName() {
		return mailName;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public int getMailPort() {
		return mailPort;
	}

	public String getMailReceive() {
		return mailReceive;
	}

	public String getMailServer() {
		return mailServer;
	}

	public void setMailAppId(int mailAppId) {
		this.mailAppId = mailAppId;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public void setMailModelId(int mailModelId) {
		this.mailModelId = mailModelId;
	}

	public void setMailName(String mailName) {
		this.mailName = mailName;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public void setMailPort(int mailPort) {
		this.mailPort = mailPort;
	}

	public void setMailReceive(String mailReceive) {
		this.mailReceive = mailReceive;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}
}
