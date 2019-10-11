package com.paladin.qos.service.epidemic.vo;


/**
 * @author 黄伟华
 * @version 2019年6月11日 下午4:15:29
 */
public class DataEpidemicSituationVO {

    private String schoolName;
    
    private Integer total;
    
    private Integer count;

    private String rate;

    public String getSchoolName() {
	return schoolName;
    }

    public void setSchoolName(String schoolName) {
	this.schoolName = schoolName;
    }

    public String getRate() {
	return rate;
    }

    public void setRate(String rate) {
	this.rate = rate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
