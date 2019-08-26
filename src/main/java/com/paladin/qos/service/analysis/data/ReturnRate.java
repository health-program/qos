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
	
	/*private long   ;//充血性心力衰竭再住院率
	private long   ;
	
	private long   ;//脑出血和脑梗死再住院率
	private long   ;
	
	private long   ;//创伤性颅脑损伤再住院率
	private long   ;
	
	private long   ;//消化道出血（无并发症）再住院率
	private long   ;
	
	private long   ;//累及身体多个部位的损伤再住院率
	private long   ;
	
	private long   ;//细菌性肺炎（成人、无并发症）再住院率
	private long   ;
	
	private long   ;//慢性阻塞性肺疾病再住院率
	private long   ;
	
	private long   ;//糖尿病伴短期与长期并发症再住院率
	private long   ;
	
	private long   ;//结节性甲状腺肿再住院率
	private long   ;
	
	private long   ;//急性阑尾炎伴弥漫性腹膜炎及脓肿再住院率
	private long   ;
	
	private long   ;//前列腺增生再住院率
	private long   ;
	
	private long   ;//肾衰竭再住院率
	private long   ;
	
	private long   ;//败血症（成人）再住院率
	private long   ;
	
	private long   ;//高血压病（成人）再住院率
	private long   ;
	
	private long   ;//急性胰腺炎再住院率
	private long   ;
	
	private long   ;//恶性肿瘤术后化疗再住院率
	private long   ;
	
	
	private long   ;//恶性肿瘤维持性化学治疗再住院率
	private long   ;*/
	
	
	
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
	
	
}
