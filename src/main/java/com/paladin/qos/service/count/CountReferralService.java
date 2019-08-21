package com.paladin.qos.service.count;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.mapper.count.CountReferralMapper;
import com.paladin.qos.model.count.CountReferral;
import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.service.count.dto.CountReferralQuery;
import com.paladin.qos.service.infectioncomplication.dto.InfectionQuery;
import com.paladin.qos.service.infectioncomplication.vo.InfectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountReferralService extends ServiceSupport<CountReferral> {


    @Autowired
    private CountReferralMapper countReferralMapper;
    //分页列表
    public PageResult<InfectionVO> searchFindPage(CountReferralQuery query) {
        Page<InfectionVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        countReferralMapper.findReferralRecord(query);
        return new PageResult<>(page);
    }

}