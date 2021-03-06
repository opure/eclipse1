package com.mingsoft.basic.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.IDiyFormBiz;
import com.mingsoft.basic.constant.e.DiyFormFieldEnum;
import com.mingsoft.basic.dao.IDiyFormDao;
import com.mingsoft.basic.dao.IDiyFormFieldDao;
import com.mingsoft.basic.entity.DiyFormEntity;
import com.mingsoft.basic.entity.DiyFormFieldEntity;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

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
 * @author 王天培 QQ:78750478
 * 
 *         <p>
 *         Comments:自定义表单
 *         </p>
 * 
 *         <p>
 *         Create Date:2015-1-1
 *         </p>
 * 
 *         <p>
 *         Modification history:
 *         </p>
 */
@Service
public class DiyFormBizImpl extends BaseBizImpl implements IDiyFormBiz {

	/**
	 * 自定义表单的默认字段
	 */
	private static final String FORM_ID = "fromID";
	
	private static final String DATE = "date";
	
	/**
	 * 自定义表单生成的表自增长编号
	 */
	private static final String  ID = "id";
	@Autowired
	private IDiyFormDao diyFormDao;
	@Autowired
	private IDiyFormFieldDao diyFormFieldDao;

	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return diyFormDao;
	}

	@Override
	public void saveDiyFormData(int formId, Map params) {
		DiyFormEntity dfe = (DiyFormEntity) diyFormDao.getEntity(formId);
		if (dfe == null) {
			return;
		}
		String tableName = dfe.getDiyFormTableName();
		List<DiyFormFieldEntity> filedList = diyFormFieldDao.queryByDiyFormId(formId);
		if (filedList == null) {
			return;
		}
		Map values = builderSqlMap(filedList,params);
		values.put(FORM_ID, formId);
		values.put(DATE, new Date());
		diyFormFieldDao.insertBySQL(tableName,values);
	}

	@Override
	public Map queryDiyFormData(int diyFormId,int appId,PageUtil page) {
		DiyFormEntity dfe = (DiyFormEntity) diyFormDao.getEntity(diyFormId);
		if (dfe!=null) {
			List<DiyFormFieldEntity> fieldList = diyFormFieldDao.queryByDiyFormId(diyFormId);
			List<Map<String,String>> fields = new ArrayList<Map<String,String>>();
			for (int i=0;i<fieldList.size();i++) {
				Map<String,String> field = new HashMap<String,String>();
				field.put(fieldList.get(i).getDiyFormFieldFieldName(), fieldList.get(i).getDiyFormFieldTipsName());
				fields.add(field);
			}
			Map wheres = new HashMap();
			wheres.put(FORM_ID, diyFormId);
			List list = diyFormDao.queryBySQL(dfe.getDiyFormTableName(), null, wheres, page.getPageSize()*page.getPageNo(), page.getPageSize(),ID);
			Map r = new HashMap();
			r.put("fields", fieldList);
			r.put("list", list);
			return r;
		}
		
		return null;
	}


	@Override
	public void deleteQueryDiyFormData(int id,int diyFormId) {
		DiyFormEntity dfe = (DiyFormEntity) diyFormDao.getEntity(diyFormId);
		Map wheres = new HashMap();
		wheres.put(FORM_ID, diyFormId);
		wheres.put(ID, id);
		diyFormDao.deleteBySQL(dfe.getDiyFormTableName(), wheres);
	}

	@Override
	public int countDiyFormData(int diyFormId, int appId) {
		DiyFormEntity dfe = (DiyFormEntity) diyFormDao.getEntity(diyFormId);
		Map wheres = new HashMap();
		wheres.put(FORM_ID, diyFormId);
		return diyFormDao.countBySQL(dfe.getDiyFormTableName(),wheres );
	}

	@Override
	public List query(int appId) {
		// TODO Auto-generated method stub
		return diyFormDao.query(appId);
	}
	

	/**
	 * 遍历出所有字段的信息
	 * 
	 * @param listField
	 *            字段列表
	 * @param params 请求参数
	 * @return
	 */
	private Map builderSqlMap(List listField, Map params) {
		Map mapParams = new HashMap();
		// 遍历字段名
		for (int i = 0; i < listField.size(); i++) {
			DiyFormFieldEntity field = (DiyFormFieldEntity) listField.get(i);
			String fieldName = field.getDiyFormFieldFieldName();
			int fieldType = field.getDiyFormFieldType();
			if (fieldType == DiyFormFieldEnum.CHECKBOX.toInt() ) {
				String langtyp[] = (String[])params.get(fieldName);
				if (langtyp != null) {
					StringBuffer sb = new StringBuffer();
					for (int j = 0; j < langtyp.length; j++) {
						sb.append(langtyp[j] + ",");
					}
					mapParams.put(fieldName, sb.toString());
				} else {
					mapParams.put(fieldName, langtyp);
				}
			} else {
				if (StringUtil.isBlank(params.get(fieldName))) {
					mapParams.put(fieldName, null);
				} else {
					mapParams.put(fieldName, params.get(fieldName));
				}
			}
		}
		return mapParams;
	}

	@Override
	public DiyFormEntity getByTableName(String diyFormTableName) {
		// TODO Auto-generated method stub
		return diyFormDao.getByTableName(diyFormTableName);
	}
	
	@Override
	public void createDiyFormTable(String table, Map<Object, List> fileds) {
		// TODO Auto-generated method stub
		diyFormDao.createDiyFormTable(table, fileds);
	}
	
}
