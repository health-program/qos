package com.paladin.qos.service.statistics.dto;


import com.paladin.framework.common.OffsetPage;

/**   
 * @author MyKite
 * @version 2019年11月16日 下午12:56:25 
 */
public class OpdQuery extends OffsetPage{

    private String dataSource;//医院
    
    private String deptId;//科室ID
    
    private String doctorName;//医生名称
    
    private String startTime;//开始时间
    
    private String endTime;//结束时间

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

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
    
}
