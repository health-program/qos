package com.paladin.qos.mapper.shejike;

import java.util.Date;

public interface SheJiKeMapper {
	//医生平均门急诊量
	long getAverageNumber(Date startTime, Date endTime, String unitId);
	//使用床位数
	long getBedInUseNumber(Date startTime, Date endTime, String unitId);
	//急诊人次数
	long getEmergencyNumber(Date startTime, Date endTime, String unitId);
	//住院人次数
	long getInhospitalNumber(Date startTime, Date endTime, String unitId);
	//在院人次数
	long getOnhospitalNumber(Date startTime, Date endTime, String unitId);
	//出院人次数
	long getOuthospitalNumber(Date startTime, Date endTime, String unitId);
	//门诊人次数
	long getOutpatientNumber(Date startTime, Date endTime, String unitId);
	//门急诊总数
	long getPatientsNumber(Date startTime, Date endTime, String unitId);
	//额定床位数
	long getRatedBedNumber(Date startTime, Date endTime, String unitId);
	//出诊医生数
	long getVisitDoctorNumber(Date startTime, Date endTime, String unitId);
	
	//病床总天数
	long getBedTotalDays(Date startTime, Date endTime, String unitId);
	//病床使用天数
	long getBedInUseDays(Date startTime, Date endTime, String unitId);
	//合计总收入
	double getTotalMoney(Date startTime, Date endTime, String unitId);
	//医疗收入
	double getMedicalMoney(Date startTime, Date endTime, String unitId);
	//药品收入
	int getDrugMoney(Date startTime, Date endTime, String unitId);
	//其他收入
	int getOtherMoney(Date startTime, Date endTime, String unitId);
	
	//处方数量
	long getPrescriptionNumber(Date startTime, Date endTime, String unitId);
	//处方总额
	double getPrescriptionMoney(Date startTime, Date endTime, String unitId);
	//最大处方金额
	double getMaxPrescriptionMoney(Date startTime, Date endTime, String unitId);
	//最小处方金额
	double getMinPrescriptionMoney(Date startTime, Date endTime, String unitId);
	//平均处方金额
	double getAvgPrescriptionMoney(Date startTime, Date endTime, String unitId);
	
	//门诊中医处方占比
	long getTotalChineseMedicinePrescription(Date startTime, Date endTime,
			String unitId);
	long getEventChineseMedicinePrescription(Date startTime, Date endTime,
			String unitId);
	//门诊中药饮片占比
	long getEventChineseDrinkMedicine(Date startTime, Date endTime,
			String unitId);
	//门诊中医非药物治疗处方占比
	long getEventNonChineseMedicinePrescription(Date startTime, Date endTime,
			String unitId);
	//激素使用率
	long getTotalHormone(Date startTime, Date endTime, String unitId);
	long getEventHormone(Date startTime, Date endTime, String unitId);
	//抗菌药物使用率
	double getTotalAntiDrug(Date startTime, Date endTime, String unitId);
	double getEventAntiDrug(Date startTime, Date endTime, String unitId);
	
	//一联抗生素使用率
	long getTotalRecipe(Date startTime, Date endTime, String unitId);
	long getEventOneAntiDrug(Date startTime, Date endTime, String unitId);
	//二联抗生素使用率
	long getEventTwoAntiDrug(Date startTime, Date endTime, String unitId);
	//三联抗生素使用率
	long getEventThreeAntiDrug(Date startTime, Date endTime, String unitId);
}
