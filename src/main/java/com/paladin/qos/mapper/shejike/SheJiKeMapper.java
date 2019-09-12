package com.paladin.qos.mapper.shejike;

import java.util.Date;

public interface SheJiKeMapper {
	//医生平均门急诊量
	long getAverageNumber(Date startTime, Date endTime, String unitId);
	//使用床位数
	long getBedInUseNumber(Date startTime, Date endTime, String unitId);
	//急诊人次数
	long getEmergencyNumber(Date startTime, Date endTime, String unitId);
	//住院人次数
	long getInhospitalNumber(Date startTime, Date endTime, String unitId);
	//在院人次数
	long getOnhospitalNumber(Date startTime, Date endTime, String unitId);
	//出院人次数
	long getOuthospitalNumber(Date startTime, Date endTime, String unitId);
	//门诊人次数
	long getOutpatientNumber(Date startTime, Date endTime, String unitId);
	//门急诊总数
	long getPatientsNumber(Date startTime, Date endTime, String unitId);
	//额定床位数
	long getRatedBedNumber(Date startTime, Date endTime, String unitId);
	//出诊医生数
	long getVisitDoctorNumber(Date startTime, Date endTime, String unitId);
	
	//病床总天数
	long getBedTotalDays(Date startTime, Date endTime, String unitId);
	//病床使用天数
	long getBedInUseDays(Date startTime, Date endTime, String unitId);
}
