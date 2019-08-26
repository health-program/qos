package com.paladin.qos.service.report.dto;

import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

/**   
 * @author MyKite
 * @version 2019年8月23日 下午4:44:05 
 */
public class ReportDataQueryDTO {

    private Integer type;
    
    private String unitId;

    @QueryCondition(type = QueryType.EQUAL)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @QueryCondition(type = QueryType.EQUAL)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
    
}
