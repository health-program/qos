package com.paladin.qos.service.infectioncomplication.dto;

import com.paladin.framework.common.OffsetPage;

public class OperationComplicationQueryDTO extends  OffsetPage {
    
    private String unitId;

    private String [] unitIds;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getUnitIds() {
        return unitIds;
    }

    public void setUnitIds(String[] unitIds) {
        this.unitIds = unitIds;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
    
    
		
}
