package com.mingsoft.basic.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.ICategoryBiz;
import com.mingsoft.basic.dao.ICategoryDao;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.util.PageUtil;
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
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments: 类别业务层实现类，继承IBaseBiz，实现ICategoryBiz接口
 * </p>
 *  
 * <p>
 * Create Date:2014-7-14
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Service("categoryBiz")
public class CategoryBizImpl extends BaseBizImpl implements ICategoryBiz {

	/**
	 * 类别持久化层的注入
	 */
	private ICategoryDao categoryDao;
	
	/**
	 * 获取类别持久化层
	 * @return categoryDao
	 */
	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	/**
	 * 设置类别持久化层
	 * @param categoryDao
	 */
	@Autowired
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return categoryDao;
	}

	/**
	 * 类别添加
	 */
	@Override
	public int saveCategory(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		 categoryDao.saveEntity(categoryEntity);
		return saveEntity(categoryEntity);
	}

	@Override
	public int saveCategoryEntity(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		return categoryDao.saveEntity(categoryEntity);
	}	
	
	/**
	 * 类别删除
	 */
	@Override
	public void deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		categoryDao.deleteEntity(categoryId);
		deleteEntity(categoryId);
	}

	@Override
	public void deleteCategoryEntity(int categoryId) {
		// TODO Auto-generated method stub
		categoryDao.deleteEntity(categoryId);
	}	
	
	/**
	 * 类别更新
	 */
	@Override
	public void updateCategory(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		categoryDao.updateEntity(categoryEntity);
		updateEntity(categoryEntity);
	}

	@Override
	public void updateCategoryEntity(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		categoryDao.updateEntity(categoryEntity);
	}	
	
	/**
	 * 获取类别
	 */
	@Override
	public CategoryEntity getCategory(int categoryId) {
		// TODO Auto-generated method stub
		return (CategoryEntity)categoryDao.getEntity(categoryId);
	}

	@Override
	public List queryByPageList(CategoryEntity category, PageUtil page,String orderBy, boolean order) {
		// TODO Auto-generated method stub
		return categoryDao.queryByPageList(category,page, orderBy, order);
	}

	@Override
	public List<CategoryEntity> queryChilds(CategoryEntity category) {
		return categoryDao.queryChilds(category);
	}
	
	@Override
    public int count(CategoryEntity category) {
    	return categoryDao.count(category);
    }
	
    /**
     * 根据modelId查询分类
     * @param category 查询条件
     * @return 分类集合
     */
    public List<CategoryEntity> queryByModelId(CategoryEntity category){
    	return categoryDao.queryByModelId(category);
    }   

    /**
     * 根据分类名称查询分类ID集合(数据采集使用)
     * @param categoryTitle 分类名称
     * @param int categoryModelId 模块ID
     * @return 查询到的分类集合
     */
    public List<Integer> queryCategoryIdByTitle(String categoryTitle,int categoryModelId){
    	return categoryDao.queryCategoryIdByTitle(categoryTitle,categoryModelId);
    }
 
    /**
     * 根据学校名称查询该学校所有专业ID
     * @param categorySchoolName 学校名称
     * @param schoolModelId 学校所属模块ID
     * @param facultyModelId 系所属模块ID
     * @return 该学校下专业ID的集合
     */
    public List<Integer> queryCategoryIdByCategoryTitle(String categorySchoolName,int schoolModelId,int facultyModelId){
    	return categoryDao.queryCategoryIdByCategoryTitle(categorySchoolName, schoolModelId, facultyModelId);
    }    
    
    /**
     * 根据分类标题和分类的模块ID查询该分类是否存在</br>
     * 若存在则不持久化直接返回数据库中原来的数据</br>
     * 若不存在则持久化并返回实体信息
     * @param categoryTitle 分类标题
     * @param categoryCategoryId 父ID
     * @param categoryModelId 模块ID
     * @return 分类实体
     */
	public CategoryEntity saveByCategoryTitle(String categoryTitle,int categoryCategoryId,int categoryModelId){
		CategoryEntity category = new  CategoryEntity();
		int categoryId = 0;
		if(StringUtil.isBlank(categoryTitle)){
			category.setCategoryId(categoryId);
			return category;
		}else{
			//查询数据库中属否存在该分类数据
			List <Integer> list = queryCategoryIdByTitle(categoryTitle,categoryModelId);
			if(list != null && list.size()>0){
				categoryId = list.get(list.size()-1);
			}			
		}
		
		//当数据库中不存在该分类数据时则持久化
		if( categoryId == 0){
			category.setCategoryTitle(categoryTitle);
			category.setCategoryCategoryId(categoryCategoryId);
			category.setCategoryModelId(categoryModelId);
			saveCategoryEntity(category);
		}else{
			category.setCategoryId(categoryId);
		}
		return category;
	}

	
    /**
     * 根据ID批量查询分类实体
     * @param listId ID集合
     * @return 分类实体
     */
    public List<CategoryEntity> queryBatchCategoryById(List<Integer> listId){
    	return categoryDao.queryBatchCategoryById(listId);
    }

	@Override
	public List<CategoryEntity> queryChildrenCategory(int categoryId) {
		// TODO Auto-generated method stub
		return categoryDao.queryChildrenCategoryId(categoryId);
	}
	

	@Override
	public List<Integer> queryChildrenCategoryIds(int categoryId) {
		// TODO Auto-generated method stub
		 List<CategoryEntity> list = categoryDao.queryChildrenCategoryId(categoryId);
		 List ids = null;
		 for (int i=0;i<list.size();i++) {
			 if (ids==null) {
				 ids = new ArrayList();
			 }
			 CategoryEntity category = list.get(i);
			 ids.add(Integer.valueOf(category.getCategoryId()));
		 }
		return ids;
	}

	@Override
	public List<CategoryEntity> queryByAppIdOrModelId(Integer appId, Integer modelId) {
		return categoryDao.queryByAppIdOrModelId(appId,modelId);
	}
    
    
}
