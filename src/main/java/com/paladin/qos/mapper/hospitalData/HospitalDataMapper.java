package com.paladin.qos.mapper.hospitalData;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.paladin.qos.model.hospitalData.HospitalDataCheck;
import com.paladin.qos.model.hospitalData.HospitalDataLog;

@Mapper
public interface HospitalDataMapper {

	List<HospitalDataLog> findHospitalDataLog(HospitalDataLog query);

	List<HospitalDataCheck> findHospitalDataCheck(HospitalDataCheck query);

	List<String> selectCheckType();

}
