package com.mingsoft.cms.regex.parser.impl;

import com.mingsoft.cms.regex.parser.IParser;
import com.mingsoft.util.PageUtil;

/**
 * 实现分页功能并进行标签的替换
 * 分页标签
 * 首页：{ms:page.index}
 * 上一页:{ms:page.pre/}
 * 下一页:{ms:page.next/}
 * 尾页:{ms:page.over/}
 * @author 成卫雄
 * QQ:330216230
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public class PageParser extends IParser{
	
	
	
	/**
	 *  首页 列表分页标签 {ms:page.index}
	 */
	private final String PAGE_INDEX="\\{ms:page.index/\\}";
	
	/**
	 * 上一页 列表分页标签 {ms:page.pre/}
	 */
	private final String PAGE_PRE="\\{ms:page.pre/\\}";
	
	/**
	 * 下一页 列表分页标签 {ms:page.next/}
	 */
	private final String PAGE_NEXT="\\{ms:page.next/\\}";
	
	/**
	 * 尾页 列表分页标签 {ms:page.last/}
	 */
	private final String PAGE_OVER="\\{ms:page.last/\\}";
	
	private PageUtil page;
	/**
	 * 构造替换标签的必须属性
	 * @param urlPage 
	 * 				当前页面的链接 链接地址  http://www.***.com/servlet
	 * @param countPage  
	 * 				需要显示数据的总数量
	 * @param sizePage	
	 * 				 每页显示的信息数量
	 *  @param currentPage 
	 *  			当前页面在数据库中的光标位置
	 * @param htmlContent
	 * 				 原HTML代码
	 */
	public PageParser(String htmlContent,PageUtil page){
		super.htmlCotent = htmlContent;
		this.page = page;
	}
	
	@Override
	public String parse() {
		if (this.page!=null) {
			// TODO Auto-generated method stub
			//替换首页标签:{ms:page.index}
			super.newCotent = this.page.getIndexUrl().replace("\\", "/");
			String indexHtml = super.replaceFirst(PAGE_INDEX);
			//替换上一页标签:{ms:page.pre/}
			super.htmlCotent = indexHtml;
			super.newCotent =   this.page.getPreviousUrl().replace("\\", "/");
			String preHtml = super.replaceFirst(PAGE_PRE);
			//替换下一页标签：{ms:page.next/}
			super.htmlCotent = preHtml;
			super.newCotent =  this.page.getNextUrl().replace("\\", "/");
			String nextHtml = super.replaceFirst(PAGE_NEXT);
			//替换尾页标签：{ms:page.over/}
			super.htmlCotent = nextHtml;
			super.newCotent =   this.page.getLastUrl().replace("\\", "/");
			String traileHtml = super.replaceFirst(PAGE_OVER);			
			return traileHtml;
		} else {
			return htmlCotent;
		}

	
	}
	

	
}
