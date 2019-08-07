package com.paladin.qos.service.epidemic.dto;

import com.paladin.framework.common.OffsetPage;

/**   
 * @author 黄伟华
 * @version 2019年6月11日 下午4:14:28 
 */
public class EpidemicSituationQueryDTO extends OffsetPage{

    private String incidentUnit;//事发单位名称
    
    private String reportUnit;//报告单位
    
    private String region;//区域
    
    private String sicknessClassify;//病种分类

    public String getIncidentUnit() {
        return incidentUnit;
    }

    public void setIncidentUnit(String incidentUnit) {
        this.incidentUnit = incidentUnit;
    }

    public String getReportUnit() {
        return reportUnit;
    }

    public void setReportUnit(String reportUnit) {
        this.reportUnit = reportUnit;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSicknessClassify() {
        return sicknessClassify;
    }

    public void setSicknessClassify(String sicknessClassify) {
        this.sicknessClassify = sicknessClassify;
    }
    
}
