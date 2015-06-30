package com.mingsoft.basic.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.IManagerBiz;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.entity.ManagerEntity;
import com.mingsoft.basic.entity.ModelEntity;
import com.mingsoft.constant.ModelCode;
import com.mingsoft.util.StringUtil;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技基础框架</b>
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
 * @author 史爱华
 * 
 * @version 100-000-000
 * 
 *          <p>
 *          版权所有
 *          </p>
 * 
 *          <p>
 *          Comments:模块控制
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-6-29
 *          </p>
 * 
 *          <p>
 *          Modification history:暂无
 *          </p>
 */
@Controller
@RequestMapping("/manager/model")
public class ModelAction extends BaseAction {

	/**
	 * 注入模块业务层
	 */
	@Autowired
	private IModelBiz modelBiz;
	
	@Autowired
	private IManagerBiz managerBiz;

	/**
	 * 增加模块
	 * 
	 * @param request
	 * @param response
	 * @return 返回页面
	 */
	@RequestMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute ModelEntity model,
			HttpServletRequest request, HttpServletResponse response) {
		//获取管理员id并赋值给模块的id
		model.setModelId(getManagerId(request));
		// 获取模块保存时间
		model.setModelDatetime(new Timestamp(System.currentTimeMillis()));
		if (!StringUtil.checkLength(model.getModelTitle(), 1, 20) ) {
			this.outJson(response,getResString("err.length",this.getResString("modelTitle"),"2","20"));
			return;
		}
		if(!StringUtil.checkLength(model.getModelCode(), 1, 20)){
			this.outJson(response,getResString("err.length",this.getResString("modelCode"),"2","20"));
			return;
		}
		
		//判断图标是否为空，不为空去掉,图标地址中含有的“|”
		//空值判断
		if(!StringUtil.isBlank(model.getModelIcon())) {
			model.setModelIcon( model.getModelIcon().replace("|", ""));
		}
		modelBiz.saveEntity(model);
		//返回模块id到页面
		this.outJson(response, ModelCode.ROLE, true,String.valueOf(model.getModelId()));
	}

	/**
	 * 保存模块
	 * 
	 * @return 返回页面
	 */
	@RequestMapping("/queryAll")
	public void queryAll(HttpServletResponse response) {
		List<BaseEntity> modelList = modelBiz.queryAll();
		this.outJson(response, JSONObject.toJSONString(modelList));
	}

	/**
	 * 编辑模块
	 * 
	 * @return
	 * @return 返回页面
	 */
	@RequestMapping("/edit/{modelId}")
	@ResponseBody
	public ModelMap edit(ModelMap mode, @PathVariable int modelId) {
		List<BaseEntity> allModel = modelBiz.queryAll();
		mode.clear();
		//根据id获取模块
		ModelEntity model = (ModelEntity) modelBiz.getEntity(modelId);
		//根据父模块id查寻模块
		ModelEntity parentModel = (ModelEntity) modelBiz.getEntity(model.getModelModelId());
		mode.addAttribute("allModel", allModel);
		mode.addAttribute("parentModel", parentModel);
		mode.addAttribute("model", model);
		return mode;
	}

	/**
	 * 修改模块
	 * 
	 * @return 返回页面
	 */
	@RequestMapping("/update")
	@ResponseBody
	public void update(@ModelAttribute ModelEntity model,
			HttpServletRequest request, HttpServletResponse response) {
		if (!StringUtil.checkLength(model.getModelTitle(), 2, 20)
				|| !StringUtil.checkLength(model.getModelCode(), 2, 20)) {
			return;
		}
		//判断图标是否为空，不为空去掉,图标地址中含有的“|”
		//空值判断
		if(!StringUtil.isBlank(model.getModelIcon())) {
			model.setModelIcon( model.getModelIcon().replace("|", ""));
		}
		modelBiz.updateEntity(model);		
		this.outJson(response, ModelCode.ROLE, true,String.valueOf(model.getModelId()));
	}

	/**
	 * 模块列表
	 * 
	 * @param request
	 * @param request
	 *            请求
	 * @return 返回页面
	 */
	@RequestMapping("/list")
	public String list(ModelMap mode, HttpServletRequest request) {
		List<BaseEntity> parentModelList = modelBiz.queryChildList(0);
		List<BaseEntity> modelList = new ArrayList<BaseEntity>();
		
		int modelId = 0;
		if (!StringUtil.isBlank(request.getParameter("modelId"))) {
			modelId = Integer.valueOf(request.getParameter("modelId"));
		}
		List<BaseEntity> childModelList = null;
		//根据模块id循环遍历出其所有的上层模块
		while (modelId != 0) {
			ModelEntity model = (ModelEntity) modelBiz.getEntity(modelId);
			//查找子模块id
			childModelList = modelBiz.queryChildList(model.getModelModelId());
			if (childModelList.size() > 1) {
				for (BaseEntity i : childModelList) {
					modelList.add(i);
				}
			} else {
				modelList.add(model);
			}
			modelId = model.getModelModelId();
		}
		if (modelList != null && modelList.size() != 0) {
			Collections.reverse(modelList);
			mode.addAttribute("modelList", modelList);
		}
		mode.addAttribute("parentModelList", parentModelList);
		return "/manager/model/model_list";
	}

	/**
	 * 查询子模块
	 * 
	 * @param modelId
	 *            :模块id
	 * @return
	 */
	@RequestMapping("/{modelId}/childlist")
	@ResponseBody
	public ModelMap childList(@PathVariable int modelId,
			HttpServletResponse response, ModelMap model) {
		List<BaseEntity> childModelList = modelBiz.queryChildList(modelId);
		model.addAttribute("chileModelList", childModelList);
		return model;
	}

	/**
	 * 根据id查询是否有子模块
	 * 
	 * @param modelId
	 *            模块Id
	 * @param response 
	 * @return false:无子模块,true:有子模块
	 */
	@RequestMapping("/{modelId}/isChildModel")
	@ResponseBody
	public void isChildModel(@PathVariable int modelId, HttpServletResponse response) {
		int childCount = modelBiz.queryChildList(modelId).size();
		
		if (childCount > 0) {
			this.outJson(response,true);
		}else{
			this.outJson(response,modelId);
		}
		
	}

	/**
	 * 根据Id删除模块
	 * 
	 * @param modelId
	 *            模块Id
	 * @return
	 */
	@RequestMapping("/{modelId}/delete")
	@ResponseBody
	public void delete(@PathVariable int modelId, HttpServletResponse response) {
		ModelEntity model = (ModelEntity) modelBiz.getEntity(modelId);
		modelBiz.deleteEntity(modelId);
		this.outJson(response, ModelCode.ROLE, true,
				String.valueOf(model.getModelModelId()));
	}
	
	/**
	 * 主页
	 * 
	 * @return 主页页面
	 */
	@RequestMapping("/{managerId}/queryModelByRoleId")
	public void queryModelByRoleId(@PathVariable int managerId,HttpServletRequest request, HttpServletResponse response) {
		ManagerEntity manager =(ManagerEntity) managerBiz.getEntity(managerId);
		if(manager==null){
			return;
		}
		List<BaseEntity> modelList = new ArrayList<BaseEntity>();
		modelList = modelBiz.queryModelByRoleId(manager.getManagerRoleID());
		this.outJson(response, null,true, JSONObject.toJSONString(modelList));

	}
}
