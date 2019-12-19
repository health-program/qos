package com.paladin.qos.mapper.epidemic;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>功能描述</p>：时间分布
 *
 * @author Huangguochen
 * @create 2019/12/17 15:43
 */
public interface TimeDistributionMapper {

    List<Map<String, Object>> searchEpidemicDataByMonth(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("sickness") String sickness);

    List<Map<String, Object>> searchPeoplesDataByMonth(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("sickness") String sickness);

    List<Map<String, Object>> searchRegionEpidemicDataByMonth(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("sickness") String sickness);

    List<Map<String, Object>> searchRegionPeoplesDataByMonth(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("sickness") String sickness);

}
