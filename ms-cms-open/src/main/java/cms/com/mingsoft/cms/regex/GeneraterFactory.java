package com.mingsoft.cms.regex;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mingsoft.cms.regex.parser.impl.ArticleTypeIdParser;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.cms.biz.IArticleBiz;
import com.mingsoft.cms.biz.IColumnBiz;
import com.mingsoft.cms.biz.IContentModelBiz;
import com.mingsoft.cms.biz.IFieldBiz;
import com.mingsoft.cms.entity.ArticleEntity;
import com.mingsoft.cms.entity.ColumnEntity;
import com.mingsoft.cms.entity.ContentModelEntity;
import com.mingsoft.cms.entity.FieldEntity;
import com.mingsoft.cms.regex.parser.impl.ArticleAuthorParser;
import com.mingsoft.cms.regex.parser.impl.ArticleContentParser;
import com.mingsoft.cms.regex.parser.impl.ArticleDateParser;
import com.mingsoft.cms.regex.parser.impl.ArticleDescripParser;
import com.mingsoft.cms.regex.parser.impl.ArticleHistoryParser;
import com.mingsoft.cms.regex.parser.impl.ArticleIdParser;
import com.mingsoft.cms.regex.parser.impl.ArticleLinkParser;
import com.mingsoft.cms.regex.parser.impl.ArticleLitpicParser;
import com.mingsoft.cms.regex.parser.impl.ArticleSourceParser;
import com.mingsoft.cms.regex.parser.impl.ArticleTitleParser;
import com.mingsoft.cms.regex.parser.impl.ArticleTypeLinkParser;
import com.mingsoft.cms.regex.parser.impl.ArticleTypeTitleParser;
import com.mingsoft.cms.regex.parser.impl.ChannelContParser;
import com.mingsoft.cms.regex.parser.impl.ChannelParser;
import com.mingsoft.cms.regex.parser.impl.GlobalCopyrightParser;
import com.mingsoft.cms.regex.parser.impl.GlobalDescripParser;
import com.mingsoft.cms.regex.parser.impl.GlobalHostParser;
import com.mingsoft.cms.regex.parser.impl.GlobalKeywordParser;
import com.mingsoft.cms.regex.parser.impl.GlobalLogoParser;
import com.mingsoft.cms.regex.parser.impl.GlobalNameParser;
import com.mingsoft.cms.regex.parser.impl.GlobalSkinUrlParser;
import com.mingsoft.cms.regex.parser.impl.GlobalUrlParser;
import com.mingsoft.cms.regex.parser.impl.IncludeParser;
import com.mingsoft.cms.regex.parser.impl.ListParser;
import com.mingsoft.cms.regex.parser.impl.PageNumParser;
import com.mingsoft.cms.regex.parser.impl.PageParser;
import com.mingsoft.cms.regex.parser.impl.ProductCodeParser;
import com.mingsoft.cms.regex.parser.impl.ProductCostPrice;
import com.mingsoft.cms.regex.parser.impl.ProductPriceParser;
import com.mingsoft.cms.regex.parser.impl.ProductSaleParser;
import com.mingsoft.cms.regex.parser.impl.ProductStockParser;
import com.mingsoft.cms.regex.parser.impl.TaglibParser;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

/**
 * 替换标签类
 * 
 * @author 成卫雄 QQ:330216230 技术支持：景德镇铭飞科技 官网：www.ming-soft.com
 */
@Component
public class GeneraterFactory {

	/**
	 * 文章业务层
	 */
	@Autowired
	private IArticleBiz articleBiz;

	/**
	 * 栏目业务层
	 */
	@Autowired
	private IColumnBiz columnBiz;

	/**
	 * 新增字段业务层
	 */
	@Autowired
	private IFieldBiz fieldBiz;

	/**
	 * 内容模型业务层
	 */
	@Autowired
	private IContentModelBiz contentBiz;

	/**
	 * 网站实体
	 */
	private AppEntity website;

	/**
	 * 网站主机连接地址，例如:http://www.ming-soft.com/n/n
	 */
	private String websiteUrl;

	public String getWebsiteUrl() {
		if (!StringUtil.isBlank(mobilePath)) {
			return website.getAppHostUrl() + File.separator + RegexConstant.HTML_SAVE_PATH + File.separator + website.getAppId() + File.separator + mobilePath;
		}
		return website.getAppHostUrl() + File.separator + RegexConstant.HTML_SAVE_PATH + File.separator + website.getAppId();
	}

	/**
	 * 分页大小
	 */
	private PageUtil page;

	/**
	 * 叠加路径，手机端生成有效
	 */
	private String mobilePath = "";

	/**
	 * 根据模版生成页面，除文章标签之外的标签都能生成。
	 * 
	 * @param app
	 *            　应用
	 * @param column
	 *            　当前栏目
	 * @param htmlContent
	 *            　模版内容
	 * @param temSkinPath
	 *            　默默路径
	 * @param mobilePath
	 *            手机端路径名称
	 * @return　解析好的内容
	 */
	public String builder(AppEntity app, ColumnEntity column, String htmlContent, String temSkinPath, String mobilePath) {
		this.mobilePath = mobilePath;
		return this.builder(app, column, htmlContent, temSkinPath);
	}

	/**
	 * 根据模版生成页面，除文章标签之外的标签都能生成。
	 * 
	 * @param app
	 *            　应用
	 * @param column
	 *            　当前栏目
	 * @param htmlContent
	 *            　模版内容
	 * @param temSkinPath
	 *            　默默路径
	 * @return　解析好的内容
	 */
	public String builder(AppEntity app, ColumnEntity column, String htmlContent, String temSkinPath) {
		this.website = app;
		this.websiteUrl = getWebsiteUrl();
		// 读取模版文件内容
		String content = "";
		content = new IncludeParser(htmlContent, temSkinPath + File.separator, this.mobilePath).parse();
		content = parseGlobal(content);
		content = parseArclist(content, 0);
		content = parseChannel(content, column);
		this.mobilePath = "";
		return content;
	}

	/**
	 * 构建移动端文章
	 * 
	 * @param app
	 *            应用
	 * @param column
	 *            栏目
	 * @param article
	 *            文章
	 * @param htmlContent
	 *            模版内容
	 * @param temSkinPath
	 *            模块路径
	 * @param previous
	 *            上一篇
	 * @param next
	 *            下一篇
	 * @param mobilePath
	 *            移动端界面
	 * @return 解析好的内容
	 */
	public String builderArticle(AppEntity app, ColumnEntity column, ArticleEntity article, String htmlContent, String temSkinPath, ArticleEntity previous, ArticleEntity next, String mobilePath) {
		this.mobilePath = mobilePath;
		return this.builderArticle(app, column, article, htmlContent, temSkinPath, previous, next);
	}

	/**
	 * 构建普通文章
	 * 
	 * @param app
	 *            应用
	 * @param column
	 *            栏目
	 * @param article
	 *            文章
	 * @param htmlContent
	 *            模版内容
	 * @param temSkinPath
	 *            模块路径
	 * @param previous
	 *            上一篇
	 * @param next
	 *            下一篇
	 * @param mobilePath
	 *            移动端界面
	 * @return 解析好的内容
	 */
	public String builderArticle(AppEntity website, ColumnEntity column, ArticleEntity article, String htmlContent, String temSkinPath, ArticleEntity previous, ArticleEntity next) {
		this.website = website;
		this.websiteUrl = getWebsiteUrl();
		// 读取模版文件内容
		String content = "";
		content = new IncludeParser(htmlContent, temSkinPath + File.separator, this.mobilePath).parse();
		content = parseGlobal(content);
		content = parseArclist(content, article.getBasicCategoryId());
		content = parseArticle(article, content, previous, next);
		content = parseChannel(content, column);
		this.mobilePath = "";
		return content;
	}

	public String buildSearch(AppEntity website, String htmlContent, String temSkinPath, int pageNo, List<ArticleEntity> articleList, ColumnEntity column, PageUtil page, String mobilePath) {
		this.mobilePath = mobilePath;
		return this.buildSearch(website, htmlContent, temSkinPath, pageNo, articleList,  column, page);
	}

	/**
	 * 构建搜索页面（新增方法）
	 * 
	 * @param website
	 *            站点实体
	 * @param htmlContent
	 *            模板内容
	 * @param temSkinPath
	 *            网站模版路径
	 * @return
	 */
	public String buildSearch(AppEntity website, String htmlContent, String temSkinPath, int pageNo, List<ArticleEntity> articleList, ColumnEntity column, PageUtil page) {
		this.website = website;
		this.websiteUrl = getWebsiteUrl();
		// 读取模版文件内容
		String content = "";
		content = new IncludeParser(htmlContent, temSkinPath + File.separator, this.mobilePath).parse();
		content = parseGlobal(content);
		content = parseSearchArcList(content, articleList);
		content = parseSearchList(content, articleList, page);
		content = parseChannel(content, column);
		this.page = page;
		if (page == null) {
			content = parsePage(content, "");
		} else {
			content = parsePage(content, page.getLinkUrl());
		}

		return content;
	}

	/**
	 * 搜索的分页查询
	 * 
	 * @param htmlContent
	 *            搜索模板内容
	 * @param articleList
	 *            文章实体列表
	 * @param page
	 *            分页
	 * @return
	 */
	public String parseSearchList(String htmlContent, List<ArticleEntity> articleList, PageUtil page) {
		// 当前列表标签中属性的集合-------------------
		Map<String, String> property = ListParser.listProperty(htmlContent, true);
		if (property == null) { // 没有找到分页标签标签
			return htmlContent;
		}
		String isPaging = property.get(RegexConstant.LIST_ISPAGING);
		if (isPaging != null && isPaging.equals("true")) {
			// 排序
			String orderBy = property.get(RegexConstant.LIST_ORDERBY);
			String order = property.get(RegexConstant.LIST_ORDER);
			// 从数据库取出文章列表数组
			List<ArticleEntity> listArticles = articleList;
			// 当数据库中该栏目下没有该文章时不取数据
			if (!StringUtil.isBlank(listArticles)) {
				/**
				 * 判断文章列表的orderby属性
				 */
				if (StringUtil.isBlank(order)) {
					order = "desc";
				}
				// 替换列表标签
				htmlContent = new ListParser(htmlContent, listArticles,  websiteUrl, property, true, fieldBiz, contentBiz).parse();
			}
		}
		return htmlContent;
	}

	/**
	 * 更新栏目列表
	 * 
	 * @param app
	 *            应用
	 * @param column
	 *            　栏目
	 * @param tmpContent
	 *            　模版内容
	 * @param temSkinPath
	 *            　模版路径
	 * @param curPageNo
	 *            　当前页码
	 * @param pagePath
	 *            　分页连接地址
	 * @param mobilePath
	 *            　移动端
	 * @return　解析好的数据
	 */
	public String builderListColumn(AppEntity app, ColumnEntity column, String tmpContent, String temSkinPath, int curPageNo, String pagePath, String mobilePath) {
		this.mobilePath = mobilePath;
		return this.builderListColumn(app, column, tmpContent, temSkinPath, curPageNo, pagePath);
	}

	/**
	 * 构成列表生成器（暂时方法）
	 * 
	 * @param website
	 * @param column
	 * @param tmpContent
	 * @param temSkinPath
	 * @param curPageNo
	 * @return
	 */
	public String builderListColumn(AppEntity website, ColumnEntity column, String tmpContent, String temSkinPath, int curPageNo, String pagePath) {
		this.website = website;
		this.websiteUrl = getWebsiteUrl();
		// 读取模版文件内容
		String content = "";
		content = new IncludeParser(tmpContent, temSkinPath, this.mobilePath).parse();
		content = parseGlobal(content);
		content = parseArclist(content, column.getCategoryId());
		content = parseList(content, column, curPageNo, pagePath);
		content = parseChannel(content, column);
		content = parsePage(content, pagePath);
		this.mobilePath = "";
		return content;
	}

	/**
	 * 查找页面中分页标签中出现的size的值
	 * 
	 * @param htmlContent
	 * @param column
	 * @param curPageNo
	 * @return
	 */
	public int getPageSize(AppEntity website, String htmlContent, ColumnEntity column) {
		this.website = website;
		// 页面总数，默认为1
		int pageSize = 1;
		// 当前列表标签中属性的集合-------------------
		Map<String, String> property = ListParser.listProperty(htmlContent, true);
		// 没有找到分页标签标签
		if (property == null) {
			return pageSize;
		}
		String isPaging = property.get(RegexConstant.LIST_ISPAGING);
		if (!StringUtil.isBlank(isPaging) && isPaging.equals("true")) {
			List<Integer> columnIds = new ArrayList<Integer>();
			// 取出当前栏目下的子栏目Id
			columnIds.add(column.getCategoryId());

			// 列表每页显示的数量
			int size = StringUtil.string2Int(property.get(RegexConstant.LIST_SIZE));
			// 显示文章的形式flag属性
			String flag = property.get(RegexConstant.LIST_FLAG);
			// 显示文章的形式noflag属性
			String noFlag = property.get(RegexConstant.LIST_NOFLAG);
			// 数据库中该栏目下文章的总数
			int articleCount = articleBiz.getCountByColumnId(this.website.getAppId(), columnIds, flag, noFlag);
			// 当用户知道的显示数量小于0或大于文章实际总数时
			if (size <= 0 || size > articleCount) {
				size = articleCount;
			}

			// 如果文章总数为0则分页数量为1
			if (size == 0) {
				pageSize = 1;
				return pageSize;
			}
			pageSize = articleCount % size >= 1 ? articleCount / size + 1 : articleCount / size;
		}
		return pageSize;
	}

	/**
	 * 解析文章内容
	 * 
	 * @param article
	 * @param htmlContent
	 * @param previous
	 * @param next
	 * @return
	 */
	private String parseArticle(ArticleEntity article, String htmlContent, ArticleEntity previous, ArticleEntity next) {

		// 替换文章作者标签：{ms:field.author/}
		htmlContent = new ArticleAuthorParser(htmlContent, article.getArticleAuthor()).parse();

		// 替换文章内容标签：{ms:field.content/}
		htmlContent = new ArticleContentParser(htmlContent, article.getArticleContent()).parse();

		// 替换文章时间标签：{ms:field.date fmt="yyyy-mm-dd"/}
		htmlContent = new ArticleDateParser(htmlContent, article.getBasicDateTime()).parse();

		// 替换文章发布来源标签：{ms:field.source/}
		htmlContent = new ArticleSourceParser(htmlContent, article.getArticleSource()).parse();

		// 替换文章标题标签： {ms:field.title/}
		htmlContent = new ArticleTitleParser(htmlContent, article.getBasicTitle()).parse();

		// 替换文章id标签： {ms:field.id/}
		htmlContent = new ArticleIdParser(htmlContent, article.getBasicId() + "").parse();

		// 替换文章描述： {ms:field.Descrip/}
		htmlContent = new ArticleDescripParser(htmlContent, article.getBasicDescription()).parse();

		// 替换文章缩略图标签
		htmlContent = new ArticleLitpicParser(htmlContent, article.getBasicThumbnails()).parse();
		// 获取文章所属的栏目实体
		ColumnEntity column = (ColumnEntity) columnBiz.getEntity(article.getBasicCategoryId());

		// 替换文章栏目链接标签{ms:filed.typelink/}
		ColumnEntity tmp = null;
		htmlContent = new ArticleTypeIdParser(htmlContent, column.getCategoryId() + "").parse();
		ArticleTypeTitleParser attp = new ArticleTypeTitleParser(htmlContent, column.getCategoryTitle());
		if (attp.isTop()) {
			if (column.getCategoryCategoryId() > 0) {
				tmp = (ColumnEntity) columnBiz.getEntity(column.getCategoryCategoryId());
				attp.setNewCotent(tmp.getCategoryTitle());
			}
		} else {
			attp.setNewCotent(column.getCategoryTitle());
		}
		htmlContent = attp.parse();

		// 替换文章栏目链接标签{ms:filed.typelink/}
		ArticleTypeLinkParser atlp = new ArticleTypeLinkParser(htmlContent, websiteUrl + column.getColumnPath() + File.separator + RegexConstant.HTML_INDEX);
		if (atlp.isTop()) {
			if (tmp == null && column.getCategoryCategoryId() > 0) { // 如果用户写分类名称标签的时候没有使用top属性，而在使用连接标签的时候使用就再次查询分类
				tmp = (ColumnEntity) columnBiz.getEntity(column.getCategoryCategoryId());
			}
			atlp.setNewCotent(websiteUrl + tmp.getColumnPath() + File.separator + RegexConstant.HTML_INDEX);
		}
		htmlContent = atlp.parse();

		// 替换当前文章内容链接标签：{ms:field.link}
		htmlContent = new ArticleLinkParser(htmlContent, article.getArticleLinkURL()).parse();

		// 替换上一篇文章、下一篇文章，链接、标题，标签
		htmlContent = new ArticleHistoryParser(htmlContent, previous, next).parse();

		// 判断该文章是否有新增字段
		if (column.getColumnContentModelId() != 0) {
			// 根据表单类型id查找出所有的字段信息
			List<BaseEntity> listField = fieldBiz.queryListByCmid(column.getColumnContentModelId());
			// 遍历所有的字段实体,得到字段名列表信息
			List<String> listFieldName = new ArrayList<String>();
			for (int i = 0; i < listField.size(); i++) {
				FieldEntity field = (FieldEntity) listField.get(i);
				listFieldName.add(field.getFieldFieldName());
			}
			ContentModelEntity contentModel = (ContentModelEntity) contentBiz.getEntity(column.getColumnContentModelId());
			// 组织where条件
			Map<String, Integer> where = new HashMap<String, Integer>();
			where.put("basicId", article.getBasicId());
			// 获取各字段的值
			List fieldLists = fieldBiz.queryBySQL(contentModel.getCmTableName(), listFieldName, where);
			Map filedValue = (Map) fieldLists.get(0);
			if (filedValue != null) {
				htmlContent = new TaglibParser(htmlContent, filedValue, column.getColumnContentModelId(), fieldBiz).parse();
			}

			// 读取并解析各标签内容
		}
		return htmlContent;
	}

	/**
	 * 解析全局标签
	 * 
	 * @param htmlContent
	 *            模版内容
	 * @param path
	 *            模版叠加路径
	 * @return　替换好的内容
	 */
	private String parseGlobal(String htmlContent) {
		// 替换网站版权信息标签：{ms: global.copyright/}
		htmlContent = new GlobalCopyrightParser(htmlContent, website.getAppCopyright()).parse();

		// 替换网站关键字标签:{ms: global.keyword/}
		htmlContent = new GlobalKeywordParser(htmlContent, website.getAppKeyword()).parse();

		// 替换网站LOGO标签：{ms: global.logo/}
		htmlContent = new GlobalLogoParser(htmlContent, website.getAppLogo()).parse();

		// 替换网站名称标签：{ms:global.name /}
		htmlContent = new GlobalNameParser(htmlContent, website.getAppName()).parse();

		// 替换模版链接地址标签：{ms: globalskin.url/}
		String tmpSkinUrl = website.getAppHostUrl() + File.separator + RegexConstant.REGEX_SAVE_TEMPLATE + File.separator + website.getAppId() + File.separator + website.getAppStyle() + File.separator + this.mobilePath;
		htmlContent = new GlobalSkinUrlParser(htmlContent, tmpSkinUrl).parse();

		// 替换网站链接地址标签:{ms:global.url/}
		htmlContent = new GlobalUrlParser(htmlContent, website.getAppHostUrl() + File.separator + RegexConstant.HTML_SAVE_PATH + File.separator + website.getAppId() + File.separator + this.mobilePath + File.separator).parse();

		// 替换网站链接地址标签:{ms:global.host/}
		htmlContent = new GlobalHostParser(htmlContent, website.getAppHostUrl() + File.separator).parse();

		// 替换网站描述标签:{ms: global.descrip/}
		htmlContent = new GlobalDescripParser(htmlContent, website.getAppDescription()).parse();
		return htmlContent;
	}

	/**
	 * 解析列表标签
	 * 
	 * @param htmlContent
	 *            模版内容
	 * @param linkColumnId
	 *            栏目编号
	 * @return　替换好的内容
	 */
	private String parseArclist(String htmlContent, int linkColumnId) {

		// 查找当前模版页面拥有多少个列表标签
		int listNum = ListParser.countArcList(htmlContent);
		// 替换完分页标签后的HTML代码
		for (int i = 0; i < listNum; i++) {
			// 当前列表标签中属性的集合-------------------
			Map<String, String> property = ListParser.listProperty(htmlContent, false);
			// 取当前标签下的栏目ID
			int columnId = StringUtil.string2Int(property.get(RegexConstant.LIST_TYPEID));

			List<Integer> columnIds = new ArrayList<Integer>();

			// 列表每页显示的数量
			int size = StringUtil.string2Int(property.get(RegexConstant.LIST_SIZE));
			// 显示文章的形式flag属性
			String flag = property.get(RegexConstant.LIST_FLAG);
			// 显示文章的形式noflag属性
			String noFlag = property.get(RegexConstant.LIST_NOFLAG);

			// 排序
			String orderBy = property.get(RegexConstant.LIST_ORDERBY);
			String order = property.get(RegexConstant.LIST_ORDER);
			// 取出当前栏目下的子栏目Id
			if (columnId != 0) {
				columnIds = columnBiz.queryChildIdsByColumnId(columnId);
				columnIds.add(columnId);
			} else {
				columnId = linkColumnId;
				columnIds = columnBiz.queryChildrenCategoryIds(columnId);
			}
			// 数据库中该栏目下文章的总数
			int articleCount = articleBiz.getCountByColumnId(website.getAppId(), columnIds, flag, noFlag);

			// 如果没有指定文章每页显示数量则显示所有数量
			if (size <= 0 || size > articleCount) {
				size = articleCount;
			}
			// 当数据库中该栏目下没有该文章时不取数据
			if (articleCount != 0) {
				/**
				 * 判断文章列表的orderby属性
				 */
				if (StringUtil.isBlank(order)) {
					order = "desc";
				}
				// 从数据库取出文章列表数组
				List<ArticleEntity> listArticles = articleBiz.queryList(website.getAppId(), columnIds, flag, noFlag, 0, size, orderBy, order.equals("desc") ? true : false);
				// 替换列表标签
				htmlContent = new ListParser(htmlContent, listArticles,  websiteUrl, property, false, fieldBiz, contentBiz).parse();
			} else {
				htmlContent = new ListParser(htmlContent, null,  websiteUrl, property, false, fieldBiz, contentBiz).parse();
			}
		}
		return htmlContent;
	}

	/**
	 * 解析分页列表标签
	 * 
	 * @param htmlContent
	 *            模版内容
	 * @param column
	 *            栏目编号
	 * @param curPageNo
	 *            当前页码
	 * @return　替换好的内容
	 */
	private String parseList(String htmlContent, ColumnEntity column, int curPageNo, String pagePath) {
		// 替换完分页标签后的HTML代码
		// 当前列表标签中属性的集合-------------------
		Map<String, String> property = ListParser.listProperty(htmlContent, true);
		if (property == null) { // 没有找到分页标签标签
			return htmlContent;
		}
		String isPaging = property.get(RegexConstant.LIST_ISPAGING);
		if (isPaging != null && isPaging.equals("true")) {
			List<Integer> columnIds = columnBiz.queryChildrenCategoryIds(column.getCategoryId());
			// 取出当前栏目下的子栏目Id

			// 列表每页显示的数量
			int size = StringUtil.string2Int(property.get(RegexConstant.LIST_SIZE));
			// 显示文章的形式flag属性
			String flag = property.get(RegexConstant.LIST_FLAG);
			// 显示文章的形式noflag属性
			String noFlag = property.get(RegexConstant.LIST_NOFLAG);

			// 排序
			String orderBy = property.get(RegexConstant.LIST_ORDERBY);
			String order = property.get(RegexConstant.LIST_ORDER);

			// 数据库中该栏目下文章的总数
			int articleCount = articleBiz.getCountByColumnId(website.getAppId(), columnIds, flag, noFlag);

			// 如果没有指定文章每页显示数量则显示所有数量
			if (size <= 0 || size > articleCount) {
				size = articleCount;
			}
			page = new PageUtilHtml(curPageNo, size, articleCount, pagePath);
			// 当数据库中该栏目下没有该文章时不取数据
			if (articleCount != 0) {
				/**
				 * 判断文章列表的orderby属性
				 */
				if (StringUtil.isBlank(order)) {
					order = "desc";
				}
				// 从数据库取出文章列表数组
				List<ArticleEntity> listArticles = articleBiz.queryList(this.website.getAppId(), columnIds, flag, noFlag, (page.getPageNo() * page.getPageSize()), page.getPageSize(), orderBy, order.equals("desc") ? true : false);
				// 替换列表标签
				htmlContent = new ListParser(htmlContent, listArticles, websiteUrl, property, true, fieldBiz, contentBiz).parse();
			} else {
				htmlContent = new ListParser(htmlContent, null, websiteUrl, property, true, fieldBiz, contentBiz).parse();
			}
		}
		return htmlContent;
	}

	/**
	 * 解析分页标签
	 * 
	 * @param htmlContent
	 *            　原始模版
	 * @param pageNo
	 *            　当期页码
	 * @param total
	 *            　总记录数
	 * @param url
	 *            　连接地址
	 * @return
	 */
	private String parsePage(String htmlContent, String url) {
		// 替换分页标签
		htmlContent = new PageParser(htmlContent, page).parse();
		// 替换页面的总数，当前文章处于第几页，列表文章的总数标签
		htmlContent = new PageNumParser(htmlContent, page).parse();
		return htmlContent;
	}

	/**
	 * 解析分类标签
	 * 
	 * @param htmlContent
	 *            　原始html内容
	 * @param column
	 *            　
	 * @param websiteUrl
	 *            网站连接地址
	 * @return
	 */
	private String parseChannel(String htmlContent, ColumnEntity column) {
		// 替换完文章标签后的HTML模版

		// 当只存在栏目ID时，解析相关的文章中的栏目标签
		if (column != null) {
			ColumnEntity tmp = null;
			String columnTitle = column.getCategoryTitle();
			// 解析当前栏目id
			htmlContent = new ArticleTypeIdParser(htmlContent, column.getCategoryId() + "").parse();

			// 替换文章所在栏目标签：{ms:field.typetitle/}
			ArticleTypeTitleParser attp = new ArticleTypeTitleParser(htmlContent, columnTitle);
			if (attp.isTop()) {
				if (column.getCategoryCategoryId() > 0) {
					tmp = (ColumnEntity) columnBiz.getEntity(column.getCategoryCategoryId());
					columnTitle = tmp.getCategoryTitle();
				}
			}
			htmlContent = attp.setNewCotent(columnTitle).parse();

			// 替换文章栏目链接标签{ms:filed.typelink/}
			ArticleTypeLinkParser atlp = new ArticleTypeLinkParser(htmlContent, websiteUrl + column.getColumnPath() + File.separator + RegexConstant.HTML_INDEX);
			if (atlp.isTop()) {
				if (tmp == null && column.getCategoryCategoryId() > 0l) { // 如果用户写分类名称标签的时候没有使用top属性，而在使用连接标签的时候使用就再次查询分类
					tmp = (ColumnEntity) columnBiz.getEntity(column.getCategoryCategoryId());
				}
				atlp.setNewCotent(websiteUrl + tmp.getColumnPath() + File.separator + RegexConstant.HTML_INDEX);
			}
			htmlContent = atlp.parse();
		}
		// //----------------------------解析栏目标签----------------------------

		// //替换完列表标签后的HTML文件
		String channel = htmlContent;

		// 查找当前模版页面拥有多少个栏目列表标签
		int strNumType = ChannelParser.channelNum(channel);

		for (int i = 0; i < strNumType; i++) {
			// 当前列表栏目中属性的集合
			Map<String, String> mapProperty = ChannelParser.channelProperty(channel);

			// 取当前标签下的栏目ID
			int tempColumnId = StringUtil.string2Int(mapProperty.get(ChannelParser.CHANNEL_TYPEID));

			if (tempColumnId == 0 && column != null) {
				tempColumnId = column.getCategoryId();
			}
			List<ColumnEntity> categoryList = null;
			if (tempColumnId != 0) {
				// 取出栏目的取值范围
				String type = mapProperty.get(ChannelParser.CHANNEL_TYPE);

				// 根据范围在BIZ中取出不同的栏目信息

				// 判断用户填写的栏目属性，如果未填写那么取当前栏目的下级栏目，如果但前栏目没有下级栏目那么晚取本级栏目
				// 如果填写:son,那么取下级栏目，没有下级栏目则取本级栏目
				// 如果为：top,那么取上级栏目，如果没有上级栏目则取本级栏目
				// 如果为：level,则取本级栏目
				if (type == null) {
					categoryList = columnBiz.queryChildListByColumnId(tempColumnId);
				} else if (type.equals(ChannelParser.CHANNEL_TYPE_SON)) {

					categoryList = columnBiz.queryChildListByColumnId(tempColumnId);
				} else if (type.equals(ChannelParser.CHANNEL_TYPE_TOP)) {
					categoryList = columnBiz.queryTopSiblingListByColumnId(tempColumnId);
				} else if (type.equals(ChannelParser.CHANNEL_TYPE_LEVEL)) {
					categoryList = columnBiz.querySibling(tempColumnId);
				}
				if (categoryList == null || categoryList.size() == 0) {
					// articleTypeList =
					// columnBiz.querySiblingListByColumnId(tempColumnId);
				}

				// 替换栏目标签
				htmlContent = new ChannelParser(channel, categoryList, websiteUrl, column != null ? column.getCategoryId() : 0, mapProperty.get(ChannelParser.CHANNEL_CLASS)).parse();
				// 替换完栏目标签后的HTML代码
				channel = htmlContent;
			} else {
				categoryList = columnBiz.queryChild(tempColumnId, website.getAppId(), null);
				// 替换栏目标签
				htmlContent = new ChannelParser(channel, categoryList, websiteUrl).parse();
				// 替换完栏目标签后的HTML代码
				channel = htmlContent;
			}
		}

		// 替换完封面标签后的TML文件
		String channelContHtml = channel;
		// 查找当前模版页面拥有多少个封面列表标签
		int channelConNum = ChannelContParser.channelContNum(channelContHtml);

		for (int i = 0; i < channelConNum; i++) {
			// 取出当前封面标签中的封面ID
			int channelTypeId = ChannelContParser.channelContTypeId(channelContHtml);
			if (channelTypeId == 0 && column != null) {
				channelTypeId = column.getCategoryId();
			}
			String channelCont = "";
			// 取出当前封面的内容
			// 取出当前封面的内容
			if (channelTypeId != 0) {
				List<ArticleEntity> arctile = articleBiz.queryListByColumnId(channelTypeId);
				if (arctile != null) {
					if (arctile.size() > 0) {
						channelCont = arctile.get(arctile.size() - 1).getArticleContent();
					} else {
						channelCont = arctile.get(arctile.size()).getArticleContent();
					}

				}
			}
			// 替换封面标签
			htmlContent = new ChannelContParser(channelContHtml, channelCont).parse();
			channelContHtml = htmlContent;
		}

		return htmlContent;
	}

	/**
	 * 对搜索列表中的文章列表标签进行解析（新增方法）
	 * 
	 * @param htmlContent
	 * @param articleId
	 * @return
	 */
	public String parseSearchArcList(String htmlContent, List<ArticleEntity> articleList) {
		// 查找当前模版页面拥有多少个列表标签
		int listNum = ListParser.countArcList(htmlContent);
		// 替换完分页标签后的HTML代码
		for (int i = 0; i < listNum; i++) {
			// 当前列表标签中属性的集合-------------------
			Map<String, String> property = ListParser.listProperty(htmlContent, false);

			List<Integer> columnIds = new ArrayList<Integer>();

			// 列表每页显示的数量
			int size = StringUtil.string2Int(property.get(RegexConstant.LIST_SIZE));
			// 显示文章的形式flag属性
			String flag = property.get(RegexConstant.LIST_FLAG);
			// 显示文章的形式noflag属性
			String noFlag = property.get(RegexConstant.LIST_NOFLAG);
			// 排序
			String orderBy = property.get(RegexConstant.LIST_ORDERBY);
			String order = property.get(RegexConstant.LIST_ORDER);
			// 取当前标签下的栏目ID
			int columnId = StringUtil.string2Int(property.get(RegexConstant.LIST_TYPEID));
			// 从数据库取出文章列表数组
			List<ArticleEntity> listArticles = new ArrayList<ArticleEntity>();
			int articleCount = 0;
			/*
			 * 判断栏目id是否指定 如果指定则取该栏目下的文章,否则取符合搜索条件的文章
			 */
			if (columnId != 0) {
				columnIds = columnBiz.queryChildIdsByColumnId(columnId);
				columnIds.add(columnId);
				// 数据库中该栏目下文章的总数
				articleCount = articleBiz.getCountByColumnId(website.getAppId(), columnIds, flag, noFlag);
				listArticles = articleBiz.queryList(website.getAppId(), columnIds, flag, noFlag, 0, size, orderBy, true);
			} else {
				listArticles = articleList;
			}
			// 判断是否存在符合条件的文章
			if (!StringUtil.isBlank(listArticles)) {
				articleCount = listArticles.size();
			}
			// 如果没有指定文章每页显示数量则显示所有数量
			if (size <= 0 || size > articleCount) {
				size = articleCount;
			}
			// 当数据库中该栏目下没有该文章时不取数据
			if (articleCount != 0) {
				/**
				 * 判断文章列表的orderby属性
				 */
				if (StringUtil.isBlank(order)) {
					order = "desc";
				}
				// 替换列表标签
				htmlContent = new ListParser(htmlContent, listArticles, websiteUrl, property, false, fieldBiz, contentBiz).parse();
			}
		}
		return htmlContent;
	}
}
