package com.paladin.qos.mapper.infectionAndComplication;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.infectionAndComplication.OperationComplication;
import com.paladin.qos.service.infectionAndComplication.dto.OperationComplicationDTO;
import com.paladin.qos.service.infectionAndComplication.dto.OperationComplicationQueryDTO;
import com.paladin.qos.service.infectionAndComplication.vo.OperationComplicationVO;



import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;


public interface OperationComplicationMapper extends CustomMapper<OperationComplication>{
 	public List<OperationComplicationVO> infectIndicationAll(OperationComplicationQueryDTO query);

	public OperationComplicationVO queryById(String id);

	public int updates(OperationComplicationDTO dto);

	public int deletes(String id);

	public int insertInto(OperationComplicationVO vo);


	public OperationComplicationVO findRecentlyRecord(@Param("unitId") String uintId);
}









 


 



