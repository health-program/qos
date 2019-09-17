package com.paladin.qos.analysis.impl.yiyuan;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.dynamic.mapper.yiyuan.MortalityAnalysisMapper;

/**
 * 整体住院死亡率数据处理
 * 
 * @author TontoZhou
 * @since 2019年8月14日
 */
@Component
public class MortalityHospitalizedDataProcessor extends YiyuanDataProcessor {

	public static final String EVENT_ID = "10100";

	private MortalityAnalysisMapper mapper;
	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public MortalityAnalysisMapper getMapper() {
		if (mapper == null) {
			mapper = sqlSessionContainer.getMapper(MortalityAnalysisMapper.class);
		}
		return mapper;
	}

	@Override
	public String getEventId() {
		return EVENT_ID;
	}

	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {
		sqlSessionContainer.setCurrentDataSource(getDataSourceByUnit(unitId));
		return getMapper().getNumberOutHospital(startTime, endTime, unitId);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		sqlSessionContainer.setCurrentDataSource(getDataSourceByUnit(unitId));
		return getMapper().getFatalitiesInHospital(startTime, endTime, unitId);
	}

}
