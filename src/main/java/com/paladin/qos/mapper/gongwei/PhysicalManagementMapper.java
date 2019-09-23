package com.paladin.qos.mapper.gongwei;

import java.util.Date;
import java.util.List;

import com.paladin.qos.service.gongwei.vo.PhysicalManagementVO;
import org.apache.ibatis.annotations.Param;

import com.paladin.qos.model.gongwei.Physical;
import org.springframework.stereotype.Service;

@Service
public interface PhysicalManagementMapper {

	// 档案总数
	PhysicalManagementVO getTotalPhysical(@Param("eventId") String eventId, @Param("unitId") String unitId);

}
