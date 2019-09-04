package com.paladin.qos.service.exhibition.vo;

import java.util.List;

/**
 * <计划生育管理前台展示类>
 * @author Huangguochen
 * @create 2019/8/29 15:58
 */
public class FamilyPlanningManagementVO {

    /**避孕套发放数量*/
    private List<DataDemonstrationVO> condomDistribution;

    /**避孕药发放数量*/
    private List<DataDemonstrationVO> birthControlPills;

    /**宫内节育器放置*/
    private List<DataDemonstrationVO> intrauterineDevicePlacement;


    /**宫内节育器取出*/
    private List<DataDemonstrationVO> intrauterineDeviceRemoval;

    public List<DataDemonstrationVO> getCondomDistribution() {
        return condomDistribution;
    }

    public void setCondomDistribution(List<DataDemonstrationVO> condomDistribution) {
        this.condomDistribution = condomDistribution;
    }

    public List<DataDemonstrationVO> getBirthControlPills() {
        return birthControlPills;
    }

    public void setBirthControlPills(List<DataDemonstrationVO> birthControlPills) {
        this.birthControlPills = birthControlPills;
    }

    public List<DataDemonstrationVO> getIntrauterineDevicePlacement() {
        return intrauterineDevicePlacement;
    }

    public void setIntrauterineDevicePlacement(List<DataDemonstrationVO> intrauterineDevicePlacement) {
        this.intrauterineDevicePlacement = intrauterineDevicePlacement;
    }

    public List<DataDemonstrationVO> getIntrauterineDeviceRemoval() {
        return intrauterineDeviceRemoval;
    }

    public void setIntrauterineDeviceRemoval(List<DataDemonstrationVO> intrauterineDeviceRemoval) {
        this.intrauterineDeviceRemoval = intrauterineDeviceRemoval;
    }
}
