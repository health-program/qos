package com.paladin.qos.analysis;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.spring.SpringBeanHelper;
import com.paladin.framework.spring.SpringContainer;
import com.paladin.qos.model.data.DataProcessedDay;
import com.paladin.qos.service.data.DataProcessedDayService;

@Component
public class DataProcessContainer implements SpringContainer {

	private static Logger logger = LoggerFactory.getLogger(DataProcessContainer.class);
	
	private Map<String, DataProcessor> processorMap = new HashMap<>();
	private Set<String> eventSet = new HashSet<>();

	@Autowired
	private DataProcessedDayService dataProcessedDayService;

	@Override
	public boolean initialize() {

		Map<String, DataProcessor> processorSpringMap = SpringBeanHelper.getBeansByType(DataProcessor.class);
		Map<String, DataProcessor> processorMap = new HashMap<>();
		Set<String> eventSet = new HashSet<>();

		for (Entry<String, DataProcessor> entry : processorSpringMap.entrySet()) {
			DataProcessor processor = entry.getValue();

			String eventId = processor.getEventId();

			if (processorMap.containsKey(eventId)) {
				logger.warn("===>已经存在数据预处理器[EVENT_ID:" + eventId + "]，该处理器会被忽略");
				continue;
			}

			processorMap.put(eventId, processor);
			eventSet.add(eventId);
		}

		this.processorMap = Collections.unmodifiableMap(processorMap);
		this.eventSet = Collections.unmodifiableSet(eventSet);

		return true;
	}

	/**
	 * 获取事件集合
	 * 
	 * @return
	 */
	public Collection<String> getEvents() {
		return eventSet;
	}

	/**
	 * 获取数据处理器
	 * 
	 * @param eventId
	 * @return
	 */
	public DataProcessor getDataProcessor(String eventId) {
		return processorMap.get(eventId);
	}

	/**
	 * 处理数据
	 * 
	 * @param startTime
	 * @param endTime
	 * @param unitIds
	 * @param eventId
	 */
	public void processData(Date startTime, Date endTime, List<String> unitIds, String eventId) {

		if (unitIds == null || unitIds.size() == 0) {
			return;
		}

		if (startTime == null) {
			startTime = new Date();
		}

		if (endTime == null) {
			endTime = new Date();
		}

		DataProcessor processor = processorMap.get(eventId);

		if (processor == null) {
			logger.error("找到事件[" + eventId + "]对应的处理器");
			return;
		}

		int timeGranularity = processor.getTimeGranularity();
		if (timeGranularity == DataProcessor.TIME_GRANULARITY_DAY) {
			processDataForDay(startTime, endTime, unitIds, processor);
		} else if (timeGranularity == DataProcessor.TIME_GRANULARITY_MONTH) {

		} else if (timeGranularity == DataProcessor.TIME_GRANULARITY_YEAR) {

		}
	}

	/**
	 * 按天时间粒度处理时间段内数据
	 * 
	 * @param startTime
	 * @param endTime
	 * @param eventId
	 */
	private void processDataForDay(Date startTime, Date endTime, List<String> unitIds, DataProcessor processor) {

		startTime = TimeUtil.toDayTime(startTime);
		endTime = TimeUtil.toDayTime(endTime);

		long startMillis = startTime.getTime();
		long endMillis = endTime.getTime();

		if (startMillis > endMillis) {
			return;
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// 现在分析粒度为：天，可以加入其它粒度分析
		while (startMillis <= endMillis) {
			Date start = new Date(startMillis);
			startMillis += TimeUtil.MILLIS_IN_DAY;
			Date end = new Date(startMillis);

			for (String unitId : unitIds) {
				try {
					RateMetadata rateMetadata = processor.processByDay(DataProcessor.TIME_GRANULARITY_DAY, start, end, unitId);
					if (rateMetadata != null) {
						saveProcessedDataForDay(rateMetadata);
					}
				} catch (Exception ex) {
					logger.error("获取数据错误！[开始时间：" + format.format(start) + "，结束时间：" + format.format(end) + "，事件ID：" + processor.getEventId() + "]", ex);
				}
			}
		}

	}

	private void saveProcessedDataForDay(RateMetadata rateMetadata) {

		// 根据日期与事件创建唯一ID
		int year = rateMetadata.getYear();
		int month = rateMetadata.getMonth();
		int day = rateMetadata.getDay();
		String eventId = rateMetadata.getEventId();
		String unitId = rateMetadata.getUnitValue();

		StringBuilder sb = new StringBuilder(eventId);
		sb.append('_').append(unitId).append('_');
		sb.append(year);
		if (month < 10) {
			sb.append('0');
		}
		sb.append(month);
		if (day < 10) {
			sb.append('0');
		}
		sb.append(day);

		String id = sb.toString();

		DataProcessedDay model = new DataProcessedDay();
		model.setId(id);
		model.setEventId(eventId);
		model.setDay(day);
		model.setMonth(month);
		model.setYear(year);
		model.setWeekMonth(rateMetadata.getWeekMonth());
		model.setWeekYear(rateMetadata.getWeekYear());
		
		int serialNumber = year * 10000 + month * 100 + day;
		model.setSerialNumber(serialNumber);
		
		long totalNum = rateMetadata.getTotalNum();
		long eventNum = rateMetadata.getEventNum();

		model.setUnitId(rateMetadata.getUnitValue());
		model.setTotalNum(totalNum);
		model.setEventNum(eventNum);
		model.setRate(getRate(totalNum, eventNum));

		try {
			if (!dataProcessedDayService.updateOrSave(model)) {
				logger.error("持久化日粒度数据错误！[ID：" + id + "，事件ID：" + eventId + "]");
			}
		} catch (Exception e) {
			logger.error("持久化日粒度数据错误！[ID：" + id + "，事件ID：" + eventId + "]", e);
		}

	}

	
	private int getRate(long totalNum, long eventNum) {
		if (totalNum == 0 || eventNum == 0) {
			return 0;
		}
		long r = eventNum * 100000 / totalNum;
		if (r < 5) {
			return 0;
		}
		r += 5;
		return (int) (r / 10);
	}

}
