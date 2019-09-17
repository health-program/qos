package com.paladin.qos.analysis.impl.gongwei.inoculate;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.impl.gongwei.GongWeiDataProcessor;
import com.paladin.qos.dynamic.mapper.exhibition.PublicHealthManagementMapper;

/**
 * 居民健康档案档案公开率
 * @author wcw
 *
 */
@Component
public class InoculateArchivesRate extends GongWeiDataProcessor{
    @Autowired
    private SqlSessionContainer sqlSessionContainer;

    public static final String EVENT_ID = "22011";

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
       return 0;
    }

    @Override
    public long getEventNum(Date startTime, Date endTime, String unitId) {
       return 0;
    }
}
