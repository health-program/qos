package com.paladin.qos.mapper.data;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.service.analysis.data.DataPointMonth;
import com.paladin.qos.service.data.vo.DataUnitVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataUnitMapper extends CustomMapper<DataUnit>{


    DataPointMonth getBedReportByQuery(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("startYear") int startYear,
                                       @Param("startMonth") int startMonth,@Param("endYear") int endYear,@Param("endMonth") int endMonth);

    List<DataUnitVO> selectData(@Param("array")String[] array,@Param("typeList")List<Integer> typeList);

    List<DataUnit> byDataUnit(@Param("array")String[] array);
}