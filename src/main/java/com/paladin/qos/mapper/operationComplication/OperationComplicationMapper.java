package com.paladin.qos.mapper.operationComplication;
import java.util.List;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.operationComplication.OperationComplication;
import com.paladin.qos.service.operationComplication.dto.OperationComplicationDTO;
import com.paladin.qos.service.operationComplication.dto.OperationComplicationQueryDTO;
import com.paladin.qos.service.operationComplication.vo.OperationComplicationVO;



public interface OperationComplicationMapper extends CustomMapper<OperationComplication>{
 	public List<OperationComplicationVO> infectIndicationAll(OperationComplicationQueryDTO query);

	public OperationComplicationVO queryById(String id);

	public int updates(OperationComplicationVO vo);

	public int deletes(String id);

	public int insertInto(OperationComplicationVO vo);


	public OperationComplicationVO findRecentlyRecord();
}









 


 



