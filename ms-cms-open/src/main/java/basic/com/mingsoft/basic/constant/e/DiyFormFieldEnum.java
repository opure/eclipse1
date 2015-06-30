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
 *         Comments:自定义表单类型
 *         </p>
 * 
 *         <p>
 *         Create Date:2015-1-1
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
public enum DiyFormFieldEnum implements BaseEnum {
	/**
	 * 单行文本
	 */
	TEXT(1),
	/**
	 * 多行文本
	 */
	TEXTAREA(2),

	/**
	 * HTML
	 */
	HTML(3),

	/**
	 * 整型
	 */
	INT(4),

	/**
	 *  小數
	 */
	DOUBLE(5),

	/**
	 * 時間
	 */
	DATE(6),
	/**
	 * 图片
	 */
	IMAGE(7), 
	/**
	 *  附件
	 */
	ATTACHMENT(8), 
	/**
	 * 下拉框
	 */
	SELECT(9), 
	/**
	 * 單選
	 */
	RADIO(10), 
	/**
	 * 多選
	 */
	CHECKBOX(11),
	/**
	 * 许为空
	 */
	IS_NULL(1);

	DiyFormFieldEnum(Object code) {
		this.code = code;
	}

	private Object code;

	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.valueOf(code.toString());
	}

}
