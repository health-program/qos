package com.paladin.qos.analysis.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.NewBabyMortalityHospitalizedMapper;

/**
 * 新生儿住院住院总死亡率统计
 * @author FM
 *
 */
@Component
public class NewBabyMortalityHospitalizedDataProcessor extends DataProcessor{

	@Autowired
	private NewBabyMortalityHospitalizedMapper newBabyMortalityHospitalizedMapper;
	
    public static final String EVENT_ID = "10110";
	
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
		return newBabyMortalityHospitalizedMapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		String aa  = format.format(startTime);
		params.put("startTime", format.format(startTime));
		params.put("endTime", format.format(endTime));
		params.put("unitId", unitId);
		return newBabyMortalityHospitalizedMapper.getEventNum(params);
	}

}
