package com.paladin.qos.service.statistics.vo;

import java.util.Date;

/**   
 * @author MyKite
 * @version 2019年11月16日 下午1:26:33 
 */
public class OpdLogVO {

    private String unitName;//单位名称
    
    private String deptName;//科室名称
    
    private String doctorName;//医生名称
    
    private String patientName;//患者名称
    
    private String diseaseName;//诊断疾病名称
    
    private Date visitingTime;//就诊时间

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public Date getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(Date visitingTime) {
        this.visitingTime = visitingTime;
    }
    
}
