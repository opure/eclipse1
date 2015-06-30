package com.mingsoft.cms.regex.parser.impl;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mingsoft.cms.regex.RegexConstant;
import com.mingsoft.cms.regex.parser.IParser;
import com.mingsoft.util.StringUtil;

public class DateParser extends IParser {

	/**
	 * 网站路径标签，单标签，主要用于提交列表等HTML的路径到相应的action中 网站全局标签 {ms:global.url/}
	 */
	private final static String DATE =  "\\[field.date\\s{0,}(fmt=(.*?))?/]";
	
	private Date date;

	/**
	 * 构造标签的属性
	 * 
	 * @param htmlContent原HTML代码
	 * @param newContent替换的内容
	 */
	public DateParser(String htmlContent,Date date) {
		super.htmlCotent = htmlContent;
		this.date = date;
	}
	
	/**
	 * 构造标签的属性
	 * 
	 * @param htmlContent原HTML代码
	 * @param newContent替换的内容
	 */
	public DateParser(String htmlContent, String newContent) {
		super.htmlCotent = htmlContent;
		super.newCotent = newContent;
	}

	@Override
	public String parse() {
		Pattern pattern = Pattern.compile(DATE);
		Matcher matcher = pattern.matcher(htmlCotent);
		while (matcher.find()) {
			String date = matcher.group();
			htmlCotent = htmlCotent.replace(date, date(date)); 
		}
		return htmlCotent;
	}

	/**
	 * 将时间格式转换成字符串型并改变时间的显示格式
	 * 
	 * @param date
	 *            数据库中的时间
	 * @param htmlCotent
	 *            读取时间格式的HTML代码
	 * @return 时间
	 */
	private String date(String reg) {
		// 从HTML代码中取出时间的显示格式，默认为yyyy-MM-dd hh:mm:ss形式
		String typeDate = RegexConstant.REGEX_DATE;
		String fmt = parseFirst(htmlCotent,DATE, 2);
		if (!StringUtil.isBlank(fmt)) {
			typeDate = fmt;
		}
		// 将时间转换成String型
		String srtDate = RegexConstant.REGEX_DATE_ERRO;
		if (date != null) {
			try {
				java.text.SimpleDateFormat forDate = new java.text.SimpleDateFormat(typeDate);
				srtDate = forDate.format(date);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return srtDate;
	}
}
