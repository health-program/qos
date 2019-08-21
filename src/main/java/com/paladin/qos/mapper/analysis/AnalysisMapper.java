package com.paladin.qos.mapper.analysis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.qos.service.analysis.data.AnalysisMonth;
import com.paladin.qos.service.analysis.data.AnalysisUnit;
import com.paladin.qos.service.analysis.data.DataPointDay;
import com.paladin.qos.service.analysis.data.DataPointMonth;
import com.paladin.qos.service.analysis.data.DataPointWeekMonth;
import com.paladin.qos.service.analysis.data.DataPointWeekYear;
import com.paladin.qos.service.analysis.data.DataPointYear;

public interface AnalysisMapper {

	List<DataPointDay> getDataPointOfDay(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start, @Param("end") int end);

	List<DataPointMonth> getDataPointOfMonth(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start,
			@Param("end") int end);

	List<DataPointYear> getDataPointOfYear(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start, @Param("end") int end);

	List<DataPointWeekYear> getDataPointOfWeekYear(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start,
			@Param("end") int end);

	List<DataPointWeekMonth> getDataPointOfWeekMonth(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start,
			@Param("end") int end);

	List<AnalysisUnit> getAnalysisResultGroupByUnit(@Param("eventId") String eventId, @Param("start") int start, @Param("end") int end);

	List<AnalysisMonth> getAnalysisResultGroupByMonth(@Param("eventId") String eventId, @Param("start") int start, @Param("end") int end);

	List<AnalysisMonth> getAnalysisResultOfUnitGroupByMonth(@Param("eventId") String eventId, @Param("unitId") String unitId,  @Param("start") int start, @Param("end") int end);

	
}
