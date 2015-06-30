package com.mingsoft.basic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.entity.BasicEntity;
import com.mingsoft.util.PageUtil;

/**
 * 
 * <p>
 * <b>铭飞基础框架</b>
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
 * @author 荣繁奎
 * 
 * @version 100-000-000
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:基本信息的数据层(接口)
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-5-10
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
public interface IBasicDao extends IBaseDao {

	/**
	 * 更新点击次数
	 * 
	 * @param basicId
	 *            　文章编号
	 * @param num
	 *            null:为递增
	 */
	void updateHit(@Param("basicId") int basicId, @Param("num") Integer num);

	/**
	 * 根据分类与关键子统计总数
	 * 
	 * @param categoryId
	 *            　分类编号
	 * @param keyWord
	 *            关键字
	 * @return　总数
	 */
	int count(@Param("categoryId") int categoryId, @Param("keyWord") String keyWord);

	/**
	 * 根据分类与关键子统计总数
	 * 
	 * @param categoryId
	 *            　分类编号
	 * @param keyWord
	 *            　关键字
	 * @param begin 开始
	 * @param end　结束
	 * @param orderField　排序字段
	 * @param ad　排序方式true:升序 false:倒序
	 * @return　列表
	 */
	List<BasicEntity> query(@Param("appId") Integer appId,@Param("categoryId") Integer categoryId, @Param("keyWord") String keyWord, @Param("begin") Integer begin, @Param("end") Integer end, @Param("orderField")String orderField, @Param("ad")Boolean ad, @Param("modelId")Integer modelId,@Param("where")Map where);

}
