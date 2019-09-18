package com.paladin.qos.analysis.impl.yiyuan.opd;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.impl.yiyuan.YiyuanDataProcessor;
import com.paladin.qos.dynamic.mapper.yiyuan.opd.OpdStatisticsMapper;

/**住院人次   
 * @author MyKite
 * @version 2019年9月17日 上午10:41:06 
 */
@Component
public class HospitalizationNumber extends YiyuanDataProcessor{
    
	private OpdStatisticsMapper mapper;

	@Autowired
	private SqlSessionContainer sqlSessionContainer;
	
	public OpdStatisticsMapper getMapper() {
		if (mapper == null) {
			mapper = sqlSessionContainer.getMapper(OpdStatisticsMapper.class);
		}
		return mapper;
	}
	
	public static final String EVENT_ID = "31004";

	@Override
	public String getEventId() {
		return EVENT_ID;
	}

	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {
		sqlSessionContainer.setCurrentDataSource(getDataSourceByUnit(unitId));
		return getMapper().HospitalizationNumber(startTime, endTime, unitId);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		return 0;
	}
}
