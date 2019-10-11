package com.paladin.qos.service.school.vo;
/**   
 * @author MyKite
 * @version 2019年7月25日 上午9:34:10 
 */
public class OrgSchoolDoctorCountVO {

    private String parentSchoolId;//学校ID
    
    private String nature;//性质
    
    private String schoolFullName;//学校名称
    
    private Integer schoolDoctorPart;//校医(兼职)人数
    
    private Integer schoolDoctorFull;//校医(专职)人数
    
    private Integer healthTeacherPart;//保健老师(兼职)人数
    
    private Integer healthTeacherFull;//保健老师(专职)人数
    
    private Integer total;

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

    public Integer getSchoolDoctorPart() {
        return schoolDoctorPart;
    }

    public void setSchoolDoctorPart(Integer schoolDoctorPart) {
        this.schoolDoctorPart = schoolDoctorPart;
    }

    public Integer getSchoolDoctorFull() {
        return schoolDoctorFull;
    }

    public void setSchoolDoctorFull(Integer schoolDoctorFull) {
        this.schoolDoctorFull = schoolDoctorFull;
    }

    public Integer getHealthTeacherPart() {
        return healthTeacherPart;
    }

    public void setHealthTeacherPart(Integer healthTeacherPart) {
        this.healthTeacherPart = healthTeacherPart;
    }

    public Integer getHealthTeacherFull() {
        return healthTeacherFull;
    }

    public void setHealthTeacherFull(Integer healthTeacherFull) {
        this.healthTeacherFull = healthTeacherFull;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
    
}
