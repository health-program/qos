package com.paladin.qos.mapper.gongwei;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.qos.model.gongwei.Physical;

public interface PhysicalManagementMapper {

	// 档案总数
	List<Physical> getTotalPhysical(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
