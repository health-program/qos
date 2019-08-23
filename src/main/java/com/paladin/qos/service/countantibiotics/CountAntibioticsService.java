package com.paladin.qos.service.countantibiotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.mapper.countantibiotics.CountAntibioticsMapper;
import com.paladin.qos.model.countantibiotics.CountAntibiotics;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsDTO;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsVO;

import java.util.Date;
import java.util.List;

@Service
public class CountAntibioticsService  extends ServiceSupport<CountAntibiotics> {

    @Autowired
    CountAntibioticsMapper countAntibioticsMapper;

    public PageResult<CountAntibioticsVO> searchFindPage(CountAntibioticsDTO query) {
	Page<CountAntibioticsVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
	countAntibioticsMapper.selecttoAll(query);
	return new PageResult<>(page);
    }

    public int judge(String unitId){
	return countAntibioticsMapper.judge(unitId);
    }

    public List<CountAntibioticsVO> getReportByQuery(String unitId,Date month) {
//		eventId, TimeUtil.getSerialNumberByDay(startDate), TimeUtil.getSerialNumberByDay(endDate)
        return countAntibioticsMapper.getReportByQuery(unitId,month);
    }

}
