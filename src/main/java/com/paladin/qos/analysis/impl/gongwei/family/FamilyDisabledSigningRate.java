package com.paladin.qos.analysis.impl.gongwei.family;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.util.StringUtil;
import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.impl.gongwei.GongWeiDataProcessor;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.dynamic.mapper.familydoctor.DataFamilyDoctorMapper;

/**残疾人签约率   
 * @author MyKite
 * @version 2019年9月11日 下午4:12:26 
 */
@Component
public class FamilyDisabledSigningRate extends GongWeiDataProcessor {
    
 	@Autowired
 	private SqlSessionContainer sqlSessionContainer;

 	public static final String EVENT_ID = "21021";

 	@Override
 	public String getEventId() {
 		return EVENT_ID;
 	}

 	private DataFamilyDoctorMapper mapper;

 	public DataFamilyDoctorMapper getMapper() {
 		if (mapper == null) {
 			mapper = sqlSessionContainer.getMapper(DataFamilyDoctorMapper.class);
 		}
 		return mapper;
 	}

 	@Override
 	public long getTotalNum(Date startTime, Date endTime, String unitId) {
 		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_GONGWEI);
 		String unit = getMappingUnitId(unitId);
 		if(StringUtil.isEmpty(unit)){
 		   return 0;
 		}
 		return getMapper().singingServiceTotal(endTime, endTime, unit);
 	}

 	@Override
 	public long getEventNum(Date startTime, Date endTime, String unitId) {
 	    	sqlSessionContainer.setCurrentDataSource(DSConstant.DS_GONGWEI);
 	    	String unit = getMappingUnitId(unitId);
 		if(StringUtil.isEmpty(unit)){
 		   return 0;
 		}
		return getMapper().personalizedHealthPushNum(endTime, endTime, unit);
 	}
}
