package com.paladin.qos.service.statistics.vo;

import com.paladin.qos.analysis.DataConstantContainer;

/**   
 * @author MyKite
 * @version 2019年8月30日 上午10:35:13 
 */
public class ExpensesVO {

    private String unitId;//医疗机构ID
    
    private int oPDCount;//门急诊总人次
    
    private double oPDMoney;//门急诊总费用(元)
    
    private double oPDAverageMoney;//门急诊人均费用(元)
    
    private int inPatientCount;//住院人次
    
    private double inPatientMoney;//住院费用(元)
    
    private double inPatietAverageMoney;//住院人均费用(元)
    
    private int inPersonCount;//转入人次
    
    private int outPersonCount;//转出人次
    
    private int oPDDrugCount;//门急诊使用药品数
    
    private int oPDDrugAvg;//门急诊人均使用药品数
    
    private int	inPatientDrugCount;//住院患者使用药品数
    
    private int inPatientDrugAvg;//住院人均使用药品数
    
    public String getUnitName() {
	return DataConstantContainer.getUnitName(unitId);
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId.trim();
    }

    public int getoPDCount() {
        return oPDCount;
    }

    public void setoPDCount(int oPDCount) {
        this.oPDCount = oPDCount;
    }

    public double getoPDMoney() {
        return oPDMoney;
    }

    public void setoPDMoney(double oPDMoney) {
        this.oPDMoney = oPDMoney;
    }

    public double getoPDAverageMoney() {
        return oPDAverageMoney;
    }

    public void setoPDAverageMoney(double oPDAverageMoney) {
        this.oPDAverageMoney = oPDAverageMoney;
    }

    public int getInPatientCount() {
        return inPatientCount;
    }

    public void setInPatientCount(int inPatientCount) {
        this.inPatientCount = inPatientCount;
    }

    public double getInPatientMoney() {
        return inPatientMoney;
    }

    public void setInPatientMoney(double inPatientMoney) {
        this.inPatientMoney = inPatientMoney;
    }

    public double getInPatietAverageMoney() {
        return inPatietAverageMoney;
    }

    public void setInPatietAverageMoney(double inPatietAverageMoney) {
        this.inPatietAverageMoney = inPatietAverageMoney;
    }

    public int getInPersonCount() {
        return inPersonCount;
    }

    public void setInPersonCount(int inPersonCount) {
        this.inPersonCount = inPersonCount;
    }

    public int getOutPersonCount() {
        return outPersonCount;
    }

    public void setOutPersonCount(int outPersonCount) {
        this.outPersonCount = outPersonCount;
    }

    public int getoPDDrugCount() {
        return oPDDrugCount;
    }

    public void setoPDDrugCount(int oPDDrugCount) {
        this.oPDDrugCount = oPDDrugCount;
    }

    public int getInPatientDrugCount() {
        return inPatientDrugCount;
    }

    public void setInPatientDrugCount(int inPatientDrugCount) {
        this.inPatientDrugCount = inPatientDrugCount;
    }

    public int getoPDDrugAvg() {
        return oPDDrugAvg;
    }

    public void setoPDDrugAvg(int oPDDrugAvg) {
        this.oPDDrugAvg = oPDDrugAvg;
    }

    public int getInPatientDrugAvg() {
        return inPatientDrugAvg;
    }

    public void setInPatientDrugAvg(int inPatientDrugAvg) {
        this.inPatientDrugAvg = inPatientDrugAvg;
    }

}
