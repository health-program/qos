package com.paladin.qos.dynamic.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HospitalIssuesMapper {

	 List<Map<String, Object>> findZYBRYZB(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findBCJL(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findCYJL(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findFYCB(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findFYZB(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findHCSY(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findGH(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findHCCK(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findHCRK(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findHZXX(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findJCBG(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findJCSQ(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findJYBG(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findJYMX(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findJYSQ(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findJZJL(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findZYSF(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findRYCY(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findRYJL(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findSSCZ(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findSXJL(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findSXSQ(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findXYCFCB(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findXYCFZB(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findXYBA(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findXYBACB(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findYPCK(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findYPRK(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findZLCZ(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findZYCFCB(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findZYCFZB(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findZYBA(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	 List<Map<String, Object>> findZYBASS(@Param("startTime") Date startTime, @Param("endTime") Date endTime);



}
