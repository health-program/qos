package com.paladin.qos.service.epidemic.vo;

/**
 * <疾病分类前台显示>
 *
 * @author Huangguochen
 * @create 2019/12/13 11:16
 */
public class DiseaseClassificationVO {

    private String region;

    private  Integer gardensNumber;

    private  Integer peoplesNumber;

    private  Integer epidemicNumber;

    private  Integer involvedPeoplesNumber;

    private  Integer peoplesAvgNumber;

    private  Integer startPeoplesNumber;

    private  Integer totalPeoplesNumber;

    private String agencyId;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getGardensNumber() {
        return gardensNumber;
    }

    public void setGardensNumber(Integer gardensNumber) {
        this.gardensNumber = gardensNumber;
    }

    public Integer getPeoplesNumber() {
        return peoplesNumber;
    }

    public void setPeoplesNumber(Integer peoplesNumber) {
        this.peoplesNumber = peoplesNumber;
    }

    public Integer getEpidemicNumber() {
        return epidemicNumber;
    }

    public void setEpidemicNumber(Integer epidemicNumber) {
        this.epidemicNumber = epidemicNumber;
    }

    public Integer getInvolvedPeoplesNumber() {
        return involvedPeoplesNumber;
    }

    public void setInvolvedPeoplesNumber(Integer involvedPeoplesNumber) {
        this.involvedPeoplesNumber = involvedPeoplesNumber;
    }

    public Integer getPeoplesAvgNumber() {
        return peoplesAvgNumber;
    }

    public void setPeoplesAvgNumber(Integer peoplesAvgNumber) {
        this.peoplesAvgNumber = peoplesAvgNumber;
    }

    public Integer getStartPeoplesNumber() {
        return startPeoplesNumber;
    }

    public void setStartPeoplesNumber(Integer startPeoplesNumber) {
        this.startPeoplesNumber = startPeoplesNumber;
    }

    public Integer getTotalPeoplesNumber() {
        return totalPeoplesNumber;
    }

    public void setTotalPeoplesNumber(Integer totalPeoplesNumber) {
        this.totalPeoplesNumber = totalPeoplesNumber;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
