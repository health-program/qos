package com.paladin.qos.model.familydoctor;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;

public class FamilySign extends BaseModel {

        private Long  signPeople;

        private Long healthSignNumber;

        private Double  healthSignRateFree;

        private Double getHealthSignRate;

        private Long personalSignNumber;

        private Long signTotal;

        private Double personalSignRate;


    public Long getSignPeople() {
        return signPeople;
    }

    public void setSignPeople(Long signPeople) {
        this.signPeople = signPeople;
    }

    public Long getHealthSignNumber() {
        return healthSignNumber;
    }

    public void setHealthSignNumber(Long healthSignNumber) {
        this.healthSignNumber = healthSignNumber;
    }

    public Double getHealthSignRateFree() {
        return healthSignRateFree;
    }

    public void setHealthSignRateFree(Double healthSignRateFree) {
        this.healthSignRateFree = healthSignRateFree;
    }

    public Double getGetHealthSignRate() {
        return getHealthSignRate;
    }

    public void setGetHealthSignRate(Double getHealthSignRate) {
        this.getHealthSignRate = getHealthSignRate;
    }

    public Long getPersonalSignNumber() {
        return personalSignNumber;
    }

    public void setPersonalSignNumber(Long personalSignNumber) {
        this.personalSignNumber = personalSignNumber;
    }

    public Long getSignTotal() {
        return signTotal;
    }

    public void setSignTotal(Long signTotal) {
        this.signTotal = signTotal;
    }

    public Double getPersonalSignRate() {
        return personalSignRate;
    }

    public void setPersonalSignRate(Double personalSignRate) {
        this.personalSignRate = personalSignRate;
    }
}