package com.mingsoft.base.entity;

import java.util.List;

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
 *         Comments:列表通用json对象
 *         </p>
 * 
 *         <p>
 *         Create Date:2014-10-15
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
public class ListJson {

	private int count;
	private List list;

	public ListJson(int count, List list) {
		this.count = count;
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public List getList() {
		return list;
	}
}
