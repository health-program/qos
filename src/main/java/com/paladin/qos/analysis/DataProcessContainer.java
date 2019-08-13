package com.paladin.qos.analysis;

import java.util.HashMap;
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
			DataProcessor preprocessor = entry.getValue();

			String preprocessorId = preprocessor.getProcessorId();

			if (processorMap.containsKey(preprocessorId)) {
				logger.warn("===>已经存在数据预处理器[ID:" + preprocessorId + "]，该处理器会被忽略");
				continue;
			}

			processorMap.put(preprocessorId, preprocessor);
		}
		
		this.processorMap = processorMap;

		return true;
	}
	
	public DataProcessor getDataPreprocessor(String preprocessorId) {
		return processorMap.get(preprocessorId);
	}
	
	 
	
}
