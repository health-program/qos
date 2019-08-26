package com.paladin.qos.service.analysis.data;

import com.paladin.qos.analysis.DataConstantContainer;

public class DeathRate {

    private String unitId;
    
    private int year;
    private int month;
	private long eventNum;
	private long totalNum;
	
	private long deathTotalNum;//住院死亡率
	private long deathEventNum;
	
	private long newBabyDeathTotalNum;//新生儿住院总死亡率
	private long newBabyDeathEventNum;
	
	private long newBabyOperationDeathTotalNum;//新生儿手术患者住院死亡率
	private long newBabyOperationDeathEventNum;
	
	private long newBabyNonOperationDeathTotalNum;//新生儿非手术患者住院死亡率
	private long newBabyNonOperationDeathEventNum;
	
	private long newBabyWeightOneTotalNum;//新生儿患者出生体重<=750克死亡率
	private long newBabyWeightOneEventNum;
	
	private long newBabyWeightTwoTotalNum;//新生儿患者出生体重751-1000克死亡率
	private long newBabyWeightTwoEventNum;
	
	private long newBabyWeightThreeTotalNum;//新生儿患者出生体重1001-1800克死亡率
	private long newBabyWeightThreeEventNum;
	
	private long newBabyWeightFourTotalNum;//新生儿患者出生体重>=1801克死亡率
	private long newBabyWeightFourEventNum;
	
	
	public String getUnitName() {
		return DataConstantContainer.getUnitName(unitId);
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
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

	public long getDeathTotalNum() {
		return deathTotalNum;
	}

	public void setDeathTotalNum(long deathTotalNum) {
		this.deathTotalNum = deathTotalNum;
	}

	public long getDeathEventNum() {
		return deathEventNum;
	}

	public void setDeathEventNum(long deathEventNum) {
		this.deathEventNum = deathEventNum;
	}

	public long getNewBabyDeathTotalNum() {
		return newBabyDeathTotalNum;
	}

	public void setNewBabyDeathTotalNum(long newBabyDeathTotalNum) {
		this.newBabyDeathTotalNum = newBabyDeathTotalNum;
	}

	public long getNewBabyDeathEventNum() {
		return newBabyDeathEventNum;
	}

	public void setNewBabyDeathEventNum(long newBabyDeathEventNum) {
		this.newBabyDeathEventNum = newBabyDeathEventNum;
	}

	public long getNewBabyOperationDeathTotalNum() {
		return newBabyOperationDeathTotalNum;
	}

	public void setNewBabyOperationDeathTotalNum(
			long newBabyOperationDeathTotalNum) {
		this.newBabyOperationDeathTotalNum = newBabyOperationDeathTotalNum;
	}

	public long getNewBabyOperationDeathEventNum() {
		return newBabyOperationDeathEventNum;
	}

	public void setNewBabyOperationDeathEventNum(
			long newBabyOperationDeathEventNum) {
		this.newBabyOperationDeathEventNum = newBabyOperationDeathEventNum;
	}

	public long getNewBabyNonOperationDeathTotalNum() {
		return newBabyNonOperationDeathTotalNum;
	}

	public void setNewBabyNonOperationDeathTotalNum(
			long newBabyNonOperationDeathTotalNum) {
		this.newBabyNonOperationDeathTotalNum = newBabyNonOperationDeathTotalNum;
	}

	public long getNewBabyNonOperationDeathEventNum() {
		return newBabyNonOperationDeathEventNum;
	}

	public void setNewBabyNonOperationDeathEventNum(
			long newBabyNonOperationDeathEventNum) {
		this.newBabyNonOperationDeathEventNum = newBabyNonOperationDeathEventNum;
	}

	public long getNewBabyWeightOneTotalNum() {
		return newBabyWeightOneTotalNum;
	}

	public void setNewBabyWeightOneTotalNum(long newBabyWeightOneTotalNum) {
		this.newBabyWeightOneTotalNum = newBabyWeightOneTotalNum;
	}

	public long getNewBabyWeightOneEventNum() {
		return newBabyWeightOneEventNum;
	}

	public void setNewBabyWeightOneEventNum(long newBabyWeightOneEventNum) {
		this.newBabyWeightOneEventNum = newBabyWeightOneEventNum;
	}

	public long getNewBabyWeightTwoTotalNum() {
		return newBabyWeightTwoTotalNum;
	}

	public void setNewBabyWeightTwoTotalNum(long newBabyWeightTwoTotalNum) {
		this.newBabyWeightTwoTotalNum = newBabyWeightTwoTotalNum;
	}

	public long getNewBabyWeightTwoEventNum() {
		return newBabyWeightTwoEventNum;
	}

	public void setNewBabyWeightTwoEventNum(long newBabyWeightTwoEventNum) {
		this.newBabyWeightTwoEventNum = newBabyWeightTwoEventNum;
	}

	public long getNewBabyWeightThreeTotalNum() {
		return newBabyWeightThreeTotalNum;
	}

	public void setNewBabyWeightThreeTotalNum(long newBabyWeightThreeTotalNum) {
		this.newBabyWeightThreeTotalNum = newBabyWeightThreeTotalNum;
	}

	public long getNewBabyWeightThreeEventNum() {
		return newBabyWeightThreeEventNum;
	}

	public void setNewBabyWeightThreeEventNum(long newBabyWeightThreeEventNum) {
		this.newBabyWeightThreeEventNum = newBabyWeightThreeEventNum;
	}

	public long getNewBabyWeightFourTotalNum() {
		return newBabyWeightFourTotalNum;
	}

	public void setNewBabyWeightFourTotalNum(long newBabyWeightFourTotalNum) {
		this.newBabyWeightFourTotalNum = newBabyWeightFourTotalNum;
	}

	public long getNewBabyWeightFourEventNum() {
		return newBabyWeightFourEventNum;
	}

	public void setNewBabyWeightFourEventNum(long newBabyWeightFourEventNum) {
		this.newBabyWeightFourEventNum = newBabyWeightFourEventNum;
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

	
	
}
