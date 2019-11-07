package com.paladin.qos.dynamic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.paladin.qos.model.hospitalData.HospitalDataCheck;
import com.paladin.qos.model.hospitalData.HospitalDataLog;

@Mapper
public interface HospitalLogMapper {

	List<HospitalDataLog> findHospitalDataLog(HospitalDataLog query);

	List<HospitalDataCheck> findHospitalDataCheck(HospitalDataCheck query);

}
