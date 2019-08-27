package com.paladin.qos.mapper.data;

import com.paladin.qos.model.data.DataUnit;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.service.data.vo.BedReportVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface DataUnitMapper extends CustomMapper<DataUnit>{

    List<BedReportVO> getBedReportByQuery(@Param("unitId") String unitId, @Param("month") String month,@Param("year") String year);
}