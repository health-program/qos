package com.paladin.qos.mapper.data;

import com.paladin.qos.model.data.DataUnit;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.service.analysis.data.AnalysisMonth;
import com.paladin.qos.service.data.vo.BedReportVO;
import com.paladin.qos.service.data.vo.DataUnitVO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataUnitMapper extends CustomMapper<DataUnit>{


    AnalysisMonth getBedReportByQuery(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start,
                                                      @Param("end") int end);

    List<DataUnitVO> selectData(String[] array);
}