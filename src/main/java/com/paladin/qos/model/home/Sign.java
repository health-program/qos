package com.paladin.qos.model.home;

import java.util.Date;

public class Sign {

    private String signOrg;

    private String signName;

    private String signGender;

    private Date signDate;

    private String signTeam;

    private String signType;

    public String getSignOrg() {
        return signOrg;
    }

    public void setSignOrg(String signOrg) {
        this.signOrg = signOrg;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getSignGender() {
        return signGender;
    }

    public void setSignGender(String signGender) {
        this.signGender = signGender;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSignTeam() {
        return signTeam;
    }

    public void setSignTeam(String signTeam) {
        this.signTeam = signTeam;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
