package com.paladin.qos.analysis.impl.gongwei.pressureSugar;


import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.impl.gongwei.GongWeiDataProcessor;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.dynamic.mapper.exhibition.PublicHealthManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.Date;

/**
 * 管理人群血压控制率
 * @author wcw
 *
 */
@Component
public class PressureControlRate extends GongWeiDataProcessor{
    @Autowired
    private SqlSessionContainer sqlSessionContainer;

    public static final String EVENT_ID = "22004";

    private PublicHealthManagementMapper mapper;

    public PublicHealthManagementMapper getMapper() {
        if (mapper == null) {
            mapper = sqlSessionContainer.getMapper(PublicHealthManagementMapper.class);
        }
        return mapper;
    }

    @Override
    public String getEventId() {
        return EVENT_ID;
    }

    @Override
    public long getTotalNum(Date startTime, Date endTime, String unitId) {
        String gongWeiUnitId=getMappingUnitId(unitId);
        if (StringUtils.isEmptyOrWhitespace(gongWeiUnitId)){
            return 0;
        }
        sqlSessionContainer.setCurrentDataSource(DSConstant.DS_GONGWEI);
        return  getMapper().getPressureNumber(startTime, endTime, gongWeiUnitId);
    }

    @Override
    public long getEventNum(Date startTime, Date endTime, String unitId) {
        String gongWeiUnitId=getMappingUnitId(unitId);
        if (StringUtils.isEmptyOrWhitespace(gongWeiUnitId)){
            return 0;
        }
        sqlSessionContainer.setCurrentDataSource(DSConstant.DS_GONGWEI);
        return  getMapper().getRecentPressureReachNumber(startTime, endTime, gongWeiUnitId);
    }
}
