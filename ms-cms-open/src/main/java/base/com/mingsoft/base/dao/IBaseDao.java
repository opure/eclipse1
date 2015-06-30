package com.mingsoft.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.basic.entity.PeopleEntity;

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
 * <p>
 * 版权所有
 * </p>
 *  
 * <p>
 * Comments:基础dao
 * </p>
 *  
 * <p>
 * Create Date:2014-6-27
 * </p>
 *
 * <p>
 * Modification history:暂无
 * </p>
 */
public interface IBaseDao {

	/**
	 * 保存
	 * @param entity 实体
	 * @return 返回保存后的id
	 */
	int saveEntity(BaseEntity entity);
	
	/**
	 * 根据id删除实体
	 * @param entity 实体类型
	 * @param id 要删除的主键id
	 */
	void deleteEntity(int id);
	
	/**
	 * 更新实体
	 * @param entity 实体
	 */
	void updateEntity(BaseEntity entity);
	
	/**
	 * 查询所有
	 * @param entity 实体
	 */
	List<BaseEntity> queryAll();
	
	/**
	 * 分页查询
	 * @param entity 实体
	 * @param page Map对象
	 * @param order 排序方式,true:asc;fales:desc
	 * @return
	 */
	List<BaseEntity> queryByPage(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("orderBy")String orderBy,@Param("order") boolean order);
	
	/**
	 * 查询数据表中记录集合总数
	 * @return
	 */
	int queryCount();
	
	/**
	 * 根据ID查询实体信息
	 * @param entity 实体
	 * @param id 实体ID
	 * @return
	 */
	BaseEntity getEntity(Integer id);
	
/**
 * 动态sql查询
 * @param table 表名称
 * @param fields list集合
 * @param basicId 都是key-value对应
 * @return
 */
	@SuppressWarnings("rawtypes")
	List queryBySQL(@Param("table")String table,@Param("fields") List<String> fields,@Param("wheres") Map wheres,@Param("begin") Integer begin,@Param("end") Integer end,@Param("order") String order);
	
	/**
	 * 总数
	 * @param table　表名称
	 * @param wheres　条件　都是key-value对应
	 * @return　总数
	 */
	int countBySQL(@Param("table")String table,@Param("wheres") Map wheres);
	
	/**
	 *动态SQL更新
	 * @param table 表名称
 * @param fields list集合每个map都是key-value对应
 *   @param 条件 都是key-value对应
	 */
	void updateBySQL(@Param("table")String table,@Param("fields") Map fields,@Param("wheres") Map wheres);
	
	/**
	 * 动态SQL删除
	 * @param table 表名称
     * @param 條件 都是key-value对应
	 */
	void deleteBySQL(@Param("table")String table,@Param("wheres") Map wheres);
	
	/**
	 * 添加记录
	 * @param table 表名称
     * @param basicId 编号
	 */
	void insertBySQL(@Param("table")String table,@Param("fields") Map fields);
	
	/**
	 * 创建表
	 * @param table 表名称
	 * @param fileds key:字段名称  list[0] 类型  list[1]长度 list[2]默认值 list[3]是否不填
	 */
	void createTable(@Param("table")String table,@Param("fileds")Map<Object,List> fileds);
	
	
	
	/**
	 * 修改表
	 * @param table 表名称
	 * @param fileds key:字段名称  list[0] 类型  list[1]长度 list[2]默认值 list[3]是否不填
	 */
	void alterTable(@Param("table")String table,@Param("fileds")Map fileds,@Param("type") String type);
	
	/**
	 * 删除表
	 * @param table 表名称
	 */
	void dropTable(@Param("table")String table);
	
	/**
	 * 导入执行数据
	 * @param sql　sql语句
	 */
	void excuteSql(@Param("sql")String sql);
	
	/**
	 * 批量新增
	 * @param list 新增数据
	 */
	void saveBatch(@Param("list")List list);
	
	
}
