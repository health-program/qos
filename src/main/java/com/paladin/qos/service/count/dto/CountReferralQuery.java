package com.paladin.qos.service.count.dto;

import com.paladin.framework.common.OffsetPage;

public class CountReferralQuery extends OffsetPage {

    private String unitId;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

}