package com.paladin.qos.model.address;

import com.paladin.framework.common.BaseModel;
import com.paladin.qos.analysis.DataConstantContainer;

import javax.persistence.Id;

public class Address extends BaseModel {

    @Id
    private String unitId;

    private String orgName;

    private String orgAddress;

    private String orgLon;

    private String orgLat;

    private Integer orgType;


    public String getUnitId() {
        return unitId;
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
