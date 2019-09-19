package com.paladin.qos.service.gongwei.vo;


import com.paladin.qos.analysis.DataConstantContainer;

public class ArchivesManagementVO {

    private String unitId;

    private Long peopleNumber;

    private Long activeArchivesNumber;

    private Long publicArchivesNumber;

    private Double createArchivesRate;

    private Double publicArchivesRate;

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return DataConstantContainer.getUnitName(unitId);
    }

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
