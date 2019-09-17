package com.paladin.qos.analysis.impl.yiyuan;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.dynamic.mapper.yiyuan.KeyOperationCesareanSectionMapper;

/**
 * 剖宫产手术死亡率
 * 
 * @author FM
 *
 */
@Component
public class KeyOperationCesareanSection extends YiyuanDataProcessor {

	private KeyOperationCesareanSectionMapper mapper;

	public static final String EVENT_ID = "12104";
	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public KeyOperationCesareanSectionMapper getMapper() {
		if (mapper == null) {
			mapper = sqlSessionContainer.getMapper(KeyOperationCesareanSectionMapper.class);
		}
		return mapper;
	}

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
