package com.paladin.qos.service.data.vo;

public class DataEventVO {

	//
	private String id;

	// 事件名称
	private String name;

	// 事件类型，概率 or 总数
	private Integer eventType;

	// 数据目标类型， 医院 or 社区 or 所有
	private Integer targetType;

	//
	private String content;

	// 是否需要实时
	private Integer realTimeEnabled;

	// 实时间隔时间，分钟
	private Integer realTimeInterval;

	// 是否启用
	private Integer enabled;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public Integer getTargetType() {
		return targetType;
	}

	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}

	public Integer getRealTimeEnabled() {
		return realTimeEnabled;
	}

	public void setRealTimeEnabled(Integer realTimeEnabled) {
		this.realTimeEnabled = realTimeEnabled;
	}

	public Integer getRealTimeInterval() {
		return realTimeInterval;
	}

	public void setRealTimeInterval(Integer realTimeInterval) {
		this.realTimeInterval = realTimeInterval;
	}

}