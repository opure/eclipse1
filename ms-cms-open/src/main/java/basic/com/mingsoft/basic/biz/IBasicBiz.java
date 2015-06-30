package com.mingsoft.basic.biz;


import java.util.List;
import java.util.Map;

import com.mingsoft.base.biz.IBaseBiz;
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
 *          Comments:基本信息的业务层(接口)
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
public interface IBasicBiz extends IBaseBiz {

    int saveBasic(BasicEntity basic);

    void updateBasic(BasicEntity basic);
    
    void deleteBasic(int basicId);
    
    BasicEntity getBasicEntity(int basicId);
    
    /**
     * 根据点击次数
     * @param basicId 信息编号
     * @param num null:为递增
     * @return　最新的点击数
     */
    void updateHit(int basicId,Integer num);
    

    
    /**
     * 根据分类统计总数
     * @param categoryId　分类编号
     * @return　总数
     */
    int count(int categoryId);
    
    /**
     * 根据分类与关键子统计总数
     * @param categoryId　分类编号
     * @param keyWord  关键字
     * @return　总数
     */
    int count(int categoryId,String keyWord);
    
    
    /**
     * 根据分类查询
     * @param categoryId 　分类编号
     * @return 列表
     */
    List<BasicEntity> query(int categoryId);
    
    /**
     * 根据分类与关键子统计总数
     * @param categoryId　分类编号
     * @param keyWord　关键字
     * @return　列表
     */
    List<BasicEntity> query(int categoryId,String keyWord);
    
    
    /**
     * 根据分类与关键子统计总数
     * @param appId　应用编号
     * @param categoryId　分类编号
     * @param keyWord　关键字
     * @param page　分页
     * @param modelId　模块编号
     *  @param where　条件
     * @return　列表
     */
    List<BasicEntity> query(Integer appId,Integer categoryId,String keyWord,PageUtil page,Integer modelId,Map where);
    
    
    
    
}
