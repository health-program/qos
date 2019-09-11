package com.paladin.qos.analysis.impl.fuyou.jhsy;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.impl.fuyou.FuyouDataProcessor;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.dynamic.mapper.exhibition.FamilyPlanningManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * <宫内节育器放置>
 *
 * @author Huangguochen
 * @create 2019/9/10 18:34
 */
public class IntrauterineDevicePlacement extends FuyouDataProcessor {
    @Autowired
    private SqlSessionContainer sqlSessionContainer;

    public static final String EVENT_ID = "13105";

    private FamilyPlanningManagementMapper mapper;

    public FamilyPlanningManagementMapper getMapper() {
        if (mapper == null) {
            mapper = sqlSessionContainer.getMapper(FamilyPlanningManagementMapper.class);
        }
        return mapper;
    }

    @Override
    public String getEventId() {
        return EVENT_ID;
    }

    @Override
    public long getTotalNum(Date startTime, Date endTime, String unitId) {
        sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);
        return  getMapper() .getIntrauterineDevicePlacementNumber(startTime, endTime, unitId);
    }

    @Override
    public long getEventNum(Date startTime, Date endTime, String unitId) {
        return 0;
    }

}
