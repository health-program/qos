package com.paladin.qos.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.mapper.report.ReportDataMapper;
import com.paladin.qos.model.report.ReportData;
import com.paladin.qos.service.report.dto.ReportDataQueryDTO;
import com.paladin.qos.service.report.vo.ReportDataVO;

/**   
 * @author MyKite
 * @version 2019年8月23日 下午4:09:47 
 */
@Service
public class ReportDataService extends ServiceSupport<ReportData>{

    @Autowired
    ReportDataMapper reportDataMapper;
    
    public PageResult<ReportDataVO> findAll(ReportDataQueryDTO query) {
   	Page<ReportDataVO> page = PageHelper.offsetPage(query.getOffset(),query.getLimit());
   	QosUserSession session = QosUserSession.getCurrentUserSession();
	if(!session.isAdminRoleLevel()){
	    String[] agencyIds = session.getAgencyIds();
	    query.setUnitId(agencyIds);
	}
   	reportDataMapper.findAll(query);
   	return new PageResult<>(page);
       }
    
    public int judgePerinatal(String unitId){
	return reportDataMapper.judgePerinatal(unitId);
    }
    
    public int judge(int type){
	return reportDataMapper.judge(type);
    }
}
