package com.paladin.qos.service.exhibition.vo;

import java.util.List;


public class ArchivesManagementVO {

    private String managedCenter;

    private Long peopleNumber;

    private Long activeArchivesNumber;

    private Long publicArchivesNumber;

    private Double createArchivesRate;

    private Double publicArchivesRate;

    public Double getCreateArchivesRate() {
        return createArchivesRate;
    }

    public void setCreateArchivesRate(Double createArchivesRate) {
        this.createArchivesRate = createArchivesRate;
    }

    public Double getPublicArchivesRate() {
        return publicArchivesRate;
    }

    public void setPublicArchivesRate(Double publicArchivesRate) {
        this.publicArchivesRate = publicArchivesRate;
    }

    public String getManagedCenter() {
        return managedCenter;
    }

    public void setManagedCenter(String managedCenter) {
        this.managedCenter = managedCenter;
    }

    public Long getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Long peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public Long getActiveArchivesNumber() {
        return activeArchivesNumber;
    }

    public void setActiveArchivesNumber(Long activeArchivesNumber) {
        this.activeArchivesNumber = activeArchivesNumber;
    }

    public Long getPublicArchivesNumber() {
        return publicArchivesNumber;
    }

    public void setPublicArchivesNumber(Long publicArchivesNumber) {
        this.publicArchivesNumber = publicArchivesNumber;
    }
}
