package com.mingsoft.cms.regex.parser.impl;

import java.io.File;

import org.codehaus.plexus.util.StringUtils;

import com.mingsoft.cms.regex.parser.IParser;
import com.mingsoft.util.StringUtil;

/**
 * 文章所在栏目链接(单标签)
 * 文章内容标签
 * {ms:field.typelink/}
 * @author 成卫雄
 * QQ:330216230
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public class ArticleTypeLinkParser extends IParser {
	
	/**
	 * 文章作者标签
	 */
	private final static String ARTICLE_TYPELINK="\\{ms:field.typelink(.*)?/\\}";
	
	private  final static String TYPE = "type";
	
	private final static String TYPE_TOP = "top";
	
	
	/**
	 * 构造标签的属性
	 * @param htmlContent原HTML代码
	 * @param newContent替换的内容
	 */
	public ArticleTypeLinkParser(String htmlContent,String newContent){
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}
	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return super.replaceAll(ARTICLE_TYPELINK);
	}
	
	/**
	 *  是否存在type=top的属性
	 * @return　true:存在 false:不存在
	 */
	public boolean  isTop() {
		String temp  = super.getProperty(ARTICLE_TYPELINK).get(TYPE);
		if (StringUtils.isBlank(temp)) {
			return false;
		} else {
			return temp.equalsIgnoreCase(TYPE_TOP) ;
		}
	}

}
