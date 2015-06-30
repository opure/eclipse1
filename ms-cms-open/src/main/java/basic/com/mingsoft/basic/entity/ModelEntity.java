package com.mingsoft.basic.entity;

import com.mingsoft.base.entity.BaseEntity;
import java.sql.Timestamp;

/**
 * 
 * 
 * 
 * <p>
 * <b>铭飞科技流量推广软件</b>
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
 * @author killfen
 * 
 * @version 100-000-000
 * 
 *          <p>
 *          版权所有
 *          </p>
 * 
 *          <p>
 *          Comments:模块实体
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-6-16
 *          </p>
 * 
 *          <p>
 *          Modification history:暂无
 *          </p>
 */
public class ModelEntity extends BaseEntity {

    /**
     * 模块的编号
     */
    private int modelId;

    /**
     * 模块的标题
     */
    private String modelTitle;

    /**
     * 发布时间
     */
    private Timestamp modelDatetime;

    /**
     * 模块父id
     */
    private int modelModelId;

    /**
     * 链接地址
     */
    private String modelUrl;

    /**
     * 模块编码
     */
    private String modelCode;

    /**
     * 模块图标
     */
    private String modelIcon = null;
    
    /**
     *模块管理员Id
     */
    private int modelManagerId;

    /**
     * 获取modelCode
     * @return modelCode
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * 设置modelCode
     * @param modelCode
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    /**
     * 获取modelIcon
     * @return modelIcon
     */
    public String getModelIcon() {
        return modelIcon;
    }

    /**
     * 设置modelIcon
     * @param modelIcon
     */
    public void setModelIcon(String modelIcon) {
        this.modelIcon = modelIcon;
    }

    /**
     * 获取modelModelId
     * @return modelModelId
     */
    public int getModelModelId() {
        return modelModelId;
    }

    /**
     * 设置modelModelId
     * @param modelModelId
     */
    public void setModelModelId(int modelModelId) {
        this.modelModelId = modelModelId;
    }

    /**
     * 获取modelUrl
     * @return modelUrl
     */
    public String getModelUrl() {
        return modelUrl;
    }

    /**
     * 设置modelUrl
     * @param modelUrl
     */
    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }

    /**
     * 获取modelId
     * @return modelId
     */
    public int getModelId() {
        return modelId;
    }

    /**
     * 设置modelId
     * @param modelId
     */
    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    /**
     * 获取modelDatetime
     * @return modelDatetime
     */
    public Timestamp getModelDatetime() {
        return modelDatetime;
    }

    /**
     * 设置modelDatetime
     * @param modelDatetime
     */
    public void setModelDatetime(Timestamp modelDatetime) {
        this.modelDatetime = modelDatetime;
    }

    /**
     * 获取modelTitle
     * @return modelTitle
     */
    public String getModelTitle() {
        return modelTitle;
    }

    /**
     * 设置modelTitle
     * @param modelTitle
     */
    public void setModelTitle(String modelTitle) {
        this.modelTitle = modelTitle;
    }
    
    /**
     * 获取管理员id 
     * @return
     */
	public int getModelManagerId() {
		return modelManagerId;
	}
	
	/**
	 * 设置管理员id
	 * @param modelManagerId
	 */
	public void setModelManagerId(int modelManagerId) {
		this.modelManagerId = modelManagerId;
	}
}
