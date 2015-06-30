package com.mingsoft.cms.regex;
/**
 * 解析正则表达式模块引入的静态常量
 * @author 成卫雄
 * QQ:330216230
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public interface RegexConstant {
	
	/**
	 * 生成的静态列表页面名
	 */
	final String PAGE_LIST ="list";
	/**
	 * 存放模版的文件夹
	 */
	final String REGEX_SAVE_TEMPLATE = "templets";
	
	/**
	 * 拥有栏目标签的提交地址
	 */
	final String REGEX_ARETICLETYPE_URL = "{ms:global.url/}/list?tid=";
	
	/**
	 * 接受栏目标签提交数据
	 */
	final String REGEX_ARETICLETYPE_URL_B = "tid";
	
	/**
	 * 提交的文章标签地址
	 */
	final String REGEX_ARTICLE_URL = "articleShow?aid=";
	
	/**
	 * 接受提交文章
	 */
	final String REGEX_ARTICLE_URL_A = "aid";
	
	
	/**
	 * 拥有封面标签的提交地址
	 */
	final String REGEX_COVER_URL = "{ms:global.url/}/";
	
	/**
	 * 接受封面链接提交的ID
	 */
	final String REGEC_COVER_URL_C = "cid";
	
	/**
	 * 模版主页
	 */
	final String REGEX_INDEX_HTML = "index.htm";
	
	
	/**
	 * 时间读取错误后报错
	 */
	final String REGEX_DATE_ERRO = "时间读取失败";
	
	/**
	 * 没有找到相应的栏目后报错
	 */
	final String REGEX_CHANNEL_ERRO="";
	
	/**
	 * 提示用户:未找到模版
	 */
	final String VIEW_NOT_HTML = "未找到显示该内容的模版";
	
	/**
	 * 提示用户：标签错误
	 */
	final String REGEX_ERRO = "未找到该标签内容";
	
	/**
	 * 新闻模板文件名
	 */
	final String ARTICLE_FILE_NAME = "article_article1.htm";
	
	/**
	 * 标签使用错误
	 */
	final String REGEX_ERR = "标签使用错误，导致堆栈错误";
	
	
	/**
	 * 文件夹路径名
	 */
	final String HTML_SUFFIX = ".html";
	

	
	/**
	 * 提交文章栏目地址
	 */
	final String HTML_LIST = "/list.do";
	/**
	 * 默认页
	 */
	String HTML_INDEX = "index.html";
	
	/**
	 *静态文件生成路径
	 */
	final String HTML_SAVE_PATH = "html";
	
	/**
	 * 返回构建目录结构失败的错误
	 */
	final String URL_STRUCTURE_ERRO = "构建目录结构失败";

	//----------------HTML标签中的属性开始-----------------------	
	/**
	 * 时间的默认格式
	 */
	final String REGEX_DATE="yyyy-MM-dd hh:mm:ss";

	/**
	 * 列表中的属性, typeid 类型 int栏目ID,在列表模板和档案模板中一般不需要指定，在首页模板中允许用","分开表示多个栏目
	 * 文章列表属性标签
	 * 必填
	 */
	final String LIST_TYPEID = "typeid";
		
	/**
	 * 列表中的属性，size 类型 int 返回文档列表总数,默认为20条全部返回，也可以配合分页使用
	 * 文章列表属性标签
	 * 非必填
	 */
	final String LIST_SIZE = "size";
	
	/**
	 * 列表中的属性，titlelen 类型 int 标题长度,等同于titlelength。默认40个汉字
	 * 文章列表属性标签
	 * 非必填
	 */
	final String LIST_TITLELENGTH = "titlelen";
	
	/**
	 * 列表中的属性，flag 类型 int flag = 1 自定义属性值：推荐[1]幻灯[2]
	 * 文章列表属性标签
	 * 非必填
	 */
	final String LIST_FLAG = "flag";
	
	/**
	 * 分页标签，true表示有分页
	 */
	String LIST_ISPAGING="ispaging";
	
	/**
	 * 文章列表中的属性, orderby 要进行排序的字段
	 */
	final String LIST_ORDERBY ="orderby";
	
	/**
	 * 文章列表中的属性, 默认(desc)升序，asc降序
	 */
	final String LIST_ORDER ="order";
	
	/**
	 * 文章列表属性,orderby 的值，如果为date则按最新时间进行排序
	 */
	final String ORDERBY_VALUE = "date";
	
	/**
	 * 列表中的属性，noflag 类型 int noflag = 1 自定义属性值：不推荐[1]不幻灯[2]
	 * 文章列表属性标签
	 * 非必填
	 */
	final String LIST_NOFLAG = "noflag";
	

	
	
	
	//----------------HTML标签中的属性开始-----------------------
	
}
