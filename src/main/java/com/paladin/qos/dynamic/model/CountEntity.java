package com.paladin.qos.dynamic.model;

public class CountEntity {

	private String orgcode;
	
	private long outpatientNumber;//门诊数
	
	private long emergencyNumber;//急诊数
	
	private long patientsNumber;//门急诊总数
	
	private long prescriptionNumber;//处方数量
	
	private double prescriptionMoney;//处方总额
	
    private double maxPrescriptionMoney;//最大处方金额
    
    private double minPrescriptionMoney;//最小处方金额
    
    private double avgPrescriptionMoney;//平均处方金额
    
    private double medicalMoney;//医疗收入
    
    private double drugMoney;//药品收入
    
    private double otherMoney;//其他收入
    
    private double totalMoney;//总收入
    
    private long visitDoctorNumber;//出诊医生数
    
    private double averageNumber;//平均门急诊量
    
    private long inhospitalNumber;//住院人次
    
    private long outhospitalNumber;//出院人次
    
    private long onhospitalNumber;//在院人次
    
    private long ratedBedNumber;//额定床位
    
    private long bedInUseNumber;//使用
    
    
	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	
}
