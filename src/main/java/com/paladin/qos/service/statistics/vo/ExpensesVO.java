package com.paladin.qos.service.statistics.vo;

import com.paladin.qos.analysis.DataConstantContainer;

/**   
 * @author MyKite
 * @version 2019年8月30日 上午10:35:13 
 */
public class ExpensesVO {

    private String unitId;//医疗机构ID
    
    private int inPersonCount;//转入人次
    
    private int outPersonCount;//转出人次   
    
    public String getUnitName() {
	return DataConstantContainer.getUnitName(unitId);
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId.trim();
    }

    public int getInPersonCount() {
        return inPersonCount;
    }

    public void setInPersonCount(int inPersonCount) {
        this.inPersonCount = inPersonCount;
    }

    public int getOutPersonCount() {
        return outPersonCount;
    }

    public void setOutPersonCount(int outPersonCount) {
        this.outPersonCount = outPersonCount;
    }

}
