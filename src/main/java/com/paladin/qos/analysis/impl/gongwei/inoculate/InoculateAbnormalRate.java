package com.paladin.qos.analysis.impl.gongwei.inoculate;

import java.util.Date;

import com.paladin.qos.dynamic.mapper.familydoctor.DataFamilyDoctorMapper;
import com.paladin.qos.dynamic.mapper.gongwei.PublicHealthManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.impl.gongwei.GongWeiDataProcessor;

/**
 * 糖尿病患者和老年人可疑症状转诊率
 * 
 * @author wcw
 *
 */
@Component
public class InoculateAbnormalRate extends GongWeiDataProcessor {
	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public static final String EVENT_ID = "22010";

	@Override
	public String getEventId() {
		return EVENT_ID;
	}

	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {	
		sqlSessionContainer.getSqlSessionTemplate().getMapper(PublicHealthManagementMapper.class);		
		return 0;
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		return 0;
	}
}
