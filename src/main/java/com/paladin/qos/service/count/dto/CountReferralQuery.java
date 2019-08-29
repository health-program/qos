package com.paladin.qos.service.count.dto;

import com.paladin.framework.common.OffsetPage;

public class CountReferralQuery extends OffsetPage {

    private String unitId;

    private String [] unitIds;

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