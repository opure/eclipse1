package com.mingsoft.cms.regex.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.mingsoft.cms.regex.RegexConstant;
import com.mingsoft.util.RegexUtil;
import com.mingsoft.util.StringUtil;

/**
 * 实现内容替换标签方法的抽象类
 * 
 * @author 成卫雄 QQ:330216230 技术支持：景德镇铭飞科技 官网：www.ming-soft.com
 */
public abstract class IParser {

	protected String mobilePath = "";

	
	/**
	 * 查询规则匹配次数
	 * @param source 模版
	 * @param regex 标签
	 * @return 标签的数量
	 */
	protected static  int  count(String source,String regex){
		return  RegexUtil.count(source, regex);
	}

	/**
	 * 返回所有匹配的结果，并且是find组
	 * @return 标签的数量
	 * @param source　模版
	 * @param regex　规则
	 * @param find　第几组
	 * @return
	 */
	protected static  List<String>  parseAll(String source,String regex,int find){
		return RegexUtil.parseAll(source, regex, find);
	}
	
	
	/**
	 * 返回第一次匹配的结果，并且是find组
	 * @param source　模版
	 * @param regex　标签
	 * @param find
	 * @return 匹配一次的值
	 */
	protected static String  parseFirst(String source,String regex,int find){
		return RegexUtil.parseFirst(source, regex,find);
	}

	/**
	 * 原始html内容
	 */
	protected String htmlCotent = null;

	/**
	 * 替换后的html内容
	 */
	protected String newCotent = null;
	
	/**
	 *  定位标签中属性的名称
	 */
	protected   final static String PRORETY_NAME="(\\w*)\\s*=";

	/**
	 * 定位标签中属性的值
	 */
	protected final static String PROPERTY_VALUE="=\\s*(\\w*)";

	public String getNewCotent() {
		return newCotent;
	}
	
	/**
	 * 取出栏目标签中的属性
	 * @param html HTML模版
	 * @return 属性集合
	 */
	public   Map<String,String> getProperty(String regex){
		 Map<String,String> listPropertyMap = new HashMap<String, String>();
		 String listProperty = parseFirst(this.htmlCotent,regex,1);
		 if (listProperty==null) {
			 return listPropertyMap;
		 }
		 List<String> listPropertyName = parseAll(listProperty,PRORETY_NAME, 1);
		 List<String> listPropertyValue = parseAll(listProperty,PROPERTY_VALUE, 1);
		 for(int i = 0 ;i<listPropertyName.size();i++){
			 listPropertyMap.put(listPropertyName.get(i).toString(), listPropertyValue.get(i).toString());
		 }
		 return listPropertyMap;
	}
	
	/**
	 * 标签替换的抽象方法
	 * 
	 * @return 替换完成的HTML模版
	 */
	public abstract String parse();

	
	/**
	 * 用该内容替换所有当前标签
	 * 
	 * @param htmlCotent
	 *            源内容
	 * @param newCotent
	 *            替换内容
	 * @param regex
	 *            替换规则，更具RegexEnum
	 * @return 替换完成的HTML模版
	 */
	public String replaceAll(String regex) {
		if (StringUtil.isBlank(newCotent)) {
			newCotent = RegexConstant.REGEX_ERRO;
		}
		return  RegexUtil.replaceAll(htmlCotent, regex,newCotent);
	}
	
	
	
	/**
	 * 用该内容替换第一个当前找到的第一个标签
	 * 
	 * @param htmlCotent
	 *            源内容
	 * @param newCotent
	 *            替换内容
	 * @param regex
	 *            替换规则，更具RegexEnum
	 * @return 替换完成的HTML模版
	 */
	public String replaceFirst(String regex) {
		if (StringUtil.isBlank(newCotent)) {
			newCotent = RegexConstant.REGEX_ERRO;
		}
		return RegexUtil.replaceFirst(htmlCotent, regex, newCotent);
	}
	
	public IParser setNewCotent(String newCotent) {
		this.newCotent = newCotent;
		return this;
	}

}
