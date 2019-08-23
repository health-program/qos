package com.paladin.qos.service.count;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.common.util.TimeIntervalUtil;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.mapper.count.CountReferralMapper;
import com.paladin.qos.model.count.CountReferral;
import com.paladin.qos.model.infectioncomplication.Infection;
import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.service.count.dto.CountReferralQuery;
import com.paladin.qos.service.infectioncomplication.dto.InfectionQuery;
import com.paladin.qos.service.infectioncomplication.vo.InfectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    public int judge(String unitId){
        return countReferralMapper.judge(unitId);
    }


    public Boolean canAdd()  {
        CountReferral countReferral = countReferralMapper.findRecentlyRecord();
        //todo test
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        Date testDate=sdf.parse("2019-7-20");
        if (null != countReferral && null != countReferral.getCreateTime()) {
            return TimeIntervalUtil.canAdd(countReferral.getCreateTime(),1);
        }
        return true;
    }

}