package com.paladin.qos.service.goal.dto;

import com.paladin.framework.common.OffsetPage;

import java.util.List;

public class HospitalGoalAnalysisQuery extends OffsetPage {

    private String year;

    private String month;

    private String unitId;

    private String eventId;

    private List<String> eventIds;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public List<String> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<String> eventIds) {
        this.eventIds = eventIds;
    }
}
