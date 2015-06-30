package com.mingsoft.basic.action;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.biz.IManagerBiz;
import com.mingsoft.basic.biz.IManagerModelPageBiz;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.entity.ManagerEntity;
import com.mingsoft.basic.entity.ManagerModelPageEntity;
import com.mingsoft.basic.entity.ManagerSessionEntity;
import com.mingsoft.basic.entity.ModelEntity;
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
 * @author 姓名：张敏
 * 
 * @version 300-001-001
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:主界面控制层，继承BasicAction
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-7-14
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
@Controller
@RequestMapping("/manager")
public class MainAction extends BaseAction {

	/**
	 * 模块业务层
	 */
	@Autowired
	private IModelBiz modelBiz;

	/**
	 * 管理员业务层
	 */
	@Autowired
	private IManagerBiz managerBiz;
	
	
	@Autowired
	private IManagerModelPageBiz managerModelPageBiz;

	/**
	 * 主页
	 * 
	 * @return 主页页面
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		ManagerSessionEntity managerSession = getManagerBySession(request);
		List<BaseEntity> modelList = new ArrayList<BaseEntity>();
		modelList = modelBiz.queryModelByRoleId(managerSession.getManagerRoleID());
		request.setAttribute("managerSession", managerSession);
		request.setAttribute("modelList", JSONObject.toJSONString(modelList));
		int managerId = managerSession.getManagerId();
		//根据管理员id查找管理员模块页面实体对象
		ManagerModelPageEntity managerModelPage =(ManagerModelPageEntity) managerModelPageBiz.getByManagerIdAndModelId(managerId,0);
		//如果存在管理员模块页面实体对象，则返回到页面
		if(managerModelPage!=null){
			request.setAttribute("managerModelPage", managerModelPage);
		}
		return "/manager/index";
	}

	/**
	 * 查询该父模块下的子模块
	 * 
	 * @param modelId
	 *            模块ID
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{modelId}/queryListByModelId", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap queryListByModelId(@PathVariable int modelId, HttpServletRequest request, ModelMap modelMap) {
		List<BaseEntity> modelList = null;
		ManagerSessionEntity managerSession = getManagerBySession(request);
		if (isSystemManager(request) && modelId == Const.DEFAULT_CMS_MODEL_ID) { // 若为系统管理员且操作CMS模块
			modelList = modelBiz.queryModelByManagerId(Const.DEFAULT_SYSTEM_MANGER_ROLE_ID, modelId);
		} else if (isSystemManager(request)) { // 若为系统管理员且非操作CMS模块
			modelList = modelBiz.queryChildList(modelId);
		} else { // 其他管理员
			modelList = modelBiz.queryModelByRoleId(managerSession.getManagerRoleID());
			for (int i = 0; i < modelList.size(); i++) {
				ModelEntity model = (ModelEntity) modelList.get(i);
				if (model.getModelModelId() != modelId) {
					modelList.remove(i);
					i--;
				}
			}
		}
		modelMap.addAttribute("modelList", modelList);
		return modelMap;
	}

	/**
	 * 修改登录密码 返回当前用户的帐号密码至修改页面
	 * 
	 * @param request
	 *            请求
	 * @return 修改登录密码页面
	 */
	@RequestMapping("/editPassword")
	@ResponseBody
	public ModelMap editPassword(ModelMap model, HttpServletRequest request) {
		ManagerSessionEntity managerSession = getManagerBySession(request);
		ManagerEntity manager = managerBiz.queryManagerByManagerName(managerSession.getManagerName());
		if (!StringUtil.isBlank(manager)) {
			manager.setManagerPassword(StringUtil.Md5(manager.getManagerPassword()));
			model.addAttribute("manager", manager);
		}
		return model;
	}

	/**
	 * 修改登录密码，若不填写密码则表示不修改
	 * 
	 * @param manager
	 *            管理员实体
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public void updatePassword(@ModelAttribute ManagerEntity manager, HttpServletResponse response) {
		// 判断密码是否为空
		if (StringUtil.isBlank(manager.getManagerPassword())) {
			this.outJson(response, ModelCode.ROLE, false, null);
		}
		// 判断密码长度
		if (!StringUtil.checkLength(manager.getManagerPassword(), 1, 16)) {
			this.outJson(response, ModelCode.ROLE, false, getResString("err.length", this.getResString("managerPassword"), "1", "16"));
		}
		manager.setManagerPassword(StringUtil.Md5(manager.getManagerPassword()));
		managerBiz.updateUserPasswordByUserName(manager);
		this.outJson(response, ModelCode.ROLE, true, null);
	}

	/**
	 * 退出系统
	 * 
	 * @param manager
	 *            管理员实体
	 * @param request
	 *            请求
	 */
	@RequestMapping("/loginOut")
	@ResponseBody
	public boolean loginOut(HttpServletRequest request) {
		removeSession(request, SessionConst.MANAGER_ESSION);
		return true;
	}

}
