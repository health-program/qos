package com.paladin.qos.mapper.gongwei;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.paladin.qos.service.gongwei.vo.PhysicalManagementVO;

@Service
public interface PhysicalManagementMapper {

	// 档案总数
	PhysicalManagementVO getTotalPhysical(@Param("eventId") String eventId, @Param("unitId") String unitId);

}
