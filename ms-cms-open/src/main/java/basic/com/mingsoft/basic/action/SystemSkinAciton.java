package com.mingsoft.basic.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.ISystemSkinBiz;
import com.mingsoft.basic.entity.ManagerSessionEntity;
import com.mingsoft.basic.entity.SystemSkinEntity;
import com.mingsoft.constant.SessionConst;


/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 *
 * @author 王天培
 *                QQ:78750478
 *
 * <p>
 * Comments:后台主题
 * </p>
 *
 * <p>
 * Create Date:2015-1-10
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller
@RequestMapping("/manager/systemSkin/")
public class SystemSkinAciton extends BaseAction{

	/**
	 * 主题业务
	 */
	@Autowired
	private ISystemSkinBiz systemSkinBiz;
	
	@RequestMapping("/{systemSkinId}/updateForManager")
	@ResponseBody
	public void updateForManager(HttpServletRequest request,HttpServletResponse response,@PathVariable int systemSkinId){
		ManagerSessionEntity mse = this.getManagerBySession(request);
		SystemSkinEntity sse = systemSkinBiz.updateManagerSystemSkin(mse.getManagerId(), systemSkinId);
		if (sse!=null) {
			mse.setSystemSkin(sse);
			this.setSession(request, SessionConst.MANAGER_ESSION, mse);
		}
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public void query(HttpServletRequest request,HttpServletResponse response){
		List list = systemSkinBiz.queryAll();
		this.outJson(response, JSONArray.toJSONString(list));
	}
}
