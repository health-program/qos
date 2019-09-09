package com.paladin.qos.service.data.vo;

import java.util.Date;

public class DataProcessExceptionVO {

	// 
	private String id;

	// 事件ID
	private String eventId;
	
	// 医院ID
	private String unitId;

	// 处理日期
	private Date processDay;

	// 异常内容
	private String exception;

	// 创建时间
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Date getProcessDay() {
		return processDay;
	}

	public void setProcessDay(Date processDay) {
		this.processDay = processDay;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

}