package com.paladin.qos.mapper.count;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.count.CountReferral;
import com.paladin.qos.service.count.dto.CountReferralQuery;
import com.paladin.qos.service.count.vo.CountReferralVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountReferralMapper extends CustomMapper<CountReferral>{


    //根据查询条件搜索记录列表
    List<CountReferralVO> findReferralRecord(CountReferralQuery query);

    //查找最近一条记录
    CountReferral findRecentlyRecord();

    public int judge(@Param("unitId") String unitId);
}