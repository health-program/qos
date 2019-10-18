package com.paladin.qos.service.gongwei.vo;


import com.paladin.qos.analysis.DataConstantContainer;

public class ArchivesMonthsVO {

    private String date;

    private Long archivesNumber;

    private Double createArchivesRate;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getArchivesNumber() {
        return archivesNumber;
    }

    public void setArchivesNumber(Long archivesNumber) {
        this.archivesNumber = archivesNumber;
    }

    public Double getCreateArchivesRate() {
        return createArchivesRate;
    }

    public void setCreateArchivesRate(Double createArchivesRate) {
        this.createArchivesRate = createArchivesRate;
    }
}
