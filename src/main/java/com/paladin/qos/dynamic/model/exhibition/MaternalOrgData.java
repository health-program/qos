package com.paladin.qos.dynamic.model.exhibition;

/**
 * <按机构过获取孕产妇数据>
 *
 * @author Huangguochen
 * @create 2019/9/6 14:11
 */
public class MaternalOrgData {

    private  String agencyName;

    private  String agencyNo;

    private  Long zyjk;

    private  Long yfjk;

    private  Long yffs;

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

    public Long getZyjk() {
        return zyjk;
    }

    public void setZyjk(Long zyjk) {
        this.zyjk = zyjk;
    }

    public Long getYfjk() {
        return yfjk;
    }

    public void setYfjk(Long yfjk) {
        this.yfjk = yfjk;
    }

    public Long getYffs() {
        return yffs;
    }

    public void setYffs(Long yffs) {
        this.yffs = yffs;
    }
}
