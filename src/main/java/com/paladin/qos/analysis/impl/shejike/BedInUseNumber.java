package com.paladin.qos.analysis.impl.shejike;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.mapper.shejike.SheJiKeMapper;

/**
 * 使用床位
 * @author FM
 *
 */
@Component
public class BedInUseNumber extends DataProcessor{
	@Autowired
    private SqlSessionContainer sqlSessionContainer;

    public static final String EVENT_ID = "14005";

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
        sqlSessionContainer.setCurrentDataSource(DSConstant.DS_JCYL);
        long a = getMapper().getBedInUseNumber(startTime, endTime, unitId);
        System.out.println("startTime"+startTime);
        System.out.println("endTime"+endTime);
        System.out.println("unitId"+unitId);
        System.out.println("--------"+a);
        return  a;
    }

    @Override
    public long getEventNum(Date startTime, Date endTime, String unitId) {
        return 0;
    }
}
