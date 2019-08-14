package com.paladin.qos.analysis.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.MortalityAnalysisMapper;

/**
 * 整体住院死亡率数据处理
 * @author TontoZhou
 * @since 2019年8月14日
 */
@Component
public class MortalityHospitalizedDataProcessor extends DataProcessor {

	public static final String EVENT_ID = "10100";
		
	@Autowired
	private MortalityAnalysisMapper mortalityAnalysisMapper;
	
	@Override
	public String getEventId() {
		return EVENT_ID;
	}

	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {
		return mortalityAnalysisMapper.getNumberOutHospital(startTime, endTime, unitId);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		return mortalityAnalysisMapper.getFatalitiesInHospital(startTime, endTime, unitId);
	}

}
