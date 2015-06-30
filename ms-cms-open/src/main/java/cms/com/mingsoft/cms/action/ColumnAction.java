package com.mingsoft.cms.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.entity.ManagerEntity;
import com.mingsoft.cms.biz.IColumnBiz;
import com.mingsoft.cms.biz.IContentModelBiz;
import com.mingsoft.cms.entity.ColumnEntity;
import com.mingsoft.cms.regex.RegexConstant;
import com.mingsoft.constant.Const;
import com.mingsoft.constant.ModelCode;
import com.mingsoft.constant.SessionConst;
import com.mingsoft.util.StringUtil;

/**
 * 
 * 
 * <p>
 * <b>铭飞CMS-铭飞内容管理系统</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 刘继平
 * 
 * @version 300-001-001
 * 
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments: 栏目控制层，继承BasicAction
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-7-15
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
@Controller
@RequestMapping("/manager/cms/column")
public class ColumnAction extends BaseAction {

	/**
	 * 业务层的注入
	 */
	@Autowired
	private IColumnBiz columnBiz;
	
	/**
	 * 业务层的注入
	 */
	@Autowired
	private ICategoryBiz categoryBiz;
	
	
	/**
	 * 业务层的注入表单内容模型
	 */
	@Autowired
	private IContentModelBiz contentModelBiz;

	/**
	 * 栏目添加
	 * 
	 * @param column
	 *            栏目对象
	 * @return 返回页面跳转
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute ColumnEntity column,
			HttpServletRequest request,HttpServletResponse response) {
		if(!checkForm(column,response)){
			return;
		}
		column.setCategoryAppId(getManagerBySession(request).getBasicId());
		column.setColumnWebsiteId(getManagerBySession(request).getBasicId());
		column.setCategoryManagerId(getManagerBySession(request).getManagerId());
		column.setCategoryDateTime(new Timestamp(System.currentTimeMillis()));
		column.setCategoryModelId(this.getModelCodeId(request));
		if(column.getColumnType() !=ColumnEntity.COLUMN_TYPE_LIST ){
			column.setColumnListUrl("");
		}
		columnBiz.saveCategory(column);
		this.columnPath(request,column);
		this.outJson(response, ModelCode.CMS_COLUMN, true,null,JSONArray.toJSONString(column.getCategoryId()));
	}

	/**
	 * 栏目添加跳转页面
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request) {
		ManagerEntity managerSession = (ManagerEntity) getSession(request, SessionConst.MANAGER_ESSION);
		// 站点ID
		int appId =this.getAppId(request);
		List<ColumnEntity> list = columnBiz.queryAll(appId, this.getModelCodeId(request));
		// 查询属于当前管理员的内容模型
		List<BaseEntity> listCm = contentModelBiz.queryByManagerId(managerSession.getManagerId());
		request.setAttribute("listColumn", JSONObject.toJSON(list).toString());
		request.setAttribute("listCm", listCm);
		return "/manager/cms/column/column_add";
	}

	/**
	 * 栏目首页面列表显示
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
			
		// 站点ID有session获取
		int websiteId = getManagerBySession(request).getBasicId();
		// 需要打开的栏目节点树的栏目ID
		int categoryId = 0;
		List<ColumnEntity> list = new ArrayList<ColumnEntity>();

		Object cId = request.getParameter("categoryId");
		if (!StringUtil.isBlank(cId)) {
			categoryId = Integer.parseInt(cId.toString());
		}
		// 是否为顶级栏目
		//if (categoryId == Const.COLUMN_TOP_CATEGORY_ID) {
			list = columnBiz.queryAll(websiteId, this.getModelCodeId(request));// columnBiz.queryChild(categoryId,websiteId,this.getModelCodeId(request));
//		} else {
//			list = columnBiz.queryColumnChildListRecursionByWebsiteId(categoryId, websiteId);
//		}

		
		//栏目链接标签拼接字符串
		List<String> listStr = new ArrayList<String>();
		listStr.add(RegexConstant.HTML_LIST);
		listStr.add(RegexConstant.REGEX_COVER_URL);
		listStr.add(RegexConstant.HTML_SAVE_PATH);
		request.setAttribute("columnRegexConstant", JSONArray.toJSONString(listStr));

		request.setAttribute("listColumn", JSONArray.toJSONString(list));
		return "/manager/cms/column/column_list";
	}

	/**
	 * 子栏目列表显示
	 */
	@RequestMapping("/{categoryId}/childList")
	public void childList(@PathVariable int categoryId,
			HttpServletResponse response, HttpServletRequest request) {
		
		// 站点ID有session获取
		int websiteId = getManagerBySession(request).getBasicId();
		// 需要打开的栏目节点树的栏目ID
		List<ColumnEntity> list = new ArrayList<ColumnEntity>();

		list = columnBiz.queryChild(categoryId, websiteId,this.getModelCodeId(request));

		response.setCharacterEncoding("utf-8");
		this.outJson(response, ModelCode.CMS_COLUMN, true, "", JSONObject.toJSON(list).toString());
	}

	/**
	 * 根据栏目ID删除栏目记录
	 * 
	 * @param request
	 * @return 重定向
	 */
	@RequestMapping("/{categoryId}/delete")
	public void delete(@PathVariable int categoryId,
			HttpServletResponse response, HttpServletRequest request) {
		
		// 站点ID有session获取
		int websiteId = getManagerBySession(request).getBasicId();
		// 查询该栏目是否有子栏目,如果存在子栏目则返回错误提示，否则删除该栏目
		if (columnBiz.queryChild(categoryId, websiteId,this.getModelCodeId(request)).size() > 0) {
			this.outJson(response, ModelCode.CMS_COLUMN, true, "false");
		} else {
			columnBiz.deleteCategory(categoryId);
			this.outJson(response, ModelCode.CMS_COLUMN, true, "true");
		}
	}
	/**
	 * 根据栏目ID进行栏目删除确认，如果有子栏目则不能被删除
	 * @param categoryId 栏目ID
	 * @param response
	 * @param request
	 */
	@RequestMapping("/{categoryId}/deleteConfirm")
	public void deleteConfirm(@PathVariable int categoryId,
			HttpServletResponse response, HttpServletRequest request){
		// 站点ID有session获取
				int websiteId = getManagerBySession(request).getBasicId();
				// 查询该栏目是否有子栏目,如果存在子栏目则返回错误提示，否则删除该栏目
				if (columnBiz.queryColumnChildListCountByWebsiteId(categoryId, websiteId) > 0) {
					this.outJson(response, ModelCode.CMS_COLUMN, true, "false");
				} else {
					this.outJson(response, ModelCode.CMS_COLUMN, true, "true");
				}
	}
	
	/**
	 * 栏目路径
	 * @param column 栏目实体
	 */
	public void columnPath(HttpServletRequest request,ColumnEntity column){
		String columnPath = "";
		String file = this.getRealPath(request,null)+RegexConstant.HTML_SAVE_PATH+File.separator+ column.getColumnWebsiteId();
		String delFile = "";
		//修改栏目路径时，删除已存在的文件夹
		column = (ColumnEntity) columnBiz.getEntity(column.getCategoryId());
		delFile = file + column.getColumnPath();
		if(!StringUtil.isBlank(delFile)){
			File delFileName = new File(delFile);
			delFileName.delete();
		}
		//若为顶级栏目，则路径为：/+栏目ID
		if(column.getCategoryCategoryId() == 0){
			column.setColumnPath(File.separator+column.getCategoryId());
			file = file + File.separator + column.getCategoryId();
		} else {
			List<ColumnEntity> list = columnBiz.queryParentColumnByColumnId(column.getCategoryId());
			if(!StringUtil.isBlank(list)){
				String temp = "";
				for(int i = list.size()-1; i>=0; i--){
					ColumnEntity entity = list.get(i);
					columnPath = columnPath + File.separator + entity.getCategoryId();
					temp = temp + File.separator + entity.getCategoryId();
				}
				column.setColumnPath(columnPath + File.separator + column.getCategoryId());
				file = file + temp + File.separator + column.getCategoryId();
			}
		}
		columnBiz.updateEntity(column);
		//生成文件夹
		File fileName = new File(file);
        fileName.mkdir();
	}
	
	/**
	 * 栏目更新
	 * 
	 * @param column
	 *            栏目实体
	 * @return 重定向
	 */
	@RequestMapping("/update")
	public void update(@ModelAttribute ColumnEntity column,
			HttpServletRequest request,HttpServletResponse response) {
		
		if(!checkForm(column,response)){
			return;
		}
		
		
		column.setCategoryManagerId(getManagerBySession(request).getManagerId());
		column.setColumnWebsiteId(getManagerBySession(request).getBasicId());
		columnBiz.updateCategory(column);
		// 获取新的栏目
		ColumnEntity newColumn = (ColumnEntity) columnBiz.getEntity(column.getCategoryId());
		
		this.columnPath(request,column);
		this.outJson(response, ModelCode.CMS_COLUMN, true,null,JSONArray.toJSONString(column.getCategoryId()));
	
	}

	/**
	 * 栏目更新页面跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/{columnId}/edit")
	public String edit(@PathVariable int columnId, HttpServletRequest request) {
		// 获取管理实体
		ManagerEntity managerSession = (ManagerEntity) getSession(request, SessionConst.MANAGER_ESSION);
		// 站点ID
		int appId = this.getAppId(request);
		List<ColumnEntity> list = new ArrayList<ColumnEntity>();
		// 判断管理员权限,查询其管理的栏目集合
		list = columnBiz.queryAll(appId, this.getModelCodeId(request));

		ColumnEntity column = (ColumnEntity) columnBiz.getEntity(columnId);
		// 查询属于当前管理员的内容模型
		List<BaseEntity> listCm = contentModelBiz.queryByManagerId(managerSession.getManagerId());
		request.setAttribute("column", column);
		request.setAttribute("columnc", column.getCategoryId());
		request.setAttribute("listCm", listCm);
		// 获取父栏目对象
		if (column.getCategoryCategoryId() != Const.COLUMN_TOP_CATEGORY_ID) {
			ColumnEntity columnSuper = (ColumnEntity) columnBiz
					.getEntity(column.getCategoryCategoryId());
			request.setAttribute("columnSuper", columnSuper);
		}
		request.setAttribute("listColumn", JSONObject.toJSON(list).toString());
		return "/manager/cms/column/column_edit";
	}

	/**
	 * 查询全部栏目集合 使用queryJsonAll替代
	 * 
	 * @param response
	 */
	@Deprecated
	@RequestMapping("/columnList")
	public void columnList(HttpServletResponse response,
			HttpServletRequest request) {
		
		// 该站点ID有session提供
		int websiteId = getManagerBySession(request).getBasicId();
		List<ColumnEntity> list  = columnBiz.queryColumnListByWebsiteId(websiteId);
		response.setCharacterEncoding("utf-8");
		this.outJson(response, ModelCode.CMS_COLUMN, true, "", JSONObject.toJSON(list).toString());
	}
	
	@RequestMapping("/queryJsonAll")
	public void queryJsonAll(HttpServletResponse response,
			HttpServletRequest request) {
		
		// 该站点ID有session提供
		int websiteId = getManagerBySession(request).getBasicId();
		List<ColumnEntity> list  = columnBiz.queryColumnListByWebsiteId(websiteId);
		response.setCharacterEncoding("utf-8");
		this.outJson(response, ModelCode.CMS_COLUMN, true, "", JSONObject.toJSON(list).toString());
	}
	
	/**
	 * 查询出商品的分类
	 * @param response
	 * @param request
	 */
	@RequestMapping("/queryProductColumn")
	public void queryProductColumn(HttpServletResponse response,HttpServletRequest request){
		// 该站点ID有session提供
				int appId = getManagerBySession(request).getBasicId();
				//根据modelId查询商品分类
				List<ColumnEntity> list = columnBiz.queryAll(appId, this.getModelCodeId(request,ModelCode.MALL_CATEGORY));
				response.setCharacterEncoding("utf-8");
				this.outJson(response, ModelCode.CMS_COLUMN, true, "", JSONObject.toJSON(list).toString());
	}
	
	private boolean checkForm(ColumnEntity column, HttpServletResponse response){
		

			//栏目标题空值验证
			if(StringUtil.isBlank(column.getCategoryTitle())){
				this.outJson( response, ModelCode.CMS_COLUMN, false, getResString("err.empty", this.getResString("categoryTitle")));
				return false;
			}
			//栏目标题长度验证
			if(!StringUtil.checkLength(column.getCategoryTitle(), 1, 31)){
				this.outJson( response, ModelCode.CMS_COLUMN, false, getResString("err.length", this.getResString("categoryTitle"), "1", "30"));
				return false;
			}
			//栏目属性空值验证
			if(StringUtil.isBlank(column.getColumnType())){
				this.outJson( response, ModelCode.CMS_COLUMN, false, getResString("err.empty", this.getResString("columnType")));
				return false;
			}
			//栏目描述处理
			if(StringUtil.checkLength(column.getColumnDescrip(), 0, 500)){
				column.setColumnDescrip(StringUtil.subString(column.getColumnDescrip(), 500));
			}
			//栏目简介处理
			if(StringUtil.checkLength(column.getColumnKeyword(), 0, 500)){
				column.setColumnKeyword(StringUtil.subString(column.getColumnKeyword(), 500));
			}
			
			return true;
	}

}
