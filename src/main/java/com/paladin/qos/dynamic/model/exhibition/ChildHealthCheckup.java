package com.paladin.qos.dynamic.model.exhibition;

/**
 * <儿童健康体检>
 *
 * @author Huangguochen
 * @create 2019/9/4 11:17
 */
public class ChildHealthCheckup {
    private  String agencyName;

    private  String agencyNo;

    private  Long childHealthCheckup;

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

    public Long getChildHealthCheckup() {
        return childHealthCheckup;
    }

    public void setChildHealthCheckup(Long childHealthCheckup) {
        this.childHealthCheckup = childHealthCheckup;
    }
}
