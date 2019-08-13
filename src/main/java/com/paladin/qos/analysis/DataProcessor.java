package com.paladin.qos.analysis;

import java.util.Calendar;
import java.util.Date;

public abstract class DataProcessor {

	/**
	 * 处理器ID，唯一
	 * @return
	 */
	public abstract String getProcessorId();

	public RateMetadata process(int timeType, Date timeValue, String unitId) {

		Date startTime = null;
		Date endTime = null;

		Calendar c = Calendar.getInstance();
		c.setTime(timeValue);

		if (timeType == RateMetadata.TIME_TYPE_YEAR) {
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DATE, 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
		} else if (timeType == RateMetadata.TIME_TYPE_MONTH) {
			c.set(Calendar.DATE, 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
		} else if (timeType == RateMetadata.TIME_TYPE_DAY) {
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
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
		
		endTime = c.getTime();

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
	 * @param startTime
	 * @param endTime
	 * @param unitId
	 * @return
	 */
	public abstract long getTotalNum(Date startTime, Date endTime, String unitId);

	/**
	 * 获取时间段内某机构的统计事件总数
	 * @param startTime
	 * @param endTime
	 * @param unitId
	 * @return
	 */
	public abstract long getEventNum(Date startTime, Date endTime, String unitId);

	
	
}
