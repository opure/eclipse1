package com.mingsoft.cms.regex.parser.impl;

import com.mingsoft.cms.regex.parser.IParser;

public class ProductCostPrice  extends IParser{
	/**
	 * 商品的编码标签
	 */
	private final static String PRODUCT_FIELD_COSTPRICE="\\{ms:field.costprice/\\}";
	
	/**
	 * 构造标签的属性
	 * @param htmlContent 原HTML代码
	 * @param newContent 替换的内容
	 */
	public ProductCostPrice(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return replaceAll(PRODUCT_FIELD_COSTPRICE);
	}

}
