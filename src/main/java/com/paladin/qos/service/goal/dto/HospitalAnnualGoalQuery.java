package com.paladin.qos.service.goal.dto;

import com.paladin.framework.common.OffsetPage;

public class HospitalAnnualGoalQuery extends OffsetPage {

    private String hospital;

    private String eventId;

    private String annual;

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

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }
}
