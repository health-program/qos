package com.paladin.qos.analysis.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.NewBabyMortalityNonOperationDataMapper;

/**
 * 新生儿非手术患者住院死亡率
 * 
 * @author FM
 *
 */
@Component
public class NewBabyMortalityNonOperationDataProcessor extends DataProcessor {
	@Autowired
	private NewBabyMortalityNonOperationDataMapper newBabyMortalityNonOperationDataMapper;

	public static final String EVENT_ID = "10112";

	@Override
	public String getEventId() {

		return EVENT_ID;
	}

	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		return newBabyMortalityNonOperationDataMapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		return newBabyMortalityNonOperationDataMapper.getEventNum(params);
	}
}
