package com.paladin.qos.analysis.impl.fuyou.ycf;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.impl.fuyou.FuyouDataProcessor;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.dynamic.mapper.exhibition.MaternalManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <孕产妇死亡人数>
 *
 * @author Huangguochen
 * @create 2019/9/11 10:55
 */
@Component
public class MaternalDeath extends FuyouDataProcessor {
    @Autowired
    private SqlSessionContainer sqlSessionContainer;

    public static final String EVENT_ID = "13306";

    private MaternalManagementMapper mapper;

    public MaternalManagementMapper getMapper() {
        if (mapper == null) {
            mapper = sqlSessionContainer.getMapper(MaternalManagementMapper.class);
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
        return  getMapper().getMaternalDeathNumber(startTime, endTime, unitId);
    }

    @Override
    public long getEventNum(Date startTime, Date endTime, String unitId) {
        return 0;
    }
}
