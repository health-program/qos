package com.paladin.qos.service.address.vo;

import com.paladin.qos.analysis.DataConstantContainer;

public class AddressVo {

    private String unitId;

    private String orgName;

    private String orgAddress;

    private String orgLon;

    private String orgLat;

    private Integer orgType;

    public String getUnitName() {
        return DataConstantContainer.getUnitName(unitId);
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgLon() {
        return orgLon;
    }

    public void setOrgLon(String orgLon) {
        this.orgLon = orgLon;
    }

    public String getOrgLat() {
        return orgLat;
    }

    public void setOrgLat(String orgLat) {
        this.orgLat = orgLat;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }
}
