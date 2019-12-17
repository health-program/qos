package com.paladin.qos.service.countantibiotics;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.mapper.countantibiotics.CountAntibioticsMapper;
import com.paladin.qos.model.countantibiotics.CountAntibiotics;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsQuery;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsPercentVO;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsVO;

@Service
public class CountAntibioticsService extends ServiceSupport<CountAntibiotics> {

	@Autowired
	CountAntibioticsMapper countAntibioticsMapper;

	public PageResult<CountAntibioticsVO> searchFindPage(CountAntibioticsQuery query) {
		Page<CountAntibioticsVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		QosUserSession userSession = QosUserSession.getCurrentUserSession();
		if (!userSession.isAdminRoleLevel()) {
			String[] agencyIds = userSession.getAgencyIds();
			query.setUnitIds(agencyIds);
		}
		countAntibioticsMapper.selecttoAll(query);
		return new PageResult<>(page);
	}
	
	
	public List<CountAntibioticsPercentVO> percent(){
	    return countAntibioticsMapper.percent();
	}

	public int judge(String unitId) {
		return countAntibioticsMapper.judge(unitId);
	}

	public int judgeDate(String unitId,String inputDate) {
		return countAntibioticsMapper.judgeDate(unitId,inputDate);
	}

	public List<CountAntibioticsVO> getReportByQuery(String unitId, String month) {
		return countAntibioticsMapper.getReportByQuery(unitId, month);
	}

}
