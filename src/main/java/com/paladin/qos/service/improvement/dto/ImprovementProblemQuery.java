package com.paladin.qos.service.improvement.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;
import com.paladin.qos.model.improvement.ImprovementProblem;

import java.util.Date;

public class ImprovementProblemQuery extends OffsetPage {

    private Date bgTime;

    private Date endTime;


    @QueryCondition(name = ImprovementProblem.COLUMN_FIELD_CREATE_TIME,type = QueryType.GREAT_EQUAL)
    public Date getBgTime() {
        return bgTime;
    }

    public void setBgTime(Date bgTime) {
        this.bgTime = bgTime;
    }
    @QueryCondition(name = ImprovementProblem.COLUMN_FIELD_CREATE_TIME,type = QueryType.LESS_THAN)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}