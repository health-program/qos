package com.paladin.qos.service.epidemic.dto;

import com.paladin.framework.common.OffsetPage;

/**   
 * @author 黄伟华
 * @version 2019年6月11日 下午4:14:28 
 */
public class EpidemicSituationQueryDTO extends OffsetPage{

    private String incidentUnit;//事发单位名称

    public String getIncidentUnit() {
        return incidentUnit;
    }

    public void setIncidentUnit(String incidentUnit) {
        this.incidentUnit = incidentUnit;
    }
    
}
