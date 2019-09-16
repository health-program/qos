package com.paladin.qos.controller.exhibition.dto;

import java.util.Date;

public class ArchivesRequest {

  private String managedCenter;

  private String  startDate;

  private String  endDate;

    public String getManagedCenter() {
        return managedCenter;
    }

    public void setManagedCenter(String managedCenter) {
        this.managedCenter = managedCenter;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
