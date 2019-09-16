package com.paladin.qos.analysis.impl.yiyuan;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.impl.yiyuan.YiyuanDataProcessor;
import com.paladin.qos.dynamic.mapper.yiyuan.NewBabyWeightBetween1001And1800Mapper;
import com.paladin.qos.dynamic.mapper.yiyuan.NewBabyWeightBetween751And1000Mapper;

/**
 * 新生儿患者出生体重751-1000克死亡率
 * 
 * @author FM
 *
 */
@Component
public class NewBabyWeightBetween751And1000 extends YiyuanDataProcessor {

	
	private NewBabyWeightBetween751And1000Mapper mapper;
	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public NewBabyWeightBetween751And1000Mapper getMapper() {
		if (mapper == null) {
			mapper = sqlSessionContainer
					.getMapper(NewBabyWeightBetween751And1000Mapper.class);
		}
		return mapper;
	}
	public static final String EVENT_ID = "10114";

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
		sqlSessionContainer.setCurrentDataSource(getDataSourceByUnit(unitId));
		return getMapper().getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		sqlSessionContainer.setCurrentDataSource(getDataSourceByUnit(unitId));
		return getMapper().getEventNum(params);
	}
}
