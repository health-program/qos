package com.paladin.qos.mapper.count;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>功能描述</p>：
 *
 * @author Huangguochen
 * @create 2019/12/31 15:52
 */
@Repository
public interface HospitalDataScoreMapper {

    List<Map<String, Object>> findData(@Param("startTime") int startTime, @Param("endTime") int endTime, @Param("eventIds") List<String> eventIds, @Param("unitId") String unitId);
}
