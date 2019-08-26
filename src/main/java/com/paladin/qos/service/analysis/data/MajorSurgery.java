package com.paladin.qos.service.analysis.data;

import com.paladin.qos.analysis.DataConstantContainer;

public class MajorSurgery {

    private String unitId;
    private int year;
    private int month;
	private long eventNum;
	private long totalNum;
	
	private long bypassGraftingEventNum;//冠状动脉旁路移植术死亡率
	private long bypassGraftingTotalNum;
	
	private long interventionEventNum;//经皮冠状动脉介入治疗死亡率
	private long interventionTotalNum;
	
	private long replacementEventNum;//髋膝关节置换术死亡率
	private long replacementTotalNum;

    private long brainSurgeryEventNum;//颅脑手术死亡率
    private long brainSurgeryTotalNum;
    
    private long cesareanEventNum;//剖宫产死亡率
    private long cesareanTotalNum;


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


	public long getBypassGraftingEventNum() {
		return bypassGraftingEventNum;
	}


	public void setBypassGraftingEventNum(long bypassGraftingEventNum) {
		this.bypassGraftingEventNum = bypassGraftingEventNum;
	}


	public long getBypassGraftingTotalNum() {
		return bypassGraftingTotalNum;
	}


	public void setBypassGraftingTotalNum(long bypassGraftingTotalNum) {
		this.bypassGraftingTotalNum = bypassGraftingTotalNum;
	}


	public long getInterventionEventNum() {
		return interventionEventNum;
	}


	public void setInterventionEventNum(long interventionEventNum) {
		this.interventionEventNum = interventionEventNum;
	}


	public long getInterventionTotalNum() {
		return interventionTotalNum;
	}


	public void setInterventionTotalNum(long interventionTotalNum) {
		this.interventionTotalNum = interventionTotalNum;
	}


	public long getReplacementEventNum() {
		return replacementEventNum;
	}


	public void setReplacementEventNum(long replacementEventNum) {
		this.replacementEventNum = replacementEventNum;
	}


	public long getReplacementTotalNum() {
		return replacementTotalNum;
	}


	public void setReplacementTotalNum(long replacementTotalNum) {
		this.replacementTotalNum = replacementTotalNum;
	}


	public long getBrainSurgeryEventNum() {
		return brainSurgeryEventNum;
	}


	public void setBrainSurgeryEventNum(long brainSurgeryEventNum) {
		this.brainSurgeryEventNum = brainSurgeryEventNum;
	}


	public long getBrainSurgeryTotalNum() {
		return brainSurgeryTotalNum;
	}


	public void setBrainSurgeryTotalNum(long brainSurgeryTotalNum) {
		this.brainSurgeryTotalNum = brainSurgeryTotalNum;
	}


	public long getCesareanEventNum() {
		return cesareanEventNum;
	}


	public void setCesareanEventNum(long cesareanEventNum) {
		this.cesareanEventNum = cesareanEventNum;
	}


	public long getCesareanTotalNum() {
		return cesareanTotalNum;
	}


	public void setCesareanTotalNum(long cesareanTotalNum) {
		this.cesareanTotalNum = cesareanTotalNum;
	}
	
	
}
