package com.paladin.qos.model.data;

import java.util.Date;

import javax.persistence.Id;

public class DataEvent {

	public static final int EVENT_TYPE_RATE = 1;
	public static final int EVENT_TYPE_COUNT = 2;

	public static final int TARGET_TYPE_ALL = 1;
	public static final int TARGET_TYPE_HOSPITAL = 2;
	public static final int TARGET_TYPE_COMMUNITY = 3;

	//
	@Id
	private String id;

	// 事件名称
	private String name;

	// 事件类型，概率 or 总数
	private Integer eventType;

	// 数据目标类型， 医院 or 社区 or 所有
	private Integer targetType;

	// 数据源
	private String dataSource;

	// 内容说明
	private String content;

	// 处理开始时间
	private Date processStartDate;

	// 最大迁移条数
	private Integer maximumProcess;

	// 是否单独处理线程
	private Integer separateProcessThread;

	// 数据归档策略
	private Integer filingStrategy;

	// 调度任务策略参数1，配合调度任务策略用
	private Integer filingStrategyParam1;

	// 调度任务策略参数2，配合调度任务策略用
	private String filingStrategyParam2;

	// 调度策略
	private Integer scheduleStrategy;

	// 调度任务策略参数1，配合调度任务策略用
	private Integer scheduleStrategyParam1;

	// 调度任务策略参数2，配合调度任务策略用
	private String scheduleStrategyParam2;

	// 是否需要实时
	private Integer realTimeEnabled;

	// 实时间隔，分钟
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

	public Integer getTargetType() {
		return targetType;
	}

	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public Integer getRealTimeInterval() {
		return realTimeInterval;
	}

	public void setRealTimeInterval(Integer realTimeInterval) {
		this.realTimeInterval = realTimeInterval;
	}

	public Integer getRealTimeEnabled() {
		return realTimeEnabled;
	}

	public void setRealTimeEnabled(Integer realTimeEnabled) {
		this.realTimeEnabled = realTimeEnabled;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Date getProcessStartDate() {
		return processStartDate;
	}

	public void setProcessStartDate(Date processStartDate) {
		this.processStartDate = processStartDate;
	}

	public Integer getMaximumProcess() {
		return maximumProcess;
	}

	public void setMaximumProcess(Integer maximumProcess) {
		this.maximumProcess = maximumProcess;
	}

	public Integer getSeparateProcessThread() {
		return separateProcessThread;
	}

	public void setSeparateProcessThread(Integer separateProcessThread) {
		this.separateProcessThread = separateProcessThread;
	}

	public Integer getFilingStrategy() {
		return filingStrategy;
	}

	public void setFilingStrategy(Integer filingStrategy) {
		this.filingStrategy = filingStrategy;
	}

	public Integer getFilingStrategyParam1() {
		return filingStrategyParam1;
	}

	public void setFilingStrategyParam1(Integer filingStrategyParam1) {
		this.filingStrategyParam1 = filingStrategyParam1;
	}

	public String getFilingStrategyParam2() {
		return filingStrategyParam2;
	}

	public void setFilingStrategyParam2(String filingStrategyParam2) {
		this.filingStrategyParam2 = filingStrategyParam2;
	}

	public Integer getScheduleStrategy() {
		return scheduleStrategy;
	}

	public void setScheduleStrategy(Integer scheduleStrategy) {
		this.scheduleStrategy = scheduleStrategy;
	}

	public Integer getScheduleStrategyParam1() {
		return scheduleStrategyParam1;
	}

	public void setScheduleStrategyParam1(Integer scheduleStrategyParam1) {
		this.scheduleStrategyParam1 = scheduleStrategyParam1;
	}

	public String getScheduleStrategyParam2() {
		return scheduleStrategyParam2;
	}

	public void setScheduleStrategyParam2(String scheduleStrategyParam2) {
		this.scheduleStrategyParam2 = scheduleStrategyParam2;
	}

}