package com.paladin.qos.analysis.impl.fuyou;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.dynamic.mapper.exhibition.FamilyPlanningManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * <药物流产人数>
 *
 * @author Huangguochen
 * @create 2019/9/10 15:29
 */
public class MedicalAbortionTotal extends DataProcessor {

    @Autowired
    private SqlSessionContainer sqlSessionContainer;

    public static final String EVENT_ID = "13101";

    @Override
    public String getEventId() {
        return EVENT_ID;
    }

    @Override
    public long getTotalNum(Date startTime, Date endTime, String unitId) {
        FamilyPlanningManagementMapper mapper = sqlSessionContainer.getMapper(FamilyPlanningManagementMapper.class);
        sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);
        return  mapper.getMedicalAbortionNumber(startTime, endTime, unitId);
    }

    @Override
    public long getEventNum(Date startTime, Date endTime, String unitId) {
        return 0;
    }
}
