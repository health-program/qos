package com.paladin.qos.mapper.goal;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.goal.HospitalMonthGoal;
import com.paladin.qos.service.goal.dto.HospitalAnnualGoalQuery;
import com.paladin.qos.service.goal.dto.HospitalGoalAnalysisQuery;
import com.paladin.qos.service.goal.dto.HospitalMonthGoalQuery;
import com.paladin.qos.service.goal.vo.HospitalMonthGoalVO;

import java.util.List;
import java.util.Map;

public interface HospitalMonthGoalMapper extends CustomMapper<HospitalMonthGoal> {


    List<HospitalMonthGoalVO> findList(HospitalMonthGoalQuery query);

    int deleteById(Map<String, Object> params);

    List<HospitalMonthGoalVO> findGoalListByCondition(HospitalGoalAnalysisQuery query);
}
