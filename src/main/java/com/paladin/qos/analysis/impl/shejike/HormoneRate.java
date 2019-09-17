package com.paladin.qos.analysis.impl.shejike;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.mapper.shejike.SheJiKeMapper;

/**
 * 激素使用率
 * @author FM
 *
 */
@Component
public class HormoneRate extends DataProcessor{
	@Autowired
    private SqlSessionContainer sqlSessionContainer;

    public static final String EVENT_ID = "16010";

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
        return getMapper().getTotalHormone(startTime, endTime, unitId);
    }

    @Override
    public long getEventNum(Date startTime, Date endTime, String unitId) {
    	sqlSessionContainer.setCurrentDataSource(DSConstant.DS_GONGWEI);
        return getMapper().getEventHormone(startTime, endTime, unitId);
    }
}