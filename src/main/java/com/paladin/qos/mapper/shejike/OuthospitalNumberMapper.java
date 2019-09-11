package com.paladin.qos.mapper.shejike;

import java.util.Date;

public interface OuthospitalNumberMapper {

	long getTotalNum(Date startTime, Date endTime, String unitId);

}
