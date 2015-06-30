package com.mingsoft.cms.regex.parser;

/**
 * 存放正则表达式的（枚举类）
 * 
 * @author 成卫雄 QQ:330216230 技术支持：景德镇铭飞科技 官网：www.ming-soft.com
 */
public enum RegexEnum {
	/**
	 * 定位标签中属性的名称
	 */
	PRORETY_NAME("(\\w*)\\s*="),

	/**
	 * 定位标签中属性的值
	 */
	PROPERTY_VALUE("=\\s*(\\w*)"),

	/**
	 * 网站名称 网站全局标签 {ms:global.name /}
	 */
	GLOBAL_NAME("\\{ms:global.name/\\}"),

	/**
	 * 网站Logo,返回logo的图片地址 网站全局标签 {ms: global.logo/}
	 */
	GLOBAL_LOGO("\\{ms:global.logo/\\}"),

	/**
	 * 网站关键字,网站标签 网站全局标签 {ms: global.keyword/}
	 */
	GLOBAL_KEYWORD("\\{ms:global.keyword/\\}"),

	/**
	 * 网站描述,网站标签 网站全局标签 {ms: global.descrip/}
	 */
	GLOBAL_DESCRIP("\\{ms:global.descrip/\\}"),

	/**
	 * 网站版权信息,网站标签 网站全局标签 {ms: global.copyright/}
	 */
	GLOBAL_COPYRIGHT("\\{ms:global.copyright/\\}"),

	/**
	 * 模版路径标签 ,单标签，主要用语引入CSS,js等文件4 网站全局标签 {ms:globalskin.url /}
	 */
	GLOBAL_SKIN("\\{ms:globalskin.url/\\}"),

	/**
	 * 网站路径标签，单标签，主要用于提交列表等HTML的路径到相应的Servlet中 网站全局标签 {ms:global.url/}
	 */
	GLOBAL_URL("\\{ms:global.url/\\}"),

	/**
	 * 引入文件标签 ,主要用于引入公用的HTML等文件 .filename 是相对ms:global.skin下面的文件名称 网站全局标签 {ms:include filename="url"/}
	 */
	INCLUDE("\\{ms:include filename\\=(.*?)\\s*/}"),
	
	
	
	
	
	
	/**
	 *不带分页列表头标签, 文章列表标签 {ms:arclist typeid= size= titlelen= flag = }
	 */
	LIST_NOPAGING("\\{ms:arclist(?![^\\{\\}]*? ispaging=true[^\\{\\}]*?}).*?\\}"),

	/**
	 * 带分页列表头标签, 文章列表标签 {ms:arclist typeid= size= titlelen= flag = }
	 */
	LIST_PAGING("\\{ms:arclist.*.(ispaging=true){1}.*\\}"),
	
	/**
	 * 定位没有分页列表标签中所有的属性
	 */
	LIST_PROPERTY("\\{ms:arclist((?![^\\{\\}]*?ispaging=true[^{}]*?}).*?)\\}"),
	
	/**
	 * 定位有分页列表标签属性
	 */
	LIST_PAGING_PROPERTY("\\{ms:arclist(.*.(ispaging=true){1}.*)\\}"),

	/**
	 * 列表尾标签 文章列表标签 {/ms:arclist}
	 */
	LIST_END("\\{/ms:arclist\\}"),
	
	/**
	 * 临时列表结束标签
	 */
	LIST_TEMP_TAB_END("\\{MS:TAB}([\\s\\S]*?)(\\{\\/ms:arclist})"),
	
	/**
	 * 文章列表子标签:序号,根据显示条数显示的序号1 2 …..10 文章列表子标签 [field.index/]
	 */
	INDEX_FIELD_LIST("\\[field.index/\\]"),

	/**
	 * 文章列表子标签:编号,对应文章在数据库里的自动编号 文章列表子标签 [field.id/]
	 */
	ID_FIELD_LIST("\\[field.id/\\]"),

	/**
	 * 标题,标题长度根据titlelen的属性值指定，默认40个汉字 文章列表子标签 [field.title/]
	 */
	TITLE_FIELD_LIST("\\[field.title/\\]"),

	/**
	 * 全部标题,显示完整的标题 文章列表子标签 [field.fulltitle/]
	 */
	FULLTITLE_FIELD_LIST("\\[field.fulltitle/\\]"),

	/**
	 * 文章缩略图 文章列表子标签 [field.litpic/]
	 */
	LITPIC_FIELD_LIST("\\[field.litpic/\\]"),
	
	/**
	 * 文章描述标签文章列表子标签[field.descrip/]
	 */
	DESCIRIP_FIELD_LIST("\\[field.descrip/\\]"),

	/**
	 * 作者 文章列表子标签 [field.author/]
	 */
	AUTHOR_FIELD_LIST("\\[field.author/\\]"),

	/**
	 * 来源 文章列表子标签 [field.source/]
	 */
	SOURCE_FIELD_LIST("\\[field.source/\\]"),

	/**
	 * 时间 文章列表子标签 fmt:时间的显示格式(非必填项) [field.date fmt="yyyy-mm-dd"/]
	 */
	DATE_FIELD_LIST("\\[field.date\\s{0,}(fmt=(.*?))?/]"),

	/**
	 * 内容 文章列表子标签 length:内容的长度,指定获取文章长度)(非必填) [field.content length=/]
	 */
	CONTENT_FIELD_LIST("\\[field.content\\s{0,}(length=(\\d*).{0,})?/]"),

	/**
	 * 分类名称，文章所属分类的名称 文章列表子标签 [field.typename/]
	 */
	TYPENAME_FIELD_LIST("\\[field.typename/\\]"),

	/**
	 * 分类编号,文章所属分类的编号 文章列表子标签 [field.typeid/]
	 */
	TYPEID_FIELD_LIST("\\[field.typeid/\\]"),

	/**
	 * 分类连接, 点击连接连接到当前分类的列表 文章列表子标签 [field.typelink/]
	 */
	TTYPELINK_FIELD_LIST("\\[field.typelink/\\]"),

	/**
	 * 内容链接 ,点击显示文章具体的内容地址 文章列表子标签 [field.link/]
	 */
	LINK_FIELD_LIST("\\[field.link/\\]"),

	/**
	 * 当前页面文章的数量 文章列表子标签 [field.num]
	 */
	NUM_ARTICLE_LIST("\\[field.num/\\]"),
	
	/**
	 * 当前文章中的自定义标签
	 */
	TAGLIB_ARTICLE_LIST("\\[cfield.(.*)?\\s*/\\]"),
	
	// ---------------文章列表子标签结束---------------
	
	/**
	 * 列表临时标签，开始标签
	 */
	TAB_BEGIN_LIST("\\{MS:TAB\\}"),

	/**
	 * 列表临时标签，结束标签
	 */
	TAB_END_LIST("\\{/MS:TAB\\}"),

	/**
	 *列表临时标签，内容规则
	 */
	TAB_BODY("\\{MS:TAB\\}([\\s\\S]*?)\\{/MS:TAB}"),
	



	// ---------------分页标签开始---------------
	/**
	 * 首页 列表分页标签 {ms:page.index}
	 */
	PAGE_INDEX("\\{ms:page.index/\\}"),

	/**
	 * 上一页 列表分页标签 {ms:page.pre/}
	 */
	PAGE_PRE("\\{ms:page.pre/\\}"),

	/**
	 * 下一页 列表分页标签 {ms:page.next/}
	 */
	PAGE_NEXT("\\{ms:page.next/\\}"),

	/**
	 * 尾页 列表分页标签 {ms:page.last/}
	 */
	PAGE_OVER("\\{ms:page.last/\\}"),

	/**
	 * 页面总数 列表分页标签 {ms:page.total/}
	 */
	PAGE_TOTAL("\\{ms:page.total/\\}"),

	/**
	 * 当前处于第几页 列表分页标签 {ms:page.cur/}
	 */
	PAGE_CUR("\\{ms:page.cur/\\}"),

	/**
	 * 列表文章的总数 列表分页标签 {ms:page.rcount}
	 */
	PAGE_RCOUNT("\\{ms:page.rcount/\\}"),
	// ---------------分页标签结束---------------

	// ----------------------文章列表标签结束---------------------
	
	
	//----------------------评论列表标签开始---------------------
	//---------------评论列表父标签开始---------------
		/**
		 * 列表头标签,
		 * 评论列表标签
		 * {ms:cmtlist size=15 arcid = 2}
		 */
		BEGIN_CMTLIST("\\{ms:cmtlist.*?\\}"),
		
		/**
		 * 列表尾标签
		 * 评论列表标签
		 * {/ms:cmtlist}
		 */
		END_CMTLIST("\\{/ms:cmtlist\\}"),
	//---------------评论列表父标签结束---------------
		
	//---------------替换标签开始---------------
		/**
		 * 替换当前要插入内容列表的头标签
		 */
		TAB_BEGIN_CMTLIST("\\{MS:TAB\\}"),
		
		/**
		 * 替换当前要插入内容列表的尾标签
		 */
		TAB_END_CMTLIST("\\{/MS:TAB\\}"),
		
		/**
		 * 用来替换内容的列表标签
		 */
		TAB_CMTLIST("\\{MS:TAB\\}(.*(\\s|\\S){0,}?)\\{/MS:TAB}"),
	//---------------替换标签结束---------------
		
	//---------------评论列表属性标签开始---------------
		/**
		 * 定位列表标签中所有的属性
		 */
		CMTLIST_PROPERTY("\\{ms:cmtlist(.*)?\\}"),
		
	//---------------评论列表属性标签结束---------------
		
	//---------------文章列表子标签开始---------------
						
		/**
		 * 评论ID,对应评论在数据库里的自动编号
		 * 评论列表子标签
		 * [field.id/]	
		 */
		ID_FIELD_CMTLIST("\\[field.id/\\]"),
		
		/**
		 * 父评论ID,对应该条评论属于哪条评论的子评论
		 * 评论列表子标签
		 * [field.pid/]	
		 */
		PID_FIELD_CMTLIST("\\[field.pid/\\]"),
	
		/**
		 * 文章/商品标题
		 * 评论列表子标签
		 * 	[field.title/]		
		 */
		TITLE_FIELD_CMTLIST("\\[field.title/\\]"),
		
		/**
		 * 评论时发布的图片
		 * 评论列表子标签
		 * [field.image/]
		 */
		 IMAGE_FIELD_CMTLIST("\\[field.image/\\]"),
		
		/**
		 * 评论内容
		 * 评论列表子标签
		 * 	[field.content/]		
		 */
		CONTENT_FIELD_CMTLIST("\\[field.content/\\]"),
		
		/**
		 * 评论者用户名
		 * 评论列表子标签
		 * 	[field.peoplename/]		
		 */
		PEOPLENAME_FIELD_CMTLIST("\\[field.peoplename/\\]"),
		
		/**
		 * 评论者用户头像	
		 * 评论列表子标签
		 * [field.peopleicon/]
		 */
		PEOPLEICON_FIELD_CMTLIST("\\[field.peopleicon/\\]"),

		/**
		 * 评论者用户积分	
		 * 评论列表子标签
		 * [field.peoplescore/]
		 */
		PEOPLESCORE_FIELD_CMTLIST("\\[field.peoplescore/\\]"),
	
		/**
		 * 时间
		 * 评论列表子标签
		 * fmt:时间的显示格式(非必填项)
		 * 	[field.date  fmt="yyyy-mm-dd"/]
		 */
		DATE_FIELD_CMTLIST("\\[field.date\\s{0,}(fmt=(.*?))?/]"),

		/**
		 * 当前页面评论的数量
		 * 评论列表子标签
		 * [field.num]
		 */
		NUM_COMMENT_CMTLIST("\\[field.num/\\]"),
		
	//---------------评论列表子标签结束---------------	


	// --------------------文章内容标签开始---------------------------
	/**
	 * 内容标题 文章内容标签 {ms:field.title/}
	 */
	ARTICLE_TITLE_FIELD("\\{ms:field.title/\\}"),

	/**
	 * 内容发布时间 文章内容标签 fmt:根据用户指定的格式输出时间(非必填) {ms:field.date fmt="yyyy-mm-dd"/}
	 */
	ARTICLE_DATE_FIELD("\\{ms:field.date\\s{0,}(fmt=(.*?))?/}"),

	/**
	 * 内容发布作者 文章内容标签 {ms:field.author/}
	 */
	ARTICLE_AUTHOR_FIELD("\\{ms:field.author/\\}"),

	/**
	 * 内容发布来源 文章内容标签 {ms:field.source/}
	 */
	ARTICLE_SOURCE_FIELD("\\{ms:field.source/\\}"),

	/**
	 * 文章内容 文章内容标签 {ms:field.content/}
	 */
	ARTICLE_CONTENT_FIELD("\\{ms:field.content/\\}"),


	/**
	 * 当前栏目所在文章链接 文章内容标签 {ms:field.typelink/}
	 */
	ARTICLE_TYPELINK_FIELD("\\{ms:field.typelink/\\}"),

	/**
	 * 上一篇文章链接 文章内容标签 {ms:field.prelink/}
	 */
	ARTICLE_PRELINK_FIELD("\\{ms:field.prelink/\\}"),

	/**
	 * 下一篇文章链接 文章内容标签 {ms:field.nextlink/}
	 */
	ARTICLE_NEXTLINK_FIELD("\\{ms:field.nextlink/\\}"),

	/**
	 * 上一篇文章标题 文章内容标签 {ms:field.pretitle/}
	 */
	ARTICLE_PRETITLE_FIELD("\\{ms:field.pretitle/\\}"),

	/**
	 * 下一篇文章标题 文章内容标签 {ms:field.nexttitle/}
	 */
	ARTICLE_NEXTTITLE_FIELD("\\{ms:field.nexttitle/\\}"),

	/**
	 * 当前文章内容链接 文章内容链接
	 */
	ARTICLE_LINK_FIELD("\\{ms:field.link/\\}"),
	
	
	/**
	 * 当前文章的描述
	 */
	ARTICLE_DESCRIP_FIELD("\\{ms:field.descrip/\\}"),
	
	/**
	 * 字定义标签{ms.cfield./}
	 */
	ARTICLE_TAGLIB_FIELD("\\{ms:cfield.(.*)?\\s*/}"),
	
	/**
	 * 
	 */
	
	// --------------------文章内容标签结束---------------------------

	// _--------------------栏目标签开始 --------------------------------
	// ---------栏目父标签开始---------
	/**
	 * 查找HTML中栏目列表的正则表达式的开始位置标签 栏目父标签 {ms:channel type=”sun” typeid=””}
	 */
	CHANNEL_BEGIN("\\{ms:channel.*?\\}"),

	/**
	 * 查找HTML中栏目列表的正则表达式的结束位置标签 栏目父标签 {/ms:channel}
	 */
	CHANNEL_END("\\{/ms:channel\\}"),

	// ---------栏目父标签结束---------

	// ---------栏目属性标签开始---------
	/**
	 * 定位栏目标签中所有的属性
	 */
	CHANNEL_PROPERTY("\\{ms:channel(.*)?\\}"),
	// ---------栏目属性标签结束---------

	// ---------栏目子标签开始---------
	/**
	 * 栏目序号 根据显示条数显示的序号1 2 …..10 栏目子标签 [field.typeindex/]
	 */
	CHANNEL_INDEX("\\[field.typeindex/\\]"),

	/**
	 * 标题 栏目名称 栏目子标签 [field.typetitle/]
	 */
	CHANNEL_TITLE("\\[field.typetitle/\\]"),

	/**
	 * 栏目连接 栏目子标签 [field.typelink/]
	 */
	CHANNEL_LINK("\\[field.typelink/\\]"),

	/**
	 * 栏目描述 栏目子标签 [field.typedescrip/]
	 */
	CHANNEL_DESCRIP("\\[field.typedescrip/\\]"),

	/**
	 * 栏目关键字 栏目子标签 [field.typekeyword/]
	 */
	CHANNEL_KEYWORD("\\[field.typekeyword/\\]"),
	// ---------栏目子标签结束---------

	// _--------------------栏目标签结束 --------------------------------

	// ---------------------封面标签开始---------------------------------
	/**
	 * 封面标签(单标签) {ms:channelcont titlelen= typeid=/}
	 */
	CHANNELCONT("\\{ms:contchannel.*?/}"),

	/**
	 * 封面标签的Id属性 类型int 默认当前页面的封面（非必填） {ms:channelcont titlelen= typeid=/}
	 */
	TYPEID_CHANNELCONT("\\{ms:contchannel.*?(typeid\\=(\\d*).{0,})?/}"),

	/**
	 * 封面标签的内容长度属性 类型int 默认当前的所有内容（非必填） {ms:channelcont titlelen= typeid=/}
	 * 
	 */
	TITLELEN_CHANNELCONT("\\{ms:contchannel.*?(titlelen\\=(\\d*).{0,})?/}");

	// ---------------------封面标签结束---------------------------------

	/**
	 * 解析标签的正则表达式
	 */
	private String regex;

	/**
	 * 构造枚举类中的属性
	 * 
	 * @param regex
	 */
	private RegexEnum(String regex) {
		this.regex = regex;
	}

	public String getRegex() {
		return regex;
	}
}
