package com.paladin.qos.dynamic.model.exhibition;

/**
 * <婴幼儿先天性心脏病>
 *
 * @author Huangguochen
 * @create 2019/9/4 9:48
 */
public class InfantCongenitalHeartDisease {
    private  String agencyName;

    private  String agencyNo;

    private  Long heartDiseaseTotal;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyNo() {
        return agencyNo;
    }

    public void setAgencyNo(String agencyNo) {
        this.agencyNo = agencyNo;
    }

    public Long getHeartDiseaseTotal() {
        return heartDiseaseTotal;
    }

    public void setHeartDiseaseTotal(Long heartDiseaseTotal) {
        this.heartDiseaseTotal = heartDiseaseTotal;
    }
}
