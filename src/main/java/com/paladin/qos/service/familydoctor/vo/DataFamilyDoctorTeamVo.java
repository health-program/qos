package com.paladin.qos.service.familydoctor.vo;

import com.paladin.qos.analysis.DataConstantContainer;

/**   
 * @author MyKite
 * @version 2019年9月17日 下午4:28:08 
 */
public class DataFamilyDoctorTeamVo {

    private String unitId;
    
    private int teamNum;//团队数量
    
    private int studioNum;//工作室数量
    
    private int doctorNum;//医生数量
    
    private int nurseNum;//护士数量
    
    private int healthCoordinatorNum;//健康协调员数量
    
    private int healthManageNum;//健康管理员数量
    
    private int dietitianNum;//营养师
    
    private int consultantNum;//心理咨询师
    
    public String getUnitName() {
        return DataConstantContainer.getUnitName(unitId);
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public int getDoctorNum() {
        return doctorNum;
    }

    public void setDoctorNum(int doctorNum) {
        this.doctorNum = doctorNum;
    }

    public int getNurseNum() {
        return nurseNum;
    }

    public void setNurseNum(int nurseNum) {
        this.nurseNum = nurseNum;
    }

    public int getHealthCoordinatorNum() {
        return healthCoordinatorNum;
    }

    public void setHealthCoordinatorNum(int healthCoordinatorNum) {
        this.healthCoordinatorNum = healthCoordinatorNum;
    }

    public int getHealthManageNum() {
        return healthManageNum;
    }

    public void setHealthManageNum(int healthManageNum) {
        this.healthManageNum = healthManageNum;
    }

    public int getDietitianNum() {
        return dietitianNum;
    }

    public void setDietitianNum(int dietitianNum) {
        this.dietitianNum = dietitianNum;
    }

    public int getConsultantNum() {
        return consultantNum;
    }

    public void setConsultantNum(int consultantNum) {
        this.consultantNum = consultantNum;
    }

    public int getStudioNum() {
        return studioNum;
    }

    public void setStudioNum(int studioNum) {
        this.studioNum = studioNum;
    }
}
