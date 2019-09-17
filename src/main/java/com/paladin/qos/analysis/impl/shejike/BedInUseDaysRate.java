package com.paladin.qos.analysis.impl.shejike;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.mapper.shejike.SheJiKeMapper;

/**
 * 病床使用率（病床使用天数/总天数）
 * 
 * @author FM
 *
 */
@Component
public class BedInUseDaysRate extends DataProcessor {

	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public static final String EVENT_ID = "14006";

	private SheJiKeMapper mapper;

	public SheJiKeMapper getMapper() {
		if (mapper == null) {
			mapper = sqlSessionContainer.getMapper(SheJiKeMapper.class);
		}
		return mapper;
	}

	@Override
	public String getEventId() {
		return EVENT_ID;
	}

	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_GONGWEI);
		long totalBed = getMapper().getBedTotalDays(startTime, endTime, unitId);// 获取总床位数
		// 时间相减（天数）
		long days = TimeUtil.getIntervalDays(startTime.getTime(), endTime.getTime());
		long total = totalBed * days;
		return total;
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_GONGWEI);
		return getMapper().getBedInUseDays(startTime, endTime, unitId);
	}
}
