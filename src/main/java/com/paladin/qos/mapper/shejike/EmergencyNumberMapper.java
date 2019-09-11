package com.paladin.qos.mapper.shejike;

import java.util.Date;

public interface EmergencyNumberMapper {

	long getTotalNum(Date startTime, Date endTime, String unitId);

}
