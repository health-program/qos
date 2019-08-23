package com.paladin.qos.mapper.infectioncomplication;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.infectioncomplication.OperationComplication;
import com.paladin.qos.service.infectioncomplication.dto.OperationComplicationDTO;
import com.paladin.qos.service.infectioncomplication.dto.OperationComplicationQueryDTO;
import com.paladin.qos.service.infectioncomplication.vo.OperationComplicationVO;



import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperationComplicationMapper extends CustomMapper<OperationComplication>{
 	public List<OperationComplicationVO> infectIndicationAll(OperationComplicationQueryDTO query);

	public OperationComplicationVO queryById(String id);

	public int updates(OperationComplicationDTO dto);

	public int deletes(String id);

	public int insertInto(OperationComplicationVO vo);


	public OperationComplicationVO findRecentlyRecord(@Param("unitId") String uintId);
}









 


 



