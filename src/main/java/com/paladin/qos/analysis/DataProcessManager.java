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
import com.paladin.qos.model.data.DataEvent;
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
		List<Event> events = DataConstantContainer.getEventList();

		for (Event event : events) {
			String eventId = event.getId();
			int targetType = event.getTargetType();

			DataProcessor dataProcessor = dataProcessContainer.getDataProcessor(eventId);

			if (dataProcessor == null) {
				logger.error("处理数据失败！未找到事件[" + eventId + ":" + event.getName() + "]对应的数据处理器");
			} else {

				List<Unit> units = null;

				if (targetType == DataEvent.TARGET_TYPE_ALL) {
					units = DataConstantContainer.getUnitList();
				} else if (targetType == DataEvent.TARGET_TYPE_HOSPITAL) {
					units = DataConstantContainer.getHospitalList();
				} else if (targetType == DataEvent.TARGET_TYPE_COMMUNITY) {
					units = DataConstantContainer.getCommunityList();
				} else {
					logger.error("处理数据失败！事件[" + eventId + ":" + event.getName() + "]找不到对应的数据范围类型[targetType:" + targetType + "]");
					continue;
				}

				Date start = dataProcessor.getScheduleDate();
				Date end = new Date(start.getTime() + TimeUtil.MILLIS_IN_DAY);

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
	public Processor processData(Date startTime, Date endTime, List<String> unitIds, List<String> eventIds) {
		Processor processor = new Processor();
		processor.process(startTime, endTime, unitIds, eventIds);
		return processor;
	}

	// 处理一天的数据
	private void processDataForOneDay(Date start, Date end, String unitId, DataProcessor processor) {
		try {
			RateMetadata rateMetadata = processor.processByDay(start, end, unitId);
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

		Unit unit = DataConstantContainer.getUnit(unitId);

		model.setUnitId(unitId);
		model.setUnitType(unit.getType());

		model.setTotalNum(totalNum);
		model.setEventNum(eventNum);
		model.setRate(getRate(totalNum, eventNum));

		if (!dataProcessedDayService.updateOrSave(model)) {
			throw new RuntimeException("持久化日粒度数据失败！");
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

	/**
	 * 用于记录处理过程
	 * 
	 * @author TontoZhou
	 * @since 2019年9月10日
	 */
	public class Processor {

		private int current = 0;
		private int total = 0;
		private boolean finished = false;

		private void process(Date startTime, Date endTime, List<String> unitIds, List<String> eventIds) {

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

			if (checkedUnitIds.size() == 0 || checkedEventIds.size() == 0) {
				return;
			}

			startTime = TimeUtil.toDayTime(startTime);
			endTime = TimeUtil.toDayTime(endTime);

			long startMillis = startTime.getTime();
			long endMillis = endTime.getTime();

			if (startMillis > endMillis) {
				return;
			}

			total = (int) ((endMillis - startMillis) / TimeUtil.MILLIS_IN_DAY + 1);
			total = total * checkedEventIds.size() * checkedUnitIds.size();

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
							current++;
						}
					}
				}
			}

			finished = true;
		}

		public int getCurrent() {
			return current;
		}

		public int getTotal() {
			return total;
		}

		public boolean isFinished() {
			return finished;
		}
	}

	// ---------------------------------------------------------------------
	//
	// 由于处理数据可能时间较长，所以使用线程处理，并轮休查询进度
	//
	// ---------------------------------------------------------------------

	private ProcessThread processThread;

	public synchronized boolean processDataByThread(Date startTime, Date endTime, List<String> unitIds, List<String> eventIds) {
		if (processThread != null && processThread.isAlive()) {
			return false;
		} else {
			processThread = new ProcessThread(startTime, endTime, unitIds, eventIds);
			processThread.start();
			return true;
		}
	}

	public ProcessStatus getProcessDataStatus() {
		if (processThread.processor == null) {
			return new ProcessStatus(0, 0, ProcessStatus.STATUS_NON);
		} else {
			Processor p = processThread.processor;
			return new ProcessStatus(p.total, p.current, processThread.finished ? ProcessStatus.STATUS_PROCESSED : ProcessStatus.STATUS_PROCESSING);
		}
	}

	public static class ProcessStatus {

		public final static int STATUS_NON = -1;
		public final static int STATUS_PROCESSING = 1;
		public final static int STATUS_PROCESSED = 2;

		private int total;
		private int current;
		private int status;

		public ProcessStatus(int total, int current, int status) {
			this.total = total;
			this.current = current;
			this.status = status;
		}

		public int getTotal() {
			return total;
		}

		public int getCurrent() {
			return current;
		}

		public int getStatus() {
			return status;
		}

	}

	private class ProcessThread extends Thread {

		private Date startTime;
		private Date endTime;
		private List<String> unitIds;
		private List<String> eventIds;

		private Processor processor;
		private boolean finished;

		private ProcessThread(Date startTime, Date endTime, List<String> unitIds, List<String> eventIds) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.unitIds = unitIds;
			this.eventIds = eventIds;
			this.finished = false;
			this.processor = new Processor();
		}

		public void run() {
			try {
				processor.process(startTime, endTime, unitIds, eventIds);
			} finally {
				finished = true;
			}
		}
	}

}
