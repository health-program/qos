package com.paladin.qos.service.epidemic.dto;

import com.paladin.framework.common.OffsetPage;


/**   
 * @author MyKite
 * @version 2019年12月11日 上午11:21:59 
 */
public class EpidemicAnalysisQueryDTO extends OffsetPage{

    private String startTime;
    
    private String endTime;
    
    private String sicknessClassify; //报告病种
    
    private String schoolSection;
    
    private String[] schoolSectionIds;
    
    private String[] agencyId;
    
    private String regionId;
    
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getSicknessClassify() {
        return sicknessClassify;
    }
    public void setSicknessClassify(String sicknessClassify) {
        this.sicknessClassify = sicknessClassify;
    }
    
    public String[] getSchoolSectionIds() {
        return schoolSectionIds;
    }
    public void setSchoolSectionIds(String[] schoolSectionIds) {
        this.schoolSectionIds = schoolSectionIds;
    }
    public String getSchoolSection() {
        return schoolSection;
    }
    public void setSchoolSection(String schoolSection) {
        this.schoolSection = schoolSection;
    }
    public String[] getAgencyId() {
        return agencyId;
    }
    public void setAgencyId(String[] agencyId) {
        this.agencyId = agencyId;
    }
    public String getRegionId() {
        return regionId;
    }
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
