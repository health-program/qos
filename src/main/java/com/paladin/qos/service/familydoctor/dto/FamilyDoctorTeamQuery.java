package com.paladin.qos.service.familydoctor.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class FamilyDoctorTeamQuery extends OffsetPage {
    
    public String unitId;
    
    public String teamName;

    @QueryCondition(type = QueryType.LIKE)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @QueryCondition(type = QueryType.EQUAL)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

}