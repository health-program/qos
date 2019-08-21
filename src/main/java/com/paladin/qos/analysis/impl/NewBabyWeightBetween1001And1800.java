package com.paladin.qos.analysis.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.NewBabyWeightBetween1001And1800Mapper;
import com.paladin.qos.mapper.analysis.NewBabyWeightBetween751And1000Mapper;
import com.paladin.qos.mapper.analysis.NewBabyWeightLessThan750Mapper;

/**
 * 新生儿患者出生体重751-1000克死亡率
 * @author FM
 *
 */
@Component
public class NewBabyWeightBetween1001And1800 extends DataProcessor{
	@Autowired
	private NewBabyWeightBetween1001And1800Mapper newBabyWeightBetween1001And1800Mapper;
	
    public static final String EVENT_ID = "10115";
	
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
		return newBabyWeightBetween1001And1800Mapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {

		Map<String, Object> params = new HashMap<String, Object>();
		String aa  = format.format(startTime);
		params.put("startTime", format.format(startTime));
		params.put("endTime", format.format(endTime));
		params.put("unitId", unitId);
		return newBabyWeightBetween1001And1800Mapper.getEventNum(params);
	}
}
