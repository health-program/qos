package com.paladin.qos.service.epidemic.dto;

import java.util.Date;

import com.paladin.framework.excel.read.ReadProperty;

/**   
 * @author MyKite
 * @version 2019年8月15日 下午1:04:43 
 */
public class ExcelEpidemicSituation {
    
    @ReadProperty(cellIndex = 0)
    private String incidentUnit;//事发单位名称

    @ReadProperty(cellIndex = 1,enumType="report-unit-type")
    private String reportUnit;//报告单位（医疗机构为1，事发学校为2，家长为3，其他为4）

    @ReadProperty(cellIndex = 2,enumType="sickness-type")
    private String sicknessClassify;//病种分类

    @ReadProperty(cellIndex = 4)
    private String grade;//班级

    @ReadProperty(cellIndex = 3,enumType="region-type")
    private String region;//区域

    @ReadProperty(cellIndex = 8)
    private Integer firstWeek;//发病人数（首周）

    @ReadProperty(cellIndex = 9)
    private Integer total;//发病人数（累计）

    @ReadProperty(cellIndex = 10)
    private Integer vaccinationGradeNumber;//应急接种班级数

    @ReadProperty(cellIndex = 11)
    private Integer vaccinationPeopleNumber;//应急接种人数

    @ReadProperty(cellIndex = 5)
    private Integer suspendGradeNumber;//停课班级数
    
    @ReadProperty(cellIndex = 6)
    private Integer suspendPeopleNumber;//停课人数
    
    @ReadProperty(cellIndex = 7)
    private Date suspendData;//停课时间
    
    @ReadProperty(cellIndex = 12)
    private Integer municipalLeveNumber;//出动人次数(市级专业部门)
    
    @ReadProperty(cellIndex = 13)
    private Integer districtTownNumber;//出动人次数(区镇级专业部门)
    
    @ReadProperty(cellIndex = 16,enumType="boolean-type")
    private Integer isEarlyWarningValue;//是否预警值(是为1，否为2)
    
    @ReadProperty(cellIndex = 14,enumType="boolean-type")
    private Integer isReasonTraceability;//是否原因溯源(是为1，否为2)
    
    @ReadProperty(cellIndex = 15,enumType="boolean-type")
    private Integer isTraceabilityClear;//溯源是否清楚(是为1，否为2)
    
    @ReadProperty(cellIndex = 17)
    private String remarks;//备注

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

    public String getSicknessClassify() {
        return sicknessClassify;
    }

    public void setSicknessClassify(String sicknessClassify) {
        this.sicknessClassify = sicknessClassify;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getFirstWeek() {
        return firstWeek;
    }

    public void setFirstWeek(Integer firstWeek) {
        this.firstWeek = firstWeek;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getVaccinationGradeNumber() {
        return vaccinationGradeNumber;
    }

    public void setVaccinationGradeNumber(Integer vaccinationGradeNumber) {
        this.vaccinationGradeNumber = vaccinationGradeNumber;
    }

    public Integer getVaccinationPeopleNumber() {
        return vaccinationPeopleNumber;
    }

    public void setVaccinationPeopleNumber(Integer vaccinationPeopleNumber) {
        this.vaccinationPeopleNumber = vaccinationPeopleNumber;
    }

    public Integer getSuspendGradeNumber() {
        return suspendGradeNumber;
    }

    public void setSuspendGradeNumber(Integer suspendGradeNumber) {
        this.suspendGradeNumber = suspendGradeNumber;
    }

    public Integer getSuspendPeopleNumber() {
        return suspendPeopleNumber;
    }

    public void setSuspendPeopleNumber(Integer suspendPeopleNumber) {
        this.suspendPeopleNumber = suspendPeopleNumber;
    }

    public Date getSuspendData() {
        return suspendData;
    }

    public void setSuspendData(Date suspendData) {
        this.suspendData = suspendData;
    }

    public Integer getMunicipalLeveNumber() {
        return municipalLeveNumber;
    }

    public void setMunicipalLeveNumber(Integer municipalLeveNumber) {
        this.municipalLeveNumber = municipalLeveNumber;
    }

    public Integer getDistrictTownNumber() {
        return districtTownNumber;
    }

    public void setDistrictTownNumber(Integer districtTownNumber) {
        this.districtTownNumber = districtTownNumber;
    }

    public Integer getIsEarlyWarningValue() {
        return isEarlyWarningValue;
    }

    public void setIsEarlyWarningValue(Integer isEarlyWarningValue) {
        this.isEarlyWarningValue = isEarlyWarningValue;
    }

    public Integer getIsReasonTraceability() {
        return isReasonTraceability;
    }

    public void setIsReasonTraceability(Integer isReasonTraceability) {
        this.isReasonTraceability = isReasonTraceability;
    }

    public Integer getIsTraceabilityClear() {
        return isTraceabilityClear;
    }

    public void setIsTraceabilityClear(Integer isTraceabilityClear) {
        this.isTraceabilityClear = isTraceabilityClear;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
}
