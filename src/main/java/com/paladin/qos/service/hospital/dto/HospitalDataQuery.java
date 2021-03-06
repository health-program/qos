package com.paladin.qos.service.hospital.dto;

import com.paladin.framework.common.OffsetPage;

import java.util.Date;

public class HospitalDataQuery  extends OffsetPage{
	
	private String dataSource;
	private String issuesType;
	private Date startTime;
	private Date endTime;
	private  String year;
	
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getIssuesType() {
		return issuesType;
	}
	public void setIssuesType(String issuesType) {
		this.issuesType = issuesType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
