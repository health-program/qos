package com.paladin.qos.analysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataConstantContainer.Event;
import com.paladin.qos.analysis.DataConstantContainer.Unit;
import com.paladin.qos.model.data.DataProcessException;
import com.paladin.qos.model.data.DataProcessedDay;
import com.paladin.qos.service.data.DataProcessExceptionService;
import com.paladin.qos.service.data.DataProcessedDayService;

@Component
public class DataProcessManager {

	private static Logger logger = LoggerFactory.getLogger(DataProcessManager.class);

	@Autowired
	private DataProcessContainer dataProcessContainer;

	@Autowired
	private DataProcessedDayService dataProcessedDayService;

	@Autowired
	private DataProcessExceptionService dataProcessExceptionService;

	/**
	 * 处理计划，处理前一天的数据
	 */
	public void processSchedule() {
		Date yesterday = new Date(System.currentTimeMillis() - TimeUtil.MILLIS_IN_DAY);
		Date start = TimeUtil.toDayTime(yesterday);
		Date end = new Date(start.getTime() + TimeUtil.MILLIS_IN_DAY);

		List<Event> events = DataConstantContainer.getEventList();
		List<Unit> units = DataConstantContainer.getUnitList();

		for (Event event : events) {
			String eventId = event.getId();
			DataProcessor dataProcessor = dataProcessContainer.getDataProcessor(eventId);

			if (dataProcessor == null) {
				logger.error("处理数据失败！未找到事件[" + eventId + ":" + event.getName() + "]对应的数据处理器");
			} else {
				for (Unit unit : units) {
					String unitId = unit.getId();
					processDataForOneDay(start, end, unitId, dataProcessor);
				}
			}
		}
	}

	/**
	 * 处理时间段内数据
	 * 
	 * @param startTime
	 * @param endTime
	 * @param unitIds
	 * @param eventId
	 */
	public void processData(Date startTime, Date endTime, List<String> unitIds, List<String> eventIds) {

		if (startTime == null || endTime == null || unitIds == null || unitIds.size() == 0 || eventIds == null || eventIds.size() == 0) {
			return;
		}

		// 检查医院与事件ID正确性
		List<String> checkedUnitIds = new ArrayList<>(unitIds.size());
		List<String> checkedEventIds = new ArrayList<>(eventIds.size());

		for (String unitId : unitIds) {
			if (DataConstantContainer.getUnit(unitId) != null) {
				checkedUnitIds.add(unitId);
			}
		}

		for (String eventId : eventIds) {
			if (DataConstantContainer.getEvent(eventId) != null) {
				checkedEventIds.add(eventId);
			}
		}
		
		if(checkedUnitIds.size() == 0 || checkedEventIds.size() == 0) {
			return;
		}

		startTime = TimeUtil.toDayTime(startTime);
		endTime = TimeUtil.toDayTime(endTime);

		long startMillis = startTime.getTime();
		long endMillis = endTime.getTime();

		if (startMillis > endMillis) {
			return;
		}

		while (startMillis <= endMillis) {
			Date start = new Date(startMillis);
			startMillis += TimeUtil.MILLIS_IN_DAY;
			Date end = new Date(startMillis);

			for (String eventId : checkedEventIds) {
				DataProcessor processor = dataProcessContainer.getDataProcessor(eventId);
				if (processor == null) {
					logger.error("处理数据失败！未找到事件[" + eventId + "]对应的数据处理器");
				} else {
					for (String unitId : checkedUnitIds) {
						processDataForOneDay(start, end, unitId, processor);
					}
				}
			}
		}
	}

	// 处理一天的数据
	private void processDataForOneDay(Date start, Date end, String unitId, DataProcessor processor) {
		try {
			RateMetadata rateMetadata = processor.processByDay(DataProcessor.TIME_GRANULARITY_DAY, start, end, unitId);
			if (rateMetadata != null) {
				saveProcessedDataForDay(rateMetadata);
			}
		} catch (Exception ex) {
			logger.error("处理数据失败！日期：" + start + "，事件：" + processor.getEventId() + "，医院：" + unitId, ex);
			DataProcessException exception = new DataProcessException();
			exception.setCreateTime(new Date());
			exception.setEventId(processor.getEventId());
			exception.setException(ex.getMessage());
			exception.setProcessDay(start);
			exception.setUnitId(unitId);
			dataProcessExceptionService.save(exception);
		}
	}

	// 保存按天处理的数据
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

	// 获取概率
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
