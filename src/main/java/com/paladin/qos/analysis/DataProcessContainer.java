package com.paladin.qos.analysis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.paladin.framework.spring.SpringBeanHelper;
import com.paladin.framework.spring.SpringContainer;

@Component
public class DataProcessContainer implements SpringContainer {

	private static Logger logger = LoggerFactory.getLogger(DataProcessContainer.class);

	private Map<String, DataProcessor> processorMap = new HashMap<>();

	@Override
	public boolean initialize() {

		Map<String, DataProcessor> processorSpringMap = SpringBeanHelper.getBeansByType(DataProcessor.class);
		Map<String, DataProcessor> processorMap = new HashMap<>();

		for (Entry<String, DataProcessor> entry : processorSpringMap.entrySet()) {
			DataProcessor processor = entry.getValue();

			String eventId = processor.getEventId();

			if (processorMap.containsKey(eventId)) {
				logger.warn("===>已经存在数据预处理器[EVENT_ID:" + eventId + "]，该处理器会被忽略");
				continue;
			}

			processorMap.put(eventId, processor);
		}

		this.processorMap = processorMap;

		return true;
	}

	public DataProcessor getDataProcessor(String preprocessorId) {
		return processorMap.get(preprocessorId);
	}

	/**
	 * 处理时间段内数据
	 * 
	 * @param startTime
	 * @param endTime
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
					RateMetadata rateMetadata = processor.processByDay(RateMetadata.TIME_TYPE_DAY, start, end, unitId);
					if (rateMetadata != null) {
						saveProcessedData(rateMetadata);
					}
				} catch (Exception ex) {
					logger.error("获取数据错误！[开始时间：" + format.format(start) + "，结束时间：" + format.format(end) + "，事件ID：" + eventId + "]", ex);
				}
			}
		}

	}

	private void saveProcessedData(RateMetadata rateMetadata) {
		
	}

	// private

}
