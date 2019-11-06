package com.paladin.qos.dynamic.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HospitalIssuesMapper {

	 List<Map<String, Object>> findZYBRYZB(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findBCJL(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findCYJL(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findFYCB(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findFYZB(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findHCSY(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findGH(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findHCCK(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findHCRK(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findHZXX(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findJCBG(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findJCSQ(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findJYBG(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findJYMX(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findJYSQ(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findJZJL(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findZYSF(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findRYCY(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findRYJL(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findSSCZ(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findSXJL(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findSXSQ(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findXYCFCB(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findXYCFZB(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findXYBA(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findXYBACB(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findYPCK(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findYPRK(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findZLCZ(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findZYCFCB(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findZYCFZB(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findZYBA(@Param("startTime") String startTime, @Param("endTime") String endTime);
	 List<Map<String, Object>> findZYBASS(@Param("startTime") String startTime, @Param("endTime") String endTime);



}
