package com.paladin.qos.service.familydoctor.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class FamilyDoctorPersonnelQuery extends OffsetPage {
    
    private String teamId;
    
    private String name;

    @QueryCondition(type = QueryType.LIKE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @QueryCondition(type = QueryType.EQUAL)
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

}