package com.paladin.common.mapper.syst;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.model.syst.SysSwitch;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.service.report.dto.ReportDataQueryDTO;

public interface SysSwitchMapper extends CustomMapper<SysSwitch>{

	public List<SysSwitch> findAll(@Param("query")SysSwitch query);

	public SysSwitch getLastOne();

	public int save(SysSwitch model);


}