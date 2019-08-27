package com.paladin.qos.service.data;

import com.paladin.qos.mapper.data.DataUnitMapper;
import com.paladin.qos.service.data.vo.BedReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.qos.model.data.DataUnit;
import com.paladin.framework.core.ServiceSupport;

import java.util.List;

@Service
public class DataUnitService extends ServiceSupport<DataUnit> {

    @Autowired
    private DataUnitMapper dataUnitMapper;

    public List<BedReportVO> getBedReportByQuery(String unitId, String month,String year) {
        return dataUnitMapper.getBedReportByQuery(unitId,month,year);
    }
}