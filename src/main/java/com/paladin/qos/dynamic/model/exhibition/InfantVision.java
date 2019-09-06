package com.paladin.qos.dynamic.model.exhibition;

/**
 * <婴幼儿视力>
 *
 * @author Huangguochen
 * @create 2019/9/4 13:26
 */
public class InfantVision {

    private  String agencyName;

    private  String agencyNo;

    private  Long infantVisionTotal;

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

    public Long getInfantVisionTotal() {
        return infantVisionTotal;
    }

    public void setInfantVisionTotal(Long infantVisionTotal) {
        this.infantVisionTotal = infantVisionTotal;
    }
}
