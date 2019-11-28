package com.paladin.qos.service.school.dto;

import com.paladin.framework.excel.read.ReadProperty;

/**
 * @author MyKite
 * @version 2019年11月14日 下午2:43:05
 */
public class ExcelCdcTeam {

    // 疾控队伍名称
    @ReadProperty(cellIndex = 0)
    private String name;

    // 区域
    @ReadProperty(cellIndex = 1, enumType = "region-type")
    private Integer region;

    // 人均管理面积(KM2)
    @ReadProperty(cellIndex = 2)
    private String manageArea;

    // 人均管理学校数
    @ReadProperty(cellIndex = 3)
    private Integer manageSchoolAmount;

    // 人均处置疫情量
    @ReadProperty(cellIndex = 4)
    private String epidemicAmount;

    // 人居处置预警疫情量
    @ReadProperty(cellIndex = 5)
    private String warningEpidemicAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getManageArea() {
        return manageArea;
    }

    public void setManageArea(String manageArea) {
        this.manageArea = manageArea;
    }

    public Integer getManageSchoolAmount() {
        return manageSchoolAmount;
    }

    public void setManageSchoolAmount(Integer manageSchoolAmount) {
        this.manageSchoolAmount = manageSchoolAmount;
    }

    public String getEpidemicAmount() {
        return epidemicAmount;
    }

    public void setEpidemicAmount(String epidemicAmount) {
        this.epidemicAmount = epidemicAmount;
    }

    public String getWarningEpidemicAmount() {
        return warningEpidemicAmount;
    }

    public void setWarningEpidemicAmount(String warningEpidemicAmount) {
        this.warningEpidemicAmount = warningEpidemicAmount;
    }
    
}
