package com.paladin.qos.dynamic.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2019/11/16 16:19
 */
public interface DrugPurchaseAnalysisMapper {

    List<Map<String, Object>> findMoneyData(@Param("year") String year);

    List<Map<String, Object>> findQuantityData(@Param("year") String year);

    List<Map<String, Object>> findMoneysData(@Param("year") String year);
}
