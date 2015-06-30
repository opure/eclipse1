/**
 * 
 */
package com.mingsoft.action.people;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.action.BaseAction;
import com.mingsoft.base.entity.ListJson;
import com.mingsoft.basic.biz.IBasicAttentionBiz;
import com.mingsoft.basic.entity.BasicAttentionEntity;
import com.mingsoft.basic.entity.PeopleEntity;
import com.mingsoft.constant.ModelCode;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

/**
 * 
 * <p>
 * <b>铭飞科技-关注</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 *
 * @author 成卫雄
 *                QQ:330216230
 *
 * <p>
 * Comments:关注控制层,用于外部请求
 * </p>
 *
 * <p>
 * Create Date:2014-11-12
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller("webAttention")
@RequestMapping("/people/attention")
public class AttentionAction extends BaseAction{

	/**
	 * 注入关注业务层
	 */
	@Autowired
	private IBasicAttentionBiz basicAttentionBiz;
	
	/**
	 * 新增关注
	 * @param basicAttention 关注实体
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	@ResponseBody
	public void saveByAjax(@ModelAttribute BasicAttentionEntity basicAttention,HttpServletRequest request,HttpServletResponse response){
		if(basicAttention == null || basicAttention.getBasicAttentionBasicId() == 0 || basicAttention.getBasicAttentionType() == 0){
			this.outJson(response, this.getResString("err"));
			return ;
		}
		//获取用户session
		PeopleEntity people = this.getPeopleBySession(request);
		if(people == null){
			this.outJson(response, this.getResString("err"));
			return ;
		}		
		basicAttention.setBasicAttentionPeopleId(people.getPeopleId());
		//获取APPID
		int appId = this.getAppId(request);
		basicAttention.setBasicAttentionAppId(appId);
		
		//比对用户是否收藏过该商品
		BasicAttentionEntity basicAttentionEntity = this.basicAttentionBiz.getEntityByPeopleAttention(basicAttention);
		if(basicAttentionEntity != null) {
			this.outJson(response, ModelCode.ATTENTION,false);
			return;
		}
		
		this.basicAttentionBiz.saveEntity(basicAttention);
		if(basicAttention.getBasicAttentionId() == 0){
			this.outJson(response, this.getResString("err"));
		}else{
			this.outJson(response, ModelCode.ATTENTION,true);
		}
	};
	
	/**
	 * 判断用户是否关注过该商品
	 * @param basicAttention
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getEntityByPeopleAttention")
	@ResponseBody
	public void getEntityByPeopleAttention(@ModelAttribute BasicAttentionEntity basicAttention,HttpServletRequest request,HttpServletResponse response){
		if(basicAttention == null || basicAttention.getBasicAttentionBasicId() == 0 || basicAttention.getBasicAttentionType() == 0){
			this.outJson(response, this.getResString("err"));
			return ;
		}
		//获取用户ID
		PeopleEntity people = this.getPeopleBySession(request);
		if(people == null){
			this.outJson(response, this.getResString("err"));
			return ;
		}	
		//获取APPid
		int appId = this.getAppId(request);
		//构建查询条件
		basicAttention.setBasicAttentionPeopleId(people.getPeopleId());
		basicAttention.setBasicAttentionAppId(appId);
		BasicAttentionEntity basicAttentionEntity = this.basicAttentionBiz.getEntityByPeopleAttention(basicAttention);
		if(basicAttentionEntity == null || basicAttentionEntity.getBasicAttentionId() == 0){
			this.outJson(response, ModelCode.ATTENTION,false);
		}else{
			this.outJson(response, ModelCode.ATTENTION,true);
		}
	}
	
	/**
	 * 查询用户关注列表</br>
	 * 接收参数:</br>
	 * 		pageNo:分页,页码</br>
	 * 		pageSize:一页显示的数量</br>
	 * 		type:积分类型</br>
	 * @param request
	 * @param response
	 */
	@RequestMapping("/queryPeopleAttention")
	@ResponseBody
	public void queryPeopleAttention(HttpServletRequest request,HttpServletResponse response){
		String pageNo = request.getParameter("pageNo"); //分页
		String pageSize  = request.getParameter("pageSize"); //分页数量
		String attentionType = request.getParameter("type");//类型
		Integer modelId = this.getInt(request, "modelId");//模块编号
		
		if(!StringUtil.isInteger(attentionType)) {
			this.outJson(response, ModelCode.ATTENTION, false);
			return ;
		}
		
		if(modelId==null){
			modelId = this.getModelCodeId(request, ModelCode.CMS_ARTICLE_BASIC);//默认文章模块
		}
		
		if(!StringUtil.isInteger(pageNo)){
			pageNo = "1";
		}
		
		if(!StringUtil.isInteger(pageSize)){
			pageSize = "10";
		}
		
		//获取用户ID
		PeopleEntity people = this.getPeopleBySession(request);
		if(people == null){
			this.outJson(response, this.getResString("err"));
			return ;
		}	
		int peopleId = people.getPeopleId();
		//获取APPid
		int appId = this.getAppId(request);
		int count = this.basicAttentionBiz.queryCountByPeopleIdAndAppId(peopleId,appId,Integer.parseInt(attentionType));
		PageUtil page = new PageUtil(Integer.parseInt(pageNo),Integer.parseInt(pageSize), count, getUrl(request)+"/basicAttentionAction/queryPeopleAttention.do");
		List<BasicAttentionEntity> listBasicAttention = this.basicAttentionBiz.queryPageByPeopleIdAndAppId(peopleId, appId,Integer.parseInt(attentionType),modelId, page);
		if(listBasicAttention == null){
			this.outJson(response,ModelCode.ATTENTION,false);
		}else{
			ListJson json = new ListJson(count,listBasicAttention);
			this.outJson(response,ModelCode.ATTENTION,true,JSONObject.toJSONString(json));
		}
	}
	
	@RequestMapping("{attentionId}/delete")
	@ResponseBody
	public void delete(@PathVariable Integer attentionId,HttpServletRequest request,HttpServletResponse response){
		if(attentionId == null ){
			this.outJson(response, ModelCode.ATTENTION, false);
			return ;
		}
		
		//执行删除
		this.basicAttentionBiz.deleteEntity(attentionId);
		this.outJson(response, ModelCode.ATTENTION, true);
	}
	
}
