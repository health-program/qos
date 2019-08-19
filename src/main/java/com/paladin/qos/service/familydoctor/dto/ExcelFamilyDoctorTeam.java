package com.paladin.qos.service.familydoctor.dto;

import com.paladin.framework.excel.read.ReadProperty;

/**   
 * @author MyKite
 * @version 2019年8月14日 上午10:05:15 
 */
public class ExcelFamilyDoctorTeam {

    @ReadProperty(cellIndex = 0)
    private String teamName;
    
    @ReadProperty(cellIndex = 1)
    private String serviceDistrict;
    
    @ReadProperty(cellIndex = 2)
    private Integer servicePopulation;
    
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getServiceDistrict() {
        return serviceDistrict;
    }
    public void setServiceDistrict(String serviceDistrict) {
        this.serviceDistrict = serviceDistrict;
    }
    public Integer getServicePopulation() {
        return servicePopulation;
    }
    public void setServicePopulation(Integer servicePopulation) {
        this.servicePopulation = servicePopulation;
    }
   
}
