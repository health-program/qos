package com.paladin.qos.service.report.vo;

import com.paladin.qos.analysis.DataConstantContainer;

/**   
 * @author MyKite
 * @version 2019年8月23日 下午4:24:42 
 */
public class ReportDataVO{

    private String id;
    private Integer type;
    private String data;
    private String unitId;
    
    public String getUnitName() {
	return DataConstantContainer.getUnitName(unitId);
    }
    
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
    
}
