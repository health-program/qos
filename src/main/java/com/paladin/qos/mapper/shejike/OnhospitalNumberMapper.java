package com.paladin.qos.mapper.shejike;

import java.util.Date;

public interface OnhospitalNumberMapper {

	long getTotalNum(Date startTime, Date endTime, String unitId);

}
