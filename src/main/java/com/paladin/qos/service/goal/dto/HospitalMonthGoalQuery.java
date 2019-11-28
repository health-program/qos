package com.paladin.qos.service.goal.dto;

import com.paladin.framework.common.OffsetPage;

public class HospitalMonthGoalQuery extends OffsetPage {

    private String hospital;

    private String eventId;

    private String month;

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
