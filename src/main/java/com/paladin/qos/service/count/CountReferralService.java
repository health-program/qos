package com.paladin.qos.service.count;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.common.util.TimeIntervalUtil;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.mapper.count.CountReferralMapper;
import com.paladin.qos.model.count.CountReferral;
import com.paladin.qos.service.count.dto.CountReferralQuery;
import com.paladin.qos.service.infectioncomplication.vo.InfectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


@Service
public class CountReferralService extends ServiceSupport<CountReferral> {


    @Autowired
    private CountReferralMapper countReferralMapper;
    //分页列表
    public PageResult<InfectionVO> searchFindPage(CountReferralQuery query) {
        Page<InfectionVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        QosUserSession userSession=QosUserSession.getCurrentUserSession();
        if (!userSession.isAdminRoleLevel()){
            String[] agencyIds = userSession.getAgencyIds();
            query.setUnitIds(agencyIds);
        }
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

    public long searchIsSigningNumber() {
        Example getCountExample = GeneralCriteriaBuilder.buildAnd(CountReferral.class, CountReferral.COLUMN_IS_SIGNING, QueryType.EQUAL, 1);
        return  countReferralMapper.selectCountByExample(getCountExample);
    }
}