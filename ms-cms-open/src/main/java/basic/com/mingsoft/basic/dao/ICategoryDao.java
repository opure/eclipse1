package com.mingsoft.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.entity.CategoryEntity;
import com.mingsoft.util.PageUtil;

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
 * Comments: 类别数据持久层，继承IBaseDao
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
public interface ICategoryDao extends IBaseDao {

    /**
     * 分页查询</br>
     * 查询分类集合</br>
     * @param category 模块
     * @param pageNo 当前页面
     * @param pageSize 一页显示的数量
     * @param orderBy 排序字段
     * @param order 排序方式true:asc false:desc
     * @return 分类集合
     */	
	public List<CategoryEntity> queryByPageList(@Param("category")CategoryEntity category,@Param("page")PageUtil page,@Param("orderBy")String orderBy,@Param("order") boolean order);
	
	
    /**
     * 根据分类ID查询子分类</br>
     * @param category 查询条件
     */
    public List<CategoryEntity> queryChilds(@Param("category")CategoryEntity category);
    
    
    /**
     * 根据分类ID查询子分类总数</br>
     * @param categoryid 查询条件
     */
    public int count(@Param("category")CategoryEntity category);
    
    /**
     * 根据modelId查询分类
     * @param category 查询条件
     * @return 分类集合
     */
    public List<CategoryEntity> queryByModelId(@Param("category")CategoryEntity category);
    
    /**
     * 根据分类名称查询分类Id集合(课表数据采集使用)
     * @param categoryTitle 分类名称
     * @param int categoryModelId 模块ID
     * @return 查询到的分类集合
     */
    public List <Integer> queryCategoryIdByTitle(@Param("categoryTitle")String categoryTitle,@Param("categoryModelId")int categoryModelId);
    
    /**
     * 根据学校名称查询该学校所有专业ID
     * @param categorySchoolName 学校名称
     * @param schoolModelId 学校所属模块ID
     * @param facultyModelId 系所属模块ID
     * @return 该学校下专业ID的集合
     * @deprecated 因为categoryTitle可能会出现重复
     */
    public List<Integer> queryCategoryIdByCategoryTitle(@Param("categoryTitle")String categoryTitle,@Param("fatherModelId")int fatherModelId,@Param("sonModelId")int sonModelId);
    
    /**
     * 查询categoryId分类下的子分类数据
     * @param categoryId 父分类编号
     * @param fatherModelId 父分类所属模块ID
     * @param sonModelId 子分类模块ID
     * @return 返回子分类集合
     */
    public List<Integer> queryCategoryIdByCategoryId(@Param("categoryId")int categoryId,@Param("fatherModelId")int fatherModelId,@Param("sonModelId")int sonModelId);
    
    /**
     * 根据ID批量查询分类实体
     * @param listId ID集合
     * @return 分类实体
     */
    public List<CategoryEntity> queryBatchCategoryById(@Param("listId")List<Integer> listId);
    
    /**
     * 查询当前分类下面的子分类
     * @param categoryId　当前分类编号
     * @return　子分类列表
     */
    public List<CategoryEntity> queryChildrenCategoryId(@Param("categoryId")int categoryId);


	/**
	 * 根据应用编号与模块编号查询分类
	 * 
	 * @param appId 应用编号
	 * @param modelId 模块编号
	 * @return 分类集合
	 */
	public List<CategoryEntity> queryByAppIdOrModelId(@Param("appId")Integer appId,@Param("modelId") Integer modelId);
}
