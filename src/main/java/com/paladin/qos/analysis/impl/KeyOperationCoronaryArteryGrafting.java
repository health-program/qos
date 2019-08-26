package com.paladin.qos.analysis.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.KeyOperationCoronaryArteryGraftingMapper;

/**
 * 冠状动脉旁路移植术死亡率
 * 
 * @author FM
 *
 */
@Component
public class KeyOperationCoronaryArteryGrafting extends DataProcessor {
	@Autowired
	private KeyOperationCoronaryArteryGraftingMapper keyOperationCoronaryArteryGraftingMapper;

	public static final String EVENT_ID = "12100";

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
		return keyOperationCoronaryArteryGraftingMapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		return keyOperationCoronaryArteryGraftingMapper.getEventNum(params);
	}
}
