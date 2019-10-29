package com.paladin.qos.mapper.analysis;

import java.util.Date;
import java.util.List;

import com.paladin.qos.model.gongwei.EntityGongwei;
import com.paladin.qos.model.home.Sign;

import org.apache.ibatis.annotations.Param;

import com.paladin.qos.model.register.Register;
import com.paladin.qos.service.analysis.data.AnalysisUnit;
import com.paladin.qos.service.analysis.data.DataCountDay;
import com.paladin.qos.service.analysis.data.DataCountMonth;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.analysis.data.DataCountYear;
import com.paladin.qos.service.analysis.data.DataPointDay;
import com.paladin.qos.service.analysis.data.DataPointMonth;
import com.paladin.qos.service.analysis.data.DataPointWeekMonth;
import com.paladin.qos.service.analysis.data.DataPointWeekYear;
import com.paladin.qos.service.analysis.data.DataPointYear;
import com.paladin.qos.service.analysis.data.DataSigningMonth;

public interface AnalysisMapper {

	// 日节点数据集合
	List<DataPointDay> getDataPointOfDayByUnit(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start,
											   @Param("end") int end);

	List<DataPointDay> getDataPointOfDay(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start, @Param("end") int end,
										 @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	// 月节点数据集合
	List<DataPointMonth> getDataPointOfMonthByUnit(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start,
												   @Param("end") int end);

	List<DataPointMonth> getDataPointOfMonth(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start,
											 @Param("end") int end, @Param("ignoreUnitIds") List<String> ignoreUnitId);

	// 年节点数据集合
	List<DataPointYear> getDataPointOfYearByUnit(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start,
												 @Param("end") int end);

	List<DataPointYear> getDataPointOfYear(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start, @Param("end") int end,
										   @Param("ignoreUnitIds") List<String> ignoreUnitId);

	// 年中星期节点数据集合
	List<DataPointWeekYear> getDataPointOfWeekYearByUnit(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start,
														 @Param("end") int end);

	List<DataPointWeekYear> getDataPointOfWeekYear(@Param("eventId") String eventId, @Param("start") int start, @Param("end") int end,
												   @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	// 月中星期节点数据集合
	List<DataPointWeekMonth> getDataPointOfWeekMonthByUnit(@Param("eventId") String eventId, @Param("unitId") String unitId, @Param("start") int start,
														   @Param("end") int end);

	List<DataPointWeekMonth> getDataPointOfWeekMonth(@Param("eventId") String eventId, @Param("start") int start, @Param("end") int end,
													 @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	// 按单位分组查询总数据
	List<AnalysisUnit> getAnalysisResultGroupByUnit(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start,
													@Param("end") int end, @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<DataCountUnit> countTotalNumByUnit(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start, @Param("end") int end,
											@Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<DataCountUnit> countEventNumByUnit(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start, @Param("end") int end,
											@Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<DataCountDay> countTotalNumByDay(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start, @Param("end") int end,
										  @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<DataCountDay> countEventNumByDay(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start, @Param("end") int end,
										  @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<DataCountMonth> countTotalNumByMonth(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start,
											  @Param("end") int end, @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<DataCountMonth> countEventNumByMonth(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start,
											  @Param("end") int end, @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<DataCountYear> countTotalNumByYear(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start,
											@Param("end") int end, @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<DataCountYear> countEventNumByYear(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("start") int start,
											@Param("end") int end, @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<Integer> getSerialNumbers(@Param("eventId") String eventId, @Param("unitId") String unitId);

	Integer getMaxSerialNumByEventAndUnit(@Param("eventId") String eventId, @Param("unitId") String unitId);

	int removeDataOfDay(@Param("serialNumber") int serialNumber, @Param("eventId") String eventId);

	long getTotalNumOfEvent(@Param("eventId") String eventId, @Param("start") int start, @Param("end") int end, @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<DataCountUnit> getLastCountByUnit(@Param("eventId") String eventId, @Param("unitType") int unitType, @Param("ignoreUnitIds") List<String> ignoreUnitIds);

	List<Register> findRegisterList();

	List<DataCountUnit> getOutPatientNumber();

	List<DataCountUnit> getEmergencyNumber();

	List<DataCountUnit> getTodayNumber();

	List<DataCountUnit> getThisMonthNumber();

	List<DataCountUnit> getHospitalOutPatientNumber();

	List<DataCountUnit> getHospitalEmergencyNumber();

	List<DataCountUnit> getHospitalTodayNumber();

	List<DataCountUnit> getHospitalThisMonthNumber();

	List<DataSigningMonth> populationSigningNum();//人口签约数

	List<Sign> getSignInfo();

	List<DataSigningMonth> getTwoYear();

	List<DataSigningMonth> getArchivesRate();

	Long getArchivesNumber(@Param("month") Date month);

	String getTotalData(@Param("id") String id);

	List<EntityGongwei> getPressureTotalDataFromLocal(String year);

	List<EntityGongwei> getSugarTotalDataFromLocal(String year);

}
