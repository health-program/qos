package com.paladin.qos.dynamic.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HospitalIssuesMapper {

	public List<Map<String, Object>> findZYBRYZB(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

}
