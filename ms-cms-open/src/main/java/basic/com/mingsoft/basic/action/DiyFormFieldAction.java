package com.mingsoft.basic.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.base.action.BaseAction;
import com.mingsoft.basic.biz.IDiyFormBiz;
import com.mingsoft.basic.biz.IDiyFormFieldBiz;
import com.mingsoft.basic.entity.DiyFormEntity;
import com.mingsoft.basic.entity.DiyFormFieldEntity;
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
* @author 姓名：史爱华
* 
* @version 300-001-001
* 
* <p>
* 版权所有 铭飞科技
* </p>
*  
* <p>
* Comments:表单字段义字段管理，继承BaseAction
* </p>
*  
* <p>
* Create Date:2015-01-10
* </p>
*
* <p>
* Modification history:
* </p>
*/
@Controller("diyFormField")
@RequestMapping("/manager/diy/formField")
public class DiyFormFieldAction extends BaseAction{
	
	/**
	 * 默认的字段id
	 */
	private static final  String FIELD_ID="id";
	
	/**
	 * 默认的字段date
	 */
	private static final  String FIELD_DATE="date";
	
	/**
	 * 默认的字段formId
	 */
	private static final  String FIELD_FORMID="formId";
	/**
	 * 
	 */
	@Autowired
	private IDiyFormFieldBiz diyFormFieldBiz;
	
	
	/**
	 * 
	 */
	@Autowired
	private IDiyFormBiz diyFormBiz;
	
	/**
	 * 查询字段的列表信息
	 * @param cmId
	 * @param request
	 * @param model
	 * @param response
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ModelMap list(int diyFormId,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		
		//查询所有的字段信息
		List<DiyFormFieldEntity> fieldList = diyFormFieldBiz.queryByDiyFormId(diyFormId);
		model.addAttribute("fieldList", fieldList);
		//获取字段属性
				Map<Integer,String> fieldType = this.getfieldType(model);
				model.addAttribute("fieldType",fieldType);
				model.addAttribute("fieldNum",fieldType.size());
				return model;
	}
	
	/**
	 * 获取字段属性
	 * @return
	 */
	public Map<Integer,String> getfieldType( ModelMap model){
		Map<String,String> maps = new LinkedHashMap<String,String>(); 
		maps = getMapByProperties("com/mingsoft/basic/resources/field_type");
		Map<Integer,String> fieldType= new LinkedHashMap<Integer,String>();
		Map<Integer,String> map= new LinkedHashMap<Integer,String>();
		for(Entry<String, String> entry : maps.entrySet()){
			map.put(Integer.valueOf(entry.getKey()), entry.getValue());
		}
		for(int i=1; i<=maps.size(); i++){
			fieldType.put(i, map.get(i));
		}
        return fieldType;
	}
	
	/**
	 * 
	 * @param diyFormfield
	 * @param cmTableName
	 * @param response
	 */
	@RequestMapping("/{diyFormId}/save")
	@ResponseBody
	public void save(@ModelAttribute DiyFormFieldEntity  diyFormfield,@PathVariable int  diyFormId, HttpServletResponse response) {
		//获取自定义表单实体
		DiyFormEntity diyForm = (DiyFormEntity) diyFormBiz.getEntity(diyFormId);
		if(diyForm==null){
			this.outJson(response, null, false,this.getResString("err.not.exist",this.getResString("diy.form")));
			return;
		}
		// 更新前判断数据是否合法
		if(!StringUtil.checkLength(diyFormfield.getDiyFormFieldTipsName(), 1,20)){
			this.outJson(response, null, false,getResString("err.length",this.getResString("fieldTipsName"),"1","20"));
			return ;
		}
		if(!StringUtil.checkLength(diyFormfield.getDiyFormFieldFieldName(), 1,20)){
			this.outJson(response, null, false,getResString("err.length",this.getResString("fieldFieldName"),"1","20"));
			return ;
		}
		
		//读取属性配置文件
		Map<String,String> maps = new LinkedHashMap<String,String>(); 
		maps = getMapByProperties("com/mingsoft/basic/resources/field_data_type");
		//动态的修改表结构
		//获取字段信息
		Map fileds = new HashMap();
		//压入字段名
		fileds.put("fieldName", diyFormfield.getDiyFormFieldFieldName());
		//字段的数据类型
		fileds.put("fieldType",maps.get(String.valueOf(diyFormfield.getDiyFormFieldType())));
		fileds.put("default", diyFormfield.getDiyFormFieldDefault());
		//在表中创建字段
		diyFormFieldBiz.alterTable(diyForm.getDiyFormTableName(), fileds,"add");
		this.diyFormFieldBiz.saveEntity(diyFormfield);
		this.outJson(response, null, true, null);
	}
	
	/**
	 * 获取编辑的字段实体的信息
	 * @param mode 
	 * @param fieldId :要获取的字段实体的id
	 * @param request
	 * @return
	 */
	@RequestMapping("/{diyFormFieldId}/edit")
	@ResponseBody
	public ModelMap edit(ModelMap mode,@PathVariable int diyFormFieldId, HttpServletRequest request){
		DiyFormFieldEntity diyFormfield = (DiyFormFieldEntity) diyFormFieldBiz.getEntity(diyFormFieldId);
		mode.addAttribute("diyFormfield", diyFormfield);
		return mode;
	}
	
	/**
	 * 更新字段信息
	 * @param field 要更新的字段信息的id
	 * @param response 
	 */

	@RequestMapping("/update")
	@ResponseBody
	public void update(@ModelAttribute DiyFormFieldEntity  diyFormfield, HttpServletResponse response){
		// 更新前判断数据是否合法
		if(!StringUtil.checkLength(diyFormfield.getDiyFormFieldTipsName(), 1,20)){
			this.outJson(response, null, false,getResString("err.length",this.getResString("fieldTipsName"),"1","20"));
			return ;
		}
		if(!StringUtil.checkLength(diyFormfield.getDiyFormFieldFieldName(), 1,20)){
				this.outJson(response, null, false,getResString("err.length",this.getResString("fieldFieldName"),"1","20"));
				return ;
		}
		
		//获取自定义表单实体
		DiyFormEntity diyForm = (DiyFormEntity) diyFormBiz.getEntity(diyFormfield.getDiyFormFieldFormId());
		//读取属性配置文件
		Map<String,String> maps = new LinkedHashMap<String,String>(); 
		maps = getMapByProperties("com/mingsoft/basic/resources/field_data_type");
		// 获取更改前的字段实体
		DiyFormFieldEntity oldField =(DiyFormFieldEntity) diyFormFieldBiz.getEntity(diyFormfield.getDiyFormFieldId());
		Map fields = new HashMap();
		//更改前的字段名
		fields.put("fieldOldName",oldField.getDiyFormFieldFieldName());
		//新字段名
		fields.put("fieldName",diyFormfield.getDiyFormFieldFieldName());
		//字段的数据类型
		fields.put("fieldType", maps.get(String.valueOf(diyFormfield.getDiyFormFieldType())));
		if(diyForm==null){
			this.outJson(response, null, false,this.getResString("err.not.exist"));
			return;
		}
		// 更新表的字段名
		diyFormFieldBiz.alterTable(diyForm.getDiyFormTableName(), fields, "modify");
		diyFormFieldBiz.updateEntity(diyFormfield);
		this.outJson(response, null, true, null);
	}
	
	/**
	 * 判断字段名是否存在重复
	 * @param diyFormFieldFieldName :字段名
	 * @param request
	 * @return true:存在重复,false:不存在重复
	 */
	@RequestMapping("/{diyFormFieldFieldName}/checkFieldNameExist")
	@ResponseBody
	public boolean checkFieldNameExist(@PathVariable String diyFormFieldFieldName, HttpServletRequest request){
		int diyFormFieldFormId = 1;
		if(request.getParameter("diyFormFieldFormId")!=null){
			diyFormFieldFormId =Integer.parseInt(request.getParameter("diyFormFieldFormId"));
		}
		//判断同一表单中是否存在表字段重名
		if(diyFormFieldFieldName.equalsIgnoreCase(FIELD_ID)||diyFormFieldFieldName.equalsIgnoreCase(FIELD_DATE)|| diyFormFieldFieldName.equalsIgnoreCase(FIELD_FORMID)||diyFormFieldBiz.getByFieldName(diyFormFieldFormId, diyFormFieldFieldName)!=null){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 删除自定义字段
	 * @param cmId 表单ID
	 * @param request 请求
	 * @param response 响应
	 */
	@RequestMapping("/{fieldId}/delete")
	public void  delete(@PathVariable int fieldId, HttpServletRequest request, HttpServletResponse response) {
		//
		DiyFormFieldEntity diyFormField = (DiyFormFieldEntity) this.diyFormFieldBiz.getEntity(fieldId);
		if(diyFormField ==null){
			this.outJson(response, null, false,this.getResString("err.not.exist",this.getResString("diy.form.field")));
			return;
		}
		DiyFormEntity diyForm = (DiyFormEntity) this.diyFormBiz.getEntity(diyFormField.getDiyFormFieldFormId());
		if(diyForm==null){
			this.outJson(response, null, false,this.getResString("err.not.exist",this.getResString("diy.form")));
			return;
		}
		Map fields = new HashMap();
		// 要删除的字段名
		fields.put("fieldName",diyFormField.getDiyFormFieldFieldName());
		//删除列
		diyFormFieldBiz.alterTable(diyForm.getDiyFormTableName(),fields,"drop");
		diyFormFieldBiz.deleteEntity(diyFormField.getDiyFormFieldId());
		this.outJson(response, null,true);
	}
}
