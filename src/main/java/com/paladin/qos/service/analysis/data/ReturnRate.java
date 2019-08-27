package com.paladin.qos.service.analysis.data;

import com.paladin.qos.analysis.DataConstantContainer;

public class ReturnRate {
    
	private String unitId;
    private int year;
    private int month;
	private long eventNum;
	private long totalNum;
	
	private long within31DaysEventNum ;//住院患者出院31天内再住院率
	private long within31DaysTotalNum;
	
	private long infarctionEventNum;//急性心肌梗死再住院率	
	private long infarctionTotalNum;
	
	private long heartFailureEventNum;//充血性心力衰竭再住院率
	private long heartFailureTotalNum;
	
	private long brainBleedingEventNum;//脑出血和脑梗死再住院率
	private long brainBleedingTotalNum;
	
	private long brainInjuryEventNum;//创伤性颅脑损伤再住院率
	private long brainInjuryTotalNum;
	
	private long gastrointestinalBleedingEventNum;//消化道出血（无并发症）再住院率
	private long gastrointestinalBleedingTotalNum;
	
	private long bodyDamageEventNum;//累及身体多个部位的损伤再住院率
	private long bodyDamageTotaltNum;
	
	private long pneumoniaEventNum;//细菌性肺炎（成人、无并发症）再住院率
	private long pneumoniaTotalNum;
	
	private long lungDiseaseEventNum;//慢性阻塞性肺疾病再住院率
	private long lungDiseaseTotalNum;
	
	private long diabetesEventNum;//糖尿病伴短期与长期并发症再住院率
	private long diabetesTotalNum;
	
	private long goiterEventNum;//结节性甲状腺肿再住院率
	private long goiterTotalNum;
	
	private long appendicitisEventNum;//急性阑尾炎伴弥漫性腹膜炎及脓肿再住院率
	private long appendicitisTotalNum;
	
	private long prostateEventNum;//前列腺增生再住院率
	private long prostateTotalNum;
	
	private long kidneyFailureEventNum;//肾衰竭再住院率
	private long kidneyFailureTotalNum;
	
	private long bloodPoisoningEventNum;//败血症（成人）再住院率
	private long bloodPoisoningTotalNum;
	
	private long hypertensionEventNum;//高血压病（成人）再住院率
	private long hypertensionTotalNum;
	
	private long pancreatitisEventNum;//急性胰腺炎再住院率
	private long pancreatitisTotalNum;
	
	private long cancerEventNum;//恶性肿瘤术后化疗再住院率
	private long cancerTotalNum;
	
	
	private long malignantTumorEventNum;//恶性肿瘤维持性化学治疗再住院率
	private long malignantTumorTotalNum;
	
	
	
	public String getUnitName() {
		return DataConstantContainer.getUnitName(unitId);
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public long getEventNum() {
		return eventNum;
	}

	public void setEventNum(long eventNum) {
		this.eventNum = eventNum;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public long getWithin31DaysEventNum() {
		return within31DaysEventNum;
	}

	public void setWithin31DaysEventNum(long within31DaysEventNum) {
		this.within31DaysEventNum = within31DaysEventNum;
	}

	public long getWithin31DaysTotalNum() {
		return within31DaysTotalNum;
	}

	public void setWithin31DaysTotalNum(long within31DaysTotalNum) {
		this.within31DaysTotalNum = within31DaysTotalNum;
	}

	public long getInfarctionEventNum() {
		return infarctionEventNum;
	}

	public void setInfarctionEventNum(long infarctionEventNum) {
		this.infarctionEventNum = infarctionEventNum;
	}

	public long getInfarctionTotalNum() {
		return infarctionTotalNum;
	}

	public void setInfarctionTotalNum(long infarctionTotalNum) {
		this.infarctionTotalNum = infarctionTotalNum;
	}

	public long getHeartFailureEventNum() {
		return heartFailureEventNum;
	}

	public void setHeartFailureEventNum(long heartFailureEventNum) {
		this.heartFailureEventNum = heartFailureEventNum;
	}

	public long getHeartFailureTotalNum() {
		return heartFailureTotalNum;
	}

	public void setHeartFailureTotalNum(long heartFailureTotalNum) {
		this.heartFailureTotalNum = heartFailureTotalNum;
	}

	public long getBrainBleedingEventNum() {
		return brainBleedingEventNum;
	}

	public void setBrainBleedingEventNum(long brainBleedingEventNum) {
		this.brainBleedingEventNum = brainBleedingEventNum;
	}

	public long getBrainBleedingTotalNum() {
		return brainBleedingTotalNum;
	}

	public void setBrainBleedingTotalNum(long brainBleedingTotalNum) {
		this.brainBleedingTotalNum = brainBleedingTotalNum;
	}

	public long getBrainInjuryEventNum() {
		return brainInjuryEventNum;
	}

	public void setBrainInjuryEventNum(long brainInjuryEventNum) {
		this.brainInjuryEventNum = brainInjuryEventNum;
	}

	public long getBrainInjuryTotalNum() {
		return brainInjuryTotalNum;
	}

	public void setBrainInjuryTotalNum(long brainInjuryTotalNum) {
		this.brainInjuryTotalNum = brainInjuryTotalNum;
	}

	public long getGastrointestinalBleedingEventNum() {
		return gastrointestinalBleedingEventNum;
	}

	public void setGastrointestinalBleedingEventNum(
			long gastrointestinalBleedingEventNum) {
		this.gastrointestinalBleedingEventNum = gastrointestinalBleedingEventNum;
	}

	public long getGastrointestinalBleedingTotalNum() {
		return gastrointestinalBleedingTotalNum;
	}

	public void setGastrointestinalBleedingTotalNum(
			long gastrointestinalBleedingTotalNum) {
		this.gastrointestinalBleedingTotalNum = gastrointestinalBleedingTotalNum;
	}

	public long getBodyDamageEventNum() {
		return bodyDamageEventNum;
	}

	public void setBodyDamageEventNum(long bodyDamageEventNum) {
		this.bodyDamageEventNum = bodyDamageEventNum;
	}

	public long getBodyDamageTotaltNum() {
		return bodyDamageTotaltNum;
	}

	public void setBodyDamageTotaltNum(long bodyDamageTotaltNum) {
		this.bodyDamageTotaltNum = bodyDamageTotaltNum;
	}

	public long getPneumoniaEventNum() {
		return pneumoniaEventNum;
	}

	public void setPneumoniaEventNum(long pneumoniaEventNum) {
		this.pneumoniaEventNum = pneumoniaEventNum;
	}

	public long getPneumoniaTotalNum() {
		return pneumoniaTotalNum;
	}

	public void setPneumoniaTotalNum(long pneumoniaTotalNum) {
		this.pneumoniaTotalNum = pneumoniaTotalNum;
	}

	public long getLungDiseaseEventNum() {
		return lungDiseaseEventNum;
	}

	public void setLungDiseaseEventNum(long lungDiseaseEventNum) {
		this.lungDiseaseEventNum = lungDiseaseEventNum;
	}

	public long getLungDiseaseTotalNum() {
		return lungDiseaseTotalNum;
	}

	public void setLungDiseaseTotalNum(long lungDiseaseTotalNum) {
		this.lungDiseaseTotalNum = lungDiseaseTotalNum;
	}

	public long getDiabetesEventNum() {
		return diabetesEventNum;
	}

	public void setDiabetesEventNum(long diabetesEventNum) {
		this.diabetesEventNum = diabetesEventNum;
	}

	public long getDiabetesTotalNum() {
		return diabetesTotalNum;
	}

	public void setDiabetesTotalNum(long diabetesTotalNum) {
		this.diabetesTotalNum = diabetesTotalNum;
	}

	public long getGoiterEventNum() {
		return goiterEventNum;
	}

	public void setGoiterEventNum(long goiterEventNum) {
		this.goiterEventNum = goiterEventNum;
	}

	public long getGoiterTotalNum() {
		return goiterTotalNum;
	}

	public void setGoiterTotalNum(long goiterTotalNum) {
		this.goiterTotalNum = goiterTotalNum;
	}

	public long getAppendicitisEventNum() {
		return appendicitisEventNum;
	}

	public void setAppendicitisEventNum(long appendicitisEventNum) {
		this.appendicitisEventNum = appendicitisEventNum;
	}

	public long getAppendicitisTotalNum() {
		return appendicitisTotalNum;
	}

	public void setAppendicitisTotalNum(long appendicitisTotalNum) {
		this.appendicitisTotalNum = appendicitisTotalNum;
	}

	public long getProstateEventNum() {
		return prostateEventNum;
	}

	public void setProstateEventNum(long prostateEventNum) {
		this.prostateEventNum = prostateEventNum;
	}

	public long getProstateTotalNum() {
		return prostateTotalNum;
	}

	public void setProstateTotalNum(long prostateTotalNum) {
		this.prostateTotalNum = prostateTotalNum;
	}

	public long getKidneyFailureEventNum() {
		return kidneyFailureEventNum;
	}

	public void setKidneyFailureEventNum(long kidneyFailureEventNum) {
		this.kidneyFailureEventNum = kidneyFailureEventNum;
	}

	public long getKidneyFailureTotalNum() {
		return kidneyFailureTotalNum;
	}

	public void setKidneyFailureTotalNum(long kidneyFailureTotalNum) {
		this.kidneyFailureTotalNum = kidneyFailureTotalNum;
	}

	public long getBloodPoisoningEventNum() {
		return bloodPoisoningEventNum;
	}

	public void setBloodPoisoningEventNum(long bloodPoisoningEventNum) {
		this.bloodPoisoningEventNum = bloodPoisoningEventNum;
	}

	public long getBloodPoisoningTotalNum() {
		return bloodPoisoningTotalNum;
	}

	public void setBloodPoisoningTotalNum(long bloodPoisoningTotalNum) {
		this.bloodPoisoningTotalNum = bloodPoisoningTotalNum;
	}

	public long getHypertensionEventNum() {
		return hypertensionEventNum;
	}

	public void setHypertensionEventNum(long hypertensionEventNum) {
		this.hypertensionEventNum = hypertensionEventNum;
	}

	public long getHypertensionTotalNum() {
		return hypertensionTotalNum;
	}

	public void setHypertensionTotalNum(long hypertensionTotalNum) {
		this.hypertensionTotalNum = hypertensionTotalNum;
	}

	public long getPancreatitisEventNum() {
		return pancreatitisEventNum;
	}

	public void setPancreatitisEventNum(long pancreatitisEventNum) {
		this.pancreatitisEventNum = pancreatitisEventNum;
	}

	public long getPancreatitisTotalNum() {
		return pancreatitisTotalNum;
	}

	public void setPancreatitisTotalNum(long pancreatitisTotalNum) {
		this.pancreatitisTotalNum = pancreatitisTotalNum;
	}

	public long getCancerEventNum() {
		return cancerEventNum;
	}

	public void setCancerEventNum(long cancerEventNum) {
		this.cancerEventNum = cancerEventNum;
	}

	public long getCancerTotalNum() {
		return cancerTotalNum;
	}

	public void setCancerTotalNum(long cancerTotalNum) {
		this.cancerTotalNum = cancerTotalNum;
	}

	public long getMalignantTumorEventNum() {
		return malignantTumorEventNum;
	}

	public void setMalignantTumorEventNum(long malignantTumorEventNum) {
		this.malignantTumorEventNum = malignantTumorEventNum;
	}

	public long getMalignantTumorTotalNum() {
		return malignantTumorTotalNum;
	}

	public void setMalignantTumorTotalNum(long malignantTumorTotalNum) {
		this.malignantTumorTotalNum = malignantTumorTotalNum;
	}
	
	
}
