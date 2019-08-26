package com.paladin.qos.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.mapper.report.ReportDataMapper;
import com.paladin.qos.model.report.ReportData;

/**   
 * @author MyKite
 * @version 2019年8月23日 下午4:09:47 
 */
@Service
public class ReportDataService extends ServiceSupport<ReportData>{

    @Autowired
    ReportDataMapper reportDataMapper;
    
    public int judgePerinatal(String unitId){
	return reportDataMapper.judgePerinatal(unitId);
    }
    
    public int judge(int type){
	return reportDataMapper.judge(type);
    }
}
