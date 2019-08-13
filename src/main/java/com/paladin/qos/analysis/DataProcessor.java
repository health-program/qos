package com.paladin.qos.analysis;

import java.util.Calendar;
import java.util.Date;

public abstract class DataProcessor {

	/**
	 * 处理器处理的事件ID
	 * 
	 * @return
	 */
	public abstract String getEventId();

	/**
	 * 处理数据，开始结束时间应当已经处理过（按照timeType），例如开始时间2019-8-13 00:00:00，结束时间为2019-8-14 00:00:00，不能带时分秒
	 * 
	 * @param timeType
	 * @param startTime
	 * @param endTime
	 * @param unitId
	 * @return
	 */
	public RateMetadata processByDay(int timeType, Date startTime, Date endTime, String unitId) {

		Calendar c = Calendar.getInstance();
		c.setTime(startTime);

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int weekYear = c.get(Calendar.WEEK_OF_YEAR);
		int weekMonth = c.get(Calendar.WEEK_OF_MONTH);

		long totalNum = getTotalNum(startTime, endTime, unitId);
		long eventNum = getEventNum(startTime, endTime, unitId);

		RateMetadata metadata = new RateMetadata();
		metadata.setEventNum(eventNum);
		metadata.setTotalNum(totalNum);
		metadata.setUnitValue(unitId);
		metadata.setTimeType(timeType);
		metadata.setYear(year);
		metadata.setMonth(month);
		metadata.setDay(day);
		metadata.setWeekMonth(weekMonth);
		metadata.setWeekYear(weekYear);

		return metadata;
	}

	/**
	 * 
	 * 根据时间类型处理时间，得到正确的开始结束时间后调用数据处理
	 * 
	 * @param timeType
	 * @param timeValue
	 * @param unitId
	 * @return
	 */
	public RateMetadata process(int timeType, Date timeValue, String unitId) {

		Date startTime = TimeUtil.toDayTime(timeValue);

		Calendar c = Calendar.getInstance();
		c.setTime(startTime);

		if (timeType == RateMetadata.TIME_TYPE_YEAR) {
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);
		} else if (timeType == RateMetadata.TIME_TYPE_MONTH) {
			c.set(Calendar.DAY_OF_MONTH, 1);
		} 
		
		startTime = c.getTime();
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int weekYear = c.get(Calendar.WEEK_OF_YEAR);
		int weekMonth = c.get(Calendar.WEEK_OF_MONTH);

		if (timeType == RateMetadata.TIME_TYPE_YEAR) {
			c.add(Calendar.YEAR, 1);
		} else if (timeType == RateMetadata.TIME_TYPE_MONTH) {
			c.add(Calendar.MONTH, 1);
		} else if (timeType == RateMetadata.TIME_TYPE_DAY) {
			c.add(Calendar.DAY_OF_MONTH, 1);
		}

		Date endTime = c.getTime();

		long totalNum = getTotalNum(startTime, endTime, unitId);
		long eventNum = getEventNum(startTime, endTime, unitId);

		RateMetadata metadata = new RateMetadata();
		metadata.setEventNum(eventNum);
		metadata.setTotalNum(totalNum);
		metadata.setUnitValue(unitId);
		metadata.setTimeType(timeType);
		metadata.setYear(year);
		metadata.setMonth(month);
		metadata.setDay(day);
		metadata.setWeekMonth(weekMonth);
		metadata.setWeekYear(weekYear);

		return metadata;
	}

	/**
	 * 获取时间段内某机构的统计样本总数
	 * 
	 * @param startTime
	 * @param endTime
	 * @param unitId
	 * @return
	 */
	public abstract long getTotalNum(Date startTime, Date endTime, String unitId);

	/**
	 * 获取时间段内某机构的统计事件总数
	 * 
	 * @param startTime
	 * @param endTime
	 * @param unitId
	 * @return
	 */
	public abstract long getEventNum(Date startTime, Date endTime, String unitId);

}
