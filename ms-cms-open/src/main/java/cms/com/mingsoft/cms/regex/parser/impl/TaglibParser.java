package com.mingsoft.cms.regex.parser.impl;


import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mingsoft.cms.biz.IFieldBiz;
import com.mingsoft.cms.entity.FieldEntity;
import com.mingsoft.cms.regex.parser.IParser;
import com.mingsoft.util.StringUtil;


/**
 * 文章自定义标签（新增字段）
 * 文章内容标签
 * {ms:cfield./}
 * @author 史爱华
 * 技术支持：景德镇铭飞科技
 * 官网：www.ming-soft.com
 */
public class TaglibParser extends IParser{
	/**
	 * 各新增字段的值
	 */
	private Map fields;
	
	/**
	 * 新增表单的id
	 */
	private int contentModelId;
	
	/**
	 * 字段业务层
	 */
	private IFieldBiz fieldBiz;
	
	/**
	 * option 选择框
	 */
	private final static int OPTION=9;
	
	/**
	 * radio 选择按钮
	 */
	private final static int RADIO=10;
	
	/**
	 * checkbox 多选框
	 */
	private final static int CHECKBOX = 11;
	
	/**
	 *字定义标签{ms.cfield./}
	 */
	private final static String ARTICLE_TAGLIB_FIELD="\\{ms:cfield.(.*)?\\s*/}";
	
	/**
	 * 
	 * @param htmlContent
	 * @param newContent
	 * 
	 * @param fields
	 * 新增字段的信息
	 */
	public TaglibParser(String htmlContent,Map fields,int contentModelId,IFieldBiz fieldBiz){
		super.htmlCotent = htmlContent;
		this.fields = fields;
		this.contentModelId = contentModelId;
		this.fieldBiz = fieldBiz;
	}
	
	/**
	 * 检查自定义标签的个数
	 */
	public int taglibNum(String html){
		int taglibNum = count(html,ARTICLE_TAGLIB_FIELD);
		return taglibNum;
	}
	
	/**
	 * 获取字段名并获取相应的值
	 */
	private String taglibContentParser(String html,Map field){
		String taglibContent = "";
		Pattern patternL = Pattern.compile(ARTICLE_TAGLIB_FIELD);
		Matcher matcherL = patternL.matcher(html);
		if (matcherL.find()) {
			//查找出用户填写的自定义标签字段名
			String fieldName = matcherL.group(1);
			Iterator<String> iter = field.keySet().iterator();
			//判断该字段是否存在
			while (iter.hasNext()) {
			    String key = iter.next();
			    if(fieldName.equals(key)){
			    	// 查找字段实体
			    	FieldEntity fieldEntity = fieldBiz.getEntityByCmId(contentModelId, fieldName);
			    	// 字段的实际值
	    			String fieldValue=String.valueOf(field.get(key));
			    	//判断是否为checkBox,或option类型
			    	if(fieldEntity.getFieldType()==OPTION || fieldEntity.getFieldType()==RADIO || fieldEntity.getFieldType()==CHECKBOX){
			    		String fieldDefault = fieldEntity.getFieldDefault();
			    		if(!StringUtil.isBlank(fieldDefault)){
			    			String[] filedNew = fieldDefault.split(",");
				    		//判断是否为checkBox
				    		if(fieldEntity.getFieldType()==CHECKBOX){
				    			String[] checkBox = field.get(key).toString().split(",");
				    			fieldValue = "";
				    			for(int i= 0;i<checkBox.length;i++){
				    				fieldValue+=filedNew[Integer.valueOf(checkBox[i].toString())-1];
				    			}
				    		}else{
				    			fieldValue = filedNew[Integer.valueOf(field.get(key).toString())-1];
				    		}
			    		}
			    	}
			    	taglibContent=fieldValue;
			    	break;
			    }
			}
		}
		return taglibContent;
	}
	@Override
	public String parse() {
		//原始内容
		String html = super.htmlCotent; 
		//检查自定义标签{ms:cfield.*/}的个数
		int taglibNum = taglibNum(super.htmlCotent);
		while(taglibNum!=0){
			super.newCotent=taglibContentParser(super.htmlCotent,fields);
			// 将取出的内容替换标签
			super.htmlCotent = super.replaceFirst(ARTICLE_TAGLIB_FIELD);
			html = super.htmlCotent;
			taglibNum = taglibNum(super.htmlCotent);
		}
		// TODO Auto-generated method stub
		return html;
	}
}
