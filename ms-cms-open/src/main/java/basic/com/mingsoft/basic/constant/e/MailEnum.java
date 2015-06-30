package com.mingsoft.basic.constant.e;

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
 *         Comments: 邮箱枚举类
 *         </p>
 * 
 *         <p>
 *         Create Date:2014-12-22
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
public enum MailEnum implements BaseEnum {
	TEXT(1), HTML(2);
	Object code;

	MailEnum(Object code) {
		this.code = code;
	}

	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.parseInt(this.code.toString());
	}

}
