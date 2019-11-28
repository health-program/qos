package com.paladin.qos.dynamic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.paladin.qos.model.hospitalData.HospitalDataCheck;
import com.paladin.qos.model.hospitalData.HospitalDataLog;
import com.paladin.qos.model.hospitalData.HospitalDataRule;

@Mapper
public interface HospitalLogMapper {

	List<HospitalDataLog> findHospitalDataLog(HospitalDataLog query);

	List<HospitalDataCheck> findHospitalDataCheck(HospitalDataCheck query);

	List<HospitalDataRule> findRulePage(HospitalDataRule query);

	int close(String id);

	int open(String id);

	int save(HospitalDataRule hospitalDataRule);

	HospitalDataRule getHospitalDataRule(String id);

	int updateHospitalDataRule(HospitalDataRule hospitalDataRule);

}
