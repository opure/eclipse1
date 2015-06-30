package com.mingsoft.base.biz;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.util.PageUtil;

/**
 * 
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
 * @author wangtp
 * 
 * @version 100-000-000
 * 
 *          <p>
 *          版权所有
 *          </p>
 * 
 *          <p>
 *          Comments:基础业务
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-6-27
 *          </p>
 * 
 *          <p>
 *          Modification history:暂无
 *          </p>
 */
public interface IBaseBiz {

	/**
	 * 保存
	 * 
	 * @param entity
	 *            实体
	 * @return 返回保存后的id
	 */
	int saveEntity(BaseEntity entity);

	/**
	 * 根据id删除实体
	 * 
	 * @param entity
	 *            实体类型
	 * @param id
	 *            要删除的主键id
	 */
	void deleteEntity(int id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void updateEntity(BaseEntity entity);

	/**
	 * 查询所有
	 * 
	 * @param entity
	 */
	List<BaseEntity> queryAll();

	
	/**
	 * 
	 * 分页查询
	 * 
	 * @param entity
	 *            实体
	 * @param page
	 *            PageUtil对象，主要封装分页的方法
	 * @param orderBy 排序字段
	 * @param order 排序方式true:asc false:desc
	 * @return
	 */
	List<BaseEntity> queryByPage(PageUtil page, String orderBy,boolean order);
	
	
	/**
	 * 查询数据表中记录集合总数</br>
	 * 可配合分页使用</br>
	 * @return
	 */
	int queryCount();
	
	/**
	 * 更具ID查询实体信息
	 * @param entity 实体
	 * @param id 实体ID
	 * @return
	 */
	BaseEntity getEntity(int id);	
	
	
/**
 * 动态sql查询
 * @param table 表名称
 * @param fields list集合
 * @param basicId 都是key-value对应
 * @param begin 开始
 * @param end 结束
 * @return
 */
	@SuppressWarnings("rawtypes")
	List queryBySQL(String table, List<String> fields, Map wheres,Integer begin,Integer end);
	/**
	 * 总数
	 * @param table　表名称
	 * @param wheres　条件　都是key-value对应
	 * @return　总数
	 */
	int countBySQL(String table, Map wheres);
	/**
	 * 动态sql查询
	 * @param table 表名称
	 * @param fields list集合
	 * @param basicId 都是key-value对应
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List queryBySQL(String table, List<String> fields, Map wheres);
	
	/**
	 *动态SQL更新
	 * @param table 表名称
 * @param fields list集合每个map都是key-value对应
 *   @param 条件 都是key-value对应
	 */
	@SuppressWarnings("rawtypes")
	void updateBySQL(String table,@Param("fields") Map fields,@Param("wheres") Map wheres);
	
	/**
	 * 动态SQL删除
	 * @param table 表名称
     * @param 條件 都是key-value对应
	 */
	@SuppressWarnings("rawtypes")
	void deleteBySQL(String table,@Param("wheres") Map wheres);
	
	/**
	 * 添加记录
	 * @param table 表名称
     * @param basicId 编号
	 */
	@SuppressWarnings("rawtypes")
	void insertBySQL(String table,Map fields);
	
	/**
	 * 创建表
	 * @param table 表名称
	 * @param fileds key:字段名称  list[0] 类型  list[1]长度 list[2]默认值 list[3]是否不填
	 */
	@SuppressWarnings("rawtypes")
	void createTable(String table,Map<Object,List> fileds);
	
	/**
	 * 修改表
	 * @param table 表名称
	 * @param fileds key:字段名称  list[0] 类型  list[1]长度 list[2]默认值 list[3]是否不填
	 */
	@SuppressWarnings("rawtypes")
	void alterTable(String table,Map fileds,String type);
	
	/**
	 * 删除表
	 * @param table 表名称
	 */
	void dropTable(String table);
	
	/**
	 * 导入执行数据
	 * @param sql　sql语句
	 */
	void excuteSql(@Param("sql")String sql);
	/**
	 * 批量新增
	 * @param list 新增数据
	 */
	void saveBatch(List list);
	
	
	
}
