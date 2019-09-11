package com.paladin.qos.analysis.impl.fuyou.etbj;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.impl.fuyou.FuyouDataProcessor;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.dynamic.mapper.exhibition.ChildCareManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * <女性新生儿分娩数>
 *
 * @author Huangguochen
 * @create 2019/9/10 19:38
 */
public class FemaleNewbornChildbirth extends FuyouDataProcessor {
    @Autowired
    private SqlSessionContainer sqlSessionContainer;

    public static final String EVENT_ID = "13202";

    private ChildCareManagementMapper mapper;

    public ChildCareManagementMapper getMapper() {
        if (mapper == null) {
            mapper = sqlSessionContainer.getMapper(ChildCareManagementMapper.class);
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
        return  getMapper().getFemaleNewbornChildbirthNumber(startTime, endTime, unitId);
    }

    @Override
    public long getEventNum(Date startTime, Date endTime, String unitId) {
        return 0;
    }
}
