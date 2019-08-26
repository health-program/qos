package com.paladin.qos.analysis.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.KeyOperationCesareanSectionMapper;
import com.paladin.qos.mapper.analysis.KeyOperationCoronaryArteryGraftingMapper;

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
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public String getEventId() {

		return EVENT_ID;
	}

	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {

		Map<String, Object> params = new HashMap<String, Object>();
		String aa  = format.format(startTime);
		params.put("startTime", format.format(startTime));
		params.put("endTime", format.format(endTime));
		params.put("unitId", unitId);
		return keyOperationCesareanSectionMapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", format.format(startTime));
		params.put("endTime", format.format(endTime));
		params.put("unitId", unitId);
		return keyOperationCesareanSectionMapper.getEventNum(params);
	}
}
