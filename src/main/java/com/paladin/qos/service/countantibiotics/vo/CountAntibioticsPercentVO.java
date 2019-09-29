package com.paladin.qos.service.countantibiotics.vo;

import com.paladin.qos.analysis.DataConstantContainer;

/**   
 * @author MyKite
 * @version 2019年9月29日 下午4:15:36 
 */
public class CountAntibioticsPercentVO {

    private String unitId;
    private String userRate;
    
    public String getUnitName() {
  	return DataConstantContainer.getUnitName(unitId);
    }
    public String getUnitId() {
        return unitId;
    }
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
    public String getUserRate() {
        return userRate;
    }
    public void setUserRate(String userRate) {
        this.userRate = userRate;
    }
    
}
