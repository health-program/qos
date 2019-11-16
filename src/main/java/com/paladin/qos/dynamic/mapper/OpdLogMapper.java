package com.paladin.qos.dynamic.mapper;

import java.util.List;
import java.util.Map;
import com.paladin.qos.service.statistics.dto.OpdQuery;

/**   
 * @author MyKite
 * @version 2019年11月16日 下午2:20:16 
 */
public interface OpdLogMapper {
    
    List<Map<String, Object>> opdLogFindList(OpdQuery query);
}
