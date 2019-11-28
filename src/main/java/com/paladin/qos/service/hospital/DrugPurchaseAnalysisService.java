package com.paladin.qos.service.hospital;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.framework.common.PageResult;
import com.paladin.qos.dynamic.mapper.DrugPurchaseAnalysisMapper;
import com.paladin.qos.service.hospital.dto.HospitalDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2019/11/16 16:16
 */
@Service
public class DrugPurchaseAnalysisService  {

    @Autowired
    private SqlSessionContainer sqlSessionContainer;

    public Object findMoneyData(HospitalDataQuery query) {
    sqlSessionContainer.setCurrentDataSource(query.getDataSource());
        DrugPurchaseAnalysisMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(DrugPurchaseAnalysisMapper.class);
        List<Map<String, Object>> result ;
        result = mapper.findMoneyData(query.getYear());
        return result;
    }

    public Object findQuantityData(HospitalDataQuery query) {
        sqlSessionContainer.setCurrentDataSource(query.getDataSource());
        DrugPurchaseAnalysisMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(DrugPurchaseAnalysisMapper.class);
        Page<Map<String, Object>> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        List<Map<String, Object>> result = null;
        try {
            result = mapper.findQuantityData(query.getYear());
            if (result == null || result.size() == 0) {
                page.setTotal(0L);
            }
            return new PageResult<Map<String, Object>>(page);
        } finally {
            PageHelper.clearPage();
        }

    }

    public Object findMoneysData(HospitalDataQuery query) {
        sqlSessionContainer.setCurrentDataSource(query.getDataSource());
        DrugPurchaseAnalysisMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(DrugPurchaseAnalysisMapper.class);
        List<Map<String, Object>> result ;
        result = mapper.findMoneysData(query.getYear());
        return result;
    }
}
