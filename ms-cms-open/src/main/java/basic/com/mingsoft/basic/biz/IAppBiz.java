package com.mingsoft.basic.biz;


import com.mingsoft.basic.entity.AppEntity;


/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞Cms</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2013 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 史爱华
 * 
 * @version 100-000-000
 * 
 * <p>
 * 版权所有
 * </p>
 *  
 * <p>
 * Comments:网站基本信息业务层接口||继承IBasicBiz
 * </p>
 *  
 * <p>
 * Create Date:2014-07-14
 * </p>
 *
 * <p>
 * Modification history:暂无
 * </p>
 */
public interface IAppBiz extends IBasicBiz{
	/**
	 * 查找域名相同站点的个数
	 * @param url
	 * @return 站点个数
	 */
	int countByUrl(String websiteUrl);
	/**
	 * 根据站点管理员id查找站点
	 * @param managerId ：站点管理员id
	 * @return 站点实体
	 */
	AppEntity getByManagerId(int managerId);
	/**
	 * 根据站点域名获取站点实体
	 * @param url:域名
	 * @return
	 */
	AppEntity getByUrl(String websiteUrl);
}
