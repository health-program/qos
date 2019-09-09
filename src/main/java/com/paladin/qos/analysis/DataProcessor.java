package com.paladin.qos.analysis;

import java.util.Calendar;
import java.util.Date;

/**
 * 数据处理器，对数据进行时间维度和机构维度的数据预处理，提高统计效率
 * 
 * @author TontoZhou
 * @since 2019年8月14日
 */
public abstract class DataProcessor {

	/** 时间粒度：按年 */
	public final static int TIME_GRANULARITY_YEAR = 10;
	/** 时间粒度：按月 */
	public final static int TIME_GRANULARITY_MONTH = 11;
	/** 时间粒度：按日 */
	public final static int TIME_GRANULARITY_DAY = 12;

	/**
	 * 处理器处理的事件ID
	 * 
	 * @return
	 */
	public abstract String getEventId();

	/**
	 * 处理时间粒度
	 * 
	 * @return
	 */
//	public int getTimeGranularity() {
//		return TIME_GRANULARITY_DAY;
//	}

	/**
	 * 处理数据，开始结束时间应当已经处理过（按照timeType），例如开始时间2019-8-13 00:00:00，结束时间为2019-8-14
	 * 00:00:00，不能带时分秒
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
		metadata.setEventId(getEventId());

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
