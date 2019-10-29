package com.paladin.qos.service.report.dto;

import com.google.common.base.Strings;

/**
 * @author MyKite
 * @version 2019年8月23日 下午4:24:42 
 */
public class ReportDataDTO {

    private String id;
    private Integer type;
    private String data;
    private String unitId;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getUnitId() {
        return unitId;
    }
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Override
    public String toString() {
        return "记录ID:" + ( Strings.isNullOrEmpty(id) ? '无' : id) + ",数据：" + data;
    }
}
