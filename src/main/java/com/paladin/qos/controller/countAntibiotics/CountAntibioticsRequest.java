package com.paladin.qos.controller.countAntibiotics;

import java.util.Date;

public class CountAntibioticsRequest {


    private String unitId;

    private Date month;

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
}
