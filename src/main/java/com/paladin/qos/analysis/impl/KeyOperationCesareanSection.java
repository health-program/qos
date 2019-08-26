package com.paladin.qos.analysis.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.KeyOperationCesareanSectionMapper;

/**
 * 剖宫产手术死亡率
 * @author FM
 *
 */
@Component
public class KeyOperationCesareanSection extends DataProcessor{
	@Autowired 
	private KeyOperationCesareanSectionMapper keyOperationCesareanSectionMapper;
	
	public static final String EVENT_ID = "12104";
	
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
		return keyOperationCesareanSectionMapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		return keyOperationCesareanSectionMapper.getEventNum(params);
	}
}
