package com.paladin.qos.mapper.analysis;

import java.util.Map;

public interface NewBabyWeightMoreThan1800Mapper {

	long getTotalNum(Map<String, Object> params);

	long getEventNum(Map<String, Object> params);

}
