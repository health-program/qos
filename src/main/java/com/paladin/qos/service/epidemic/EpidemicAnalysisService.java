package com.paladin.qos.service.epidemic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.paladin.framework.common.PageResult;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.mapper.epidemic.EpidemicAnalysisMapper;
import com.paladin.qos.service.epidemic.dto.EpidemicAnalysisQueryDTO;

/**   
 * @author MyKite
 * @version 2019年12月11日 上午10:08:25 
 */
@Service
public class EpidemicAnalysisService {

    @Autowired
    EpidemicAnalysisMapper epidemicAnalysisMapper;
    
    //疫情事件量
    public List<Map<String, Object>> schoolepidemicquantity(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolepidemicquantity(query);
    }
    //疫情构成比
    public List<Map<String, Object>> schoolepidemicRate(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolepidemicRate(query);
    }
    
    //疫情涉及人数
    public List<Map<String, Object>> schoolepidemicnumber(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolepidemicnumber(query);
    }
    
    //疫情涉及人数构成比
    public List<Map<String, Object>> schoolepidemicnumberRate(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolepidemicnumberRate(query);
    }
    
    //疫情起均人数
    public List<Map<String, Object>> schoolPersonnelStartNumber(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolPersonnelStartNumber(query);
    }
    
    //疫情人群分布
    public List<Map<String, Object>> schoolPersonnelcrowd(EpidemicAnalysisQueryDTO query){
	String schoolSectionIds = query.getSchoolSection();
	if(StringUtil.isNotEmpty(schoolSectionIds)){
	    String[] strArr = schoolSectionIds.split(",");
	    query.setSchoolSectionIds(strArr);
	}
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolPersonnelcrowd(query);
    }
    
    //区域疫情量构成比
    public List<Map<String, Object>> schoolPersonnelRegion(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolPersonnelRegion(query);
    }
    
    //区域涉及人数构成比
    public List<Map<String, Object>> schoolPersonnelRegion1(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolPersonnelRegion1(query);
    }
    
    //区域涉及人数构成比详情
    public Object schoolPersonnelRegion1details(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	
	Page<Map<String, Object>> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
	List<Map<String, Object>> result = null;
	try {
	    result = epidemicAnalysisMapper.schoolPersonnelRegion1details(query);
		if (result == null || result.size() == 0) {
			page.setTotal(0L);
		}
		return new PageResult<Map<String, Object>>(page);
	} finally {
		PageHelper.clearPage();
	}
	
    }
    
    //区域起均人数
    public List<Map<String, Object>> schoolPersonnelstartNum(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolPersonnelstartNum(query);
    }
    
    //区域罹患率
    public List<Map<String, Object>> schoolPersonnelincidence(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolPersonnelincidence(query);
    }
    
    //警戒值分析-疾病分类
    public List<Map<String, Object>> schoolPersonneldisease(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolPersonneldisease(query);
    }
    //警戒值分析-区镇分布
    public List<Map<String, Object>> schoolPersonnelSurpassRegion(EpidemicAnalysisQueryDTO query){
	query.setAgencyId(agencyIds());
	return epidemicAnalysisMapper.schoolPersonnelSurpassRegion(query);
    }
    
    private String[] agencyIds(){
	QosUserSession userSession = QosUserSession.getCurrentUserSession();
	String[] agencyId = userSession.getAgencyIds();
	return agencyId;
    }
}
