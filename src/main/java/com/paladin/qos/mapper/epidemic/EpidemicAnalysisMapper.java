package com.paladin.qos.mapper.epidemic;

import java.util.List;
import java.util.Map;
import com.paladin.qos.service.epidemic.dto.EpidemicAnalysisQueryDTO;

/**   
 * @author MyKite
 * @version 2019年12月11日 上午10:09:11 
 */
public interface EpidemicAnalysisMapper {
    
    List<Map<String, Object>> schoolepidemicquantity(EpidemicAnalysisQueryDTO query);
    
    List<Map<String, Object>> schoolepidemicRate(EpidemicAnalysisQueryDTO query);

    List<Map<String, Object>> schoolepidemicnumber(EpidemicAnalysisQueryDTO query);
    
    List<Map<String, Object>> schoolepidemicnumberRate(EpidemicAnalysisQueryDTO query);
    
    List<Map<String, Object>> schoolPersonnelStartNumber(EpidemicAnalysisQueryDTO query);
    
    List<Map<String, Object>> schoolPersonnelcrowd(EpidemicAnalysisQueryDTO query);
    
    List<Map<String, Object>> schoolPersonnelRegion(EpidemicAnalysisQueryDTO query);
    
    //区域涉及人数构成比
    List<Map<String, Object>> schoolPersonnelRegion1(EpidemicAnalysisQueryDTO query);
    
    //区域涉及人数构成比详情
    List<Map<String, Object>> schoolPersonnelRegion1details(EpidemicAnalysisQueryDTO query);
    
    List<Map<String, Object>> schoolPersonnelstartNum(EpidemicAnalysisQueryDTO query);
    
    List<Map<String, Object>> schoolPersonnelincidence(EpidemicAnalysisQueryDTO query);
    
    List<Map<String, Object>> schoolPersonneldisease(EpidemicAnalysisQueryDTO query);
    
    List<Map<String, Object>> schoolPersonnelSurpassRegion(EpidemicAnalysisQueryDTO query);
    
}
