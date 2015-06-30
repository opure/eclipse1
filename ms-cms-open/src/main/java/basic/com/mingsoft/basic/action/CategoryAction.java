package com.mingsoft.basic.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.constant.ModelCode;
import com.mingsoft.constant.SessionConst;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

/**
 * 
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技MS-MMS</b>
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
 * @author killfen
 * 
 * @version 100-000-000
 * 
 *          <p>
 *          版权所有
 *          </p>
 * 
 *          <p>
 *          Comments:基础应用层　无限极分类
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-6-15
 *          </p>
 * 
 *          <p>
 *          Modification history:暂无
 *          </p>
 */
@Controller
@RequestMapping("/manager/category")
public class CategoryAction extends BaseAction {
	/**
	 * 业务层的注入
	 */
	@Autowired
	private ICategoryBiz categoryBiz;

	
	/**
	 * 栏目添加跳转页面
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request) {
		String categoryCategoryId = request.getParameter("categoryCategoryId"); 
		CategoryEntity category = new CategoryEntity();
		if (!StringUtil.isBlank(categoryCategoryId)) {
			category.setCategoryCategoryId(Integer.parseInt(categoryCategoryId));	
		}
		category.setCategoryManagerId(this.getManagerId(request));
		category.setCategoryModelId(this.getModelCodeId(request));
		List list = categoryBiz.queryChilds(category);
		
		String listJsonString = JSONObject.toJSON(list).toString();
		
		request.setAttribute("categoryCategoryId", categoryCategoryId); //提供给子栏目使用
		request.setAttribute("listCategory", listJsonString);
		request.setAttribute("modelTitle", request.getParameter("modelTitle"));
		request.setAttribute("modelId",request.getParameter("modelId"));
		return "/manager/category/category_form"; 
	}
	
	
	/**
	 * 栏目添加
	 * 
	 * @param category
	 *            栏目对象
	 * @return 返回页面跳转
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute CategoryEntity category,
			HttpServletRequest request, HttpServletResponse response) {

		if (!checkForm(category, response)) {
			return;
		}
		category.setCategoryManagerId(getManagerBySession(request).getManagerId());
		category.setCategoryDateTime(new Timestamp(System.currentTimeMillis()));
		category.setCategoryAppId(this.getAppId(request));
		category.setCategoryModelId(this.getModelCodeId(request));
		categoryBiz.saveCategoryEntity(category);
		this.outJson(response, ModelCode.CMS_COLUMN, true, null,String.valueOf(category.getCategoryId()));
	}


	/**
	 * 栏目更新页面跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/{categoryId}/edit")
	public String edit(@PathVariable int categoryId, HttpServletRequest request) {

		
		// 站点ID
		List<CategoryEntity> list = new ArrayList<CategoryEntity>();
		
		// 判断管理员权限,查询其管理的栏目集合
		CategoryEntity category = new CategoryEntity();
//		category.setCategoryCategoryId(1);
		category.setCategoryManagerId(this.getManagerId(request));
		category.setCategoryModelId(this.getModelCodeId(request));
		list = categoryBiz.queryChilds(category);

		CategoryEntity column = (CategoryEntity) categoryBiz.getEntity(categoryId);
		request.setAttribute("category", column);
		// 获取父栏目对象
		if (column.getCategoryCategoryId() != 0) {
			CategoryEntity columnSuper = (CategoryEntity) categoryBiz.getEntity(column.getCategoryCategoryId());
			request.setAttribute("columnSuper", columnSuper);
		}

		String listJsonString = JSONObject.toJSON(list).toString();
		request.setAttribute("listCategory", listJsonString);
		
		request.setAttribute("modelTitle", request.getParameter("modelTitle"));;
		request.setAttribute("modelId",request.getParameter("modelId"));
		return "/manager/category/category_form";
	}
	
	/**
	 * 栏目更新
	 * @param category  栏目实体
	 * @return 重定向
	 */
	@RequestMapping("/update")
	public void update(@ModelAttribute CategoryEntity category,
			HttpServletRequest request, HttpServletResponse response) {
		if (!checkForm(category, response)) {
			return;
		}
		category.setCategoryManagerId(getManagerBySession(request).getManagerId());
		categoryBiz.updateCategoryEntity(category);
		this.outJson(response, ModelCode.CMS_COLUMN, true, null,JSONArray.toJSONString(category.getCategoryId()));

	}



	/**
	 * 栏目首页面列表显示
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		String modelId = request.getParameter("modelId"); 
		String categoryCategoryId = request.getParameter("categoryCategoryId");//提供展开效果使用
		this.setSession(request, SessionConst.MANAGER_MODEL_CODE, modelId);
		
		//传入一个实体，提供查询条件
		CategoryEntity category  = new CategoryEntity();
		category.setCategoryModelId(Integer.parseInt(modelId));
		category.setCategoryManagerId(this.getManagerId(request));
		PageUtil page = new PageUtil(0,1000,"");
		List list = categoryBiz.queryByPageList(category, page, "category_id", true);
		

		request.setAttribute("categoryCategoryId", categoryCategoryId);
		request.setAttribute("categoryJson", JSONArray.toJSONString(list));
		request.setAttribute("modelTitle", request.getParameter("modelTitle"));
		request.setAttribute("modelId",request.getParameter("modelId"));
		
		return "/manager/category/category_list";
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
		CategoryEntity category = new CategoryEntity();
		category.setCategoryCategoryId(categoryId);
		category.setCategoryManagerId(this.getManagerId(request));
		category.setCategoryModelId(this.getModelCodeId(request));
		
		// 查询该栏目是否有子栏目,如果存在子栏目则返回错误提示，否则删除该栏目
		if (categoryBiz.count(category) > 0) {
			this.outJson(response, ModelCode.CMS_COLUMN, true, "0");
		} else {
			categoryBiz.deleteCategoryEntity(categoryId);
			this.outJson(response, ModelCode.CMS_COLUMN, true, "1");
		}
	}

	private boolean checkForm(CategoryEntity category,
			HttpServletResponse response) {

		// 栏目标题空值验证
		if (StringUtil.isBlank(category.getCategoryTitle())) {
			this.outJson(
					response,
					ModelCode.CMS_COLUMN,
					false,
					getResString("err.empty",
							this.getResString("categoryTitle")));
			return false;
		}
		// 栏目标题长度验证
		if (!StringUtil.checkLength(category.getCategoryTitle(), 1, 31)) {
			this.outJson(
					response,
					ModelCode.CMS_COLUMN,
					false,
					getResString("err.length",
							this.getResString("categoryTitle"), "1", "30"));
			return false;
		}

		return true;
	}
	
	
	/**
	 * 根据分类id查找分类实体和它的父分类
	 * @param categoryId
	 * @param request
	 * @param mode
	 * @param response
	 */
	@RequestMapping("/{categoryId}/query")
	public void query(@PathVariable  int categoryId,HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		//根据分类id查询分类实体
		CategoryEntity category = (CategoryEntity)categoryBiz.getEntity(categoryId);
		//如何分类实体不存在
		if(category==null){
			return;
		}
		//查询该分类的父分类
		CategoryEntity categoryCategory = (CategoryEntity)categoryBiz.getEntity(category.getCategoryCategoryId());
		
		List<CategoryEntity> list = new ArrayList<CategoryEntity>();
		list.add(categoryCategory);
		list.add(category);
		this.outJson(response, JSONObject.toJSONString(list));
	}
	
	
	/**
	 * 根据分类id查找分类子分类
	 * @param categoryId
	 * @param request
	 * @param mode
	 * @param response
	 */
	@RequestMapping("/{categoryId}/queryChildren")
	public void queryChildren(@PathVariable  int categoryId,HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		CategoryEntity category = (CategoryEntity) this.categoryBiz.getEntity(categoryId);
		if(category!=null){
			List<CategoryEntity> list = this.categoryBiz.queryChilds(category);
			this.outJson(response, JSONObject.toJSONString(list));
		}
		
	}
	
}
