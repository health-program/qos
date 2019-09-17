package com.paladin.qos.dynamic.mapper.exhibition;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.qos.dynamic.model.exhibition.Physical;

public interface PhysicalManagementMapper {

	// 档案总数
	List<Physical> getTotalPhysical(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
