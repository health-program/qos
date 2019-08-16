package com.paladin.qos.mapper.analysis;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RehospitalizationAnalysisMapper {

	long getTotalNum(Map<String, Object> params);

	long getEventNum(Map<String, Object> params);

}
                           