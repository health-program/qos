package com.paladin.qos.service.exhibition.vo;

/**
 * <前台数据展示类>
 * @author Huangguochen
 * @create 2019/8/28 11:27
 */
public class DataDemonstrationVO {

    private String searchTime;

    private Long total;

    private String agencyName;

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
}
