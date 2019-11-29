package com.paladin.qos.mapper.goal;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.goal.HospitalAnnualGoal;
import com.paladin.qos.service.goal.dto.HospitalAnnualGoalQuery;
import com.paladin.qos.service.goal.dto.HospitalGoalAnalysisQuery;
import com.paladin.qos.service.goal.vo.HospitalAnnualGoalVO;

import java.util.List;
import java.util.Map;

public interface HospitalAnnualGoalMapper extends CustomMapper<HospitalAnnualGoal> {

    List<HospitalAnnualGoalVO> findList(HospitalAnnualGoalQuery query);

    int deleteById(Map<String, Object> params);

    List<HospitalAnnualGoalVO> findGoalListByCondition(HospitalGoalAnalysisQuery query);

    HospitalAnnualGoal validDuplicatedRecord(HospitalAnnualGoal example);
}
