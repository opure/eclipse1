package com.mingsoft.basic.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.IBasicBiz;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.entity.BasicEntity;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.basic.entity.ModelEntity;
import com.mingsoft.cms.entity.ArticleEntity;
import com.mingsoft.constant.ModelCode;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技流量推广软件</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2013 - 2015
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
 *          Comments:基础应用层
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
@RequestMapping("/manager/basic")
public class BasicAction extends BaseAction {

	/**
	 * 业务层的注入
	 */
	@Autowired
	private IBasicBiz basicBiz;
	
	/**
	 * 业务层的注入
	 */
	@Autowired
	private ICategoryBiz categoryBiz;

	/**
	 * 加载页面显示所有文章信息
	 * 
	 * @param request
	 * @return 返回文章页面显示地址
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/index")
	public String index(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		
		// 获取站点id
		int appId = getManagerBySession(request).getBasicId();
		ModelEntity model = this.getCategoryModelCode(request);
		if (model==null) {
			this.outString(response, this.getResString("err"));
			return null;
		}
		List<CategoryEntity> list = categoryBiz.queryByAppIdOrModelId(appId,model.getModelId() );
		JSONObject ja = new JSONObject();
		request.setAttribute("listCategory", ja.toJSON(list).toString());
		// 返回路径
		return "/manager/basic/index"; 
	}
	
	@RequestMapping("/{categoryId}/list")
	public String list(HttpServletRequest request,@PathVariable int categoryId) {
		String keyWord = request.getParameter("keyword");
		String categoryTitle = request.getParameter("categoryTitle");		
		// 当前页面
		int pageNo = 1;
		// 获取页面的当页数
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		String url =  "/manager/basic/"+categoryId+"/list.do?categoryTitle="+StringUtil.encodeStringByUTF8(categoryTitle)+"&keyword="+(keyWord==null?"":keyWord);
		int count = 0;
		// 分页集合
		PageUtil page = new PageUtil(pageNo, 60, count, getUrl(request) + url);
		// 实例化对象
		List<BasicEntity> basicList = basicBiz.query(this.getAppId(request),categoryId, keyWord, page,this.getModelCodeId(request),null);
		request.setAttribute("basicList", basicList);
		request.setAttribute("categoryId", categoryId);
		return "/manager/basic/basic_list";
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		String categoryId = request.getParameter("categoryId");		
		request.setAttribute("categoryId", categoryId);
		return "/manager/basic/basic";
	}

	@RequestMapping("/save")
	public void save(@ModelAttribute BasicEntity basic, HttpServletRequest request, HttpServletResponse response) {
		basic.setBasicAppId(this.getAppId(request));
		basic.setBasicModelId(this.getModelCodeId(request));
		basicBiz.saveEntity(basic);
		this.outJson(response, null, true);
	}

	@RequestMapping("/{basicId}/edit")
	public String edit(@PathVariable int basicId, HttpServletRequest request) {
		BasicEntity basic = basicBiz.getBasicEntity(basicId);
		request.setAttribute("basic", basic);
		return "/manager/basic/basic";
	}

	@RequestMapping("/update")
	@ResponseBody
	public void update(@ModelAttribute BasicEntity basic, HttpServletRequest request, HttpServletResponse response) {
		basicBiz.updateBasic(basic);
		this.outJson(response, null, true);
	}
	

	@RequestMapping("/{basicId}/delete")
	@ResponseBody
	public void delete(@PathVariable int basicId, HttpServletResponse response, HttpServletRequest request) {
		basicBiz.deleteBasic(basicId);
		this.outJson(response, null, true);
	}
	
	@RequestMapping("/allDelete")
	@ResponseBody
	public void allDelete(HttpServletResponse response, HttpServletRequest request) {
		String checkboxData[] = request.getParameterValues("checkbox");
		if (!StringUtil.isBlank(checkboxData)) {
			for (int i = 0; i < checkboxData.length; i++) {
				int basicId = Integer.parseInt(checkboxData[i]);
				// 获取id，查询该文章是否在该站点下
				basicBiz.deleteBasic(basicId);
			}
		}
		this.outJson(response, null, true);
	}
	
	/**
	 * 获取列表提供给ajax使用
	 * @param response
	 * @param request
	 */
	@RequestMapping("/listForAjax")
	public void listForAjax( HttpServletResponse response, HttpServletRequest request) {
		PageUtil page = new PageUtil(1000);
		List list = basicBiz.query(this.getAppId(request),null, null, page,this.getBasicModelCode(request).getModelId(),null);
		this.outJson(response, JSONArray.toJSONString(list));
	}
	
}
