package com.paladin.qos.service.school.dto;

import com.paladin.framework.excel.read.ReadProperty;

/**
 * @author MyKite
 * @version 2019年8月15日 下午2:32:14
 */
public class ExcelOrgSchool {

    @ReadProperty(cellIndex = 0)
    private String parentSchoolName;// 学校名称

    @ReadProperty(cellIndex = 1)
    private String schoolYear;//学年

    @ReadProperty(cellIndex = 2, enumType = "nature-type")
    private Integer nature;// 性质

    @ReadProperty(cellIndex = 3, enumType = "subordination-type")
    private Integer affiliation;// 隶属关系

    @ReadProperty(cellIndex = 4, enumType = "region-type")
    private String district;// 区域

    @ReadProperty(cellIndex = 5,enumType = "business-region-type")
    private String businessDistrict;// 业务管理区域
    
    @ReadProperty(cellIndex = 6)
    private String mainLeaderName;// 主要领导姓名

    @ReadProperty(cellIndex = 7)
    private String mainLeaderCellphone;// 主要领导电话

    @ReadProperty(cellIndex = 8)
    private String chargeLeaderName;// 分管领导姓名

    @ReadProperty(cellIndex = 9)
    private String chargeLeaderCellphone;// 分管领导电话

    @ReadProperty(cellIndex = 10)
    private String specificChargeLeaderName;// 具体负责人姓名

    @ReadProperty(cellIndex = 11)
    private String specificChargeLeaderCellphone;// 具体负责人电话

    @ReadProperty(cellIndex = 12)
    private Integer schoolDoctorFull;// 校医人数

    @ReadProperty(cellIndex = 13)
    private Integer healthTeacherFull;// 保健老师(专职)人数

    @ReadProperty(cellIndex = 14)
    private Integer healthTeacherPart;// 保健老师(兼职)人数

    @ReadProperty(cellIndex = 15)
    private Integer logistics;// 后勤保障人数
    
    @ReadProperty(cellIndex = 16)
    private Integer teaching;// 教职员工人数

    @ReadProperty(cellIndex = 17)
    private String address;// 地址

    @ReadProperty(cellIndex = 18, enumType = "school-section-type")
    private Integer schoolSection;// 学段

    @ReadProperty(cellIndex = 19, enumType = "grade-type")
    private Integer grade;// 年级

    @ReadProperty(cellIndex = 20)
    private String klass;// 班级

    @ReadProperty(cellIndex = 21)
    private Integer gradeTotal;// 年级学生人数

    @ReadProperty(cellIndex = 22)
    private Integer boarders;// 寄宿生人数

    @ReadProperty(cellIndex = 23)
    private String hygieneHealth;// 卫生保健人员

    public String getParentSchoolName() {
	return parentSchoolName;
    }

    public void setParentSchoolName(String parentSchoolName) {
	this.parentSchoolName = parentSchoolName;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public Integer getAffiliation() {
	return affiliation;
    }

    public void setAffiliation(Integer affiliation) {
	this.affiliation = affiliation;
    }

    public String getDistrict() {
	return district;
    }

    public void setDistrict(String district) {
	this.district = district;
    }

    public Integer getNature() {
	return nature;
    }

    public void setNature(Integer nature) {
	this.nature = nature;
    }

    public String getMainLeaderName() {
	return mainLeaderName;
    }

    public void setMainLeaderName(String mainLeaderName) {
	this.mainLeaderName = mainLeaderName;
    }

    public String getMainLeaderCellphone() {
	return mainLeaderCellphone;
    }

    public void setMainLeaderCellphone(String mainLeaderCellphone) {
	this.mainLeaderCellphone = mainLeaderCellphone;
    }

    public String getChargeLeaderName() {
	return chargeLeaderName;
    }

    public void setChargeLeaderName(String chargeLeaderName) {
	this.chargeLeaderName = chargeLeaderName;
    }

    public String getChargeLeaderCellphone() {
	return chargeLeaderCellphone;
    }

    public void setChargeLeaderCellphone(String chargeLeaderCellphone) {
	this.chargeLeaderCellphone = chargeLeaderCellphone;
    }

    public String getSpecificChargeLeaderName() {
	return specificChargeLeaderName;
    }

    public void setSpecificChargeLeaderName(String specificChargeLeaderName) {
	this.specificChargeLeaderName = specificChargeLeaderName;
    }

    public String getSpecificChargeLeaderCellphone() {
	return specificChargeLeaderCellphone;
    }

    public void setSpecificChargeLeaderCellphone(
	    String specificChargeLeaderCellphone) {
	this.specificChargeLeaderCellphone = specificChargeLeaderCellphone;
    }

    public Integer getLogistics() {
	return logistics;
    }

    public void setLogistics(Integer logistics) {
	this.logistics = logistics;
    }

    public Integer getHealthTeacherPart() {
	return healthTeacherPart;
    }

    public void setHealthTeacherPart(Integer healthTeacherPart) {
	this.healthTeacherPart = healthTeacherPart;
    }

    public Integer getSchoolDoctorFull() {
	return schoolDoctorFull;
    }

    public void setSchoolDoctorFull(Integer schoolDoctorFull) {
	this.schoolDoctorFull = schoolDoctorFull;
    }

    public Integer getHealthTeacherFull() {
	return healthTeacherFull;
    }

    public void setHealthTeacherFull(Integer healthTeacherFull) {
	this.healthTeacherFull = healthTeacherFull;
    }

    public Integer getSchoolSection() {
	return schoolSection;
    }

    public void setSchoolSection(Integer schoolSection) {
	this.schoolSection = schoolSection;
    }

    public Integer getGrade() {
	return grade;
    }

    public void setGrade(Integer grade) {
	this.grade = grade;
    }

    public Integer getGradeTotal() {
	return gradeTotal;
    }

    public void setGradeTotal(Integer gradeTotal) {
	this.gradeTotal = gradeTotal;
    }

    public Integer getBoarders() {
	return boarders;
    }

    public void setBoarders(Integer boarders) {
	this.boarders = boarders;
    }

    public Integer getTeaching() {
	return teaching;
    }

    public void setTeaching(Integer teaching) {
	this.teaching = teaching;
    }

    public String getSchoolYear() {
	return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
	this.schoolYear = schoolYear;
    }

    public String getKlass() {
	return klass;
    }

    public void setKlass(String klass) {
	this.klass = klass;
    }

    public String getBusinessDistrict() {
        return businessDistrict;
    }

    public void setBusinessDistrict(String businessDistrict) {
        this.businessDistrict = businessDistrict;
    }

    public String getHygieneHealth() {
        return hygieneHealth;
    }

    public void setHygieneHealth(String hygieneHealth) {
        this.hygieneHealth = hygieneHealth;
    }

}
