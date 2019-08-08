package com.paladin.qos.service.school.vo;
/**   
 * @author MyKite
 * @version 2019年7月25日 上午9:34:10 
 */
public class OrgSchoolDoctorCountVO {

    private String id;
    
    private String parentSchoolId;//学校ID
    
    private String schoolFullName;//学校名称
    
    private String schoolDoctorPart;//校医(兼职)人数
    
    private String schoolDoctorFull;//校医(专职)人数
    
    private String healthTeacherPart;//保健老师(兼职)人数
    
    private String healthTeacherFull;//保健老师(专职)人数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentSchoolId() {
        return parentSchoolId;
    }

    public void setParentSchoolId(String parentSchoolId) {
        this.parentSchoolId = parentSchoolId;
    }

    public String getSchoolFullName() {
        return schoolFullName;
    }

    public void setSchoolFullName(String schoolFullName) {
        this.schoolFullName = schoolFullName;
    }

    public String getSchoolDoctorPart() {
        return schoolDoctorPart;
    }

    public void setSchoolDoctorPart(String schoolDoctorPart) {
        this.schoolDoctorPart = schoolDoctorPart;
    }

    public String getSchoolDoctorFull() {
        return schoolDoctorFull;
    }

    public void setSchoolDoctorFull(String schoolDoctorFull) {
        this.schoolDoctorFull = schoolDoctorFull;
    }

    public String getHealthTeacherPart() {
        return healthTeacherPart;
    }

    public void setHealthTeacherPart(String healthTeacherPart) {
        this.healthTeacherPart = healthTeacherPart;
    }

    public String getHealthTeacherFull() {
        return healthTeacherFull;
    }

    public void setHealthTeacherFull(String healthTeacherFull) {
        this.healthTeacherFull = healthTeacherFull;
    }
    
}
