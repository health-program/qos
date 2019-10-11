package com.paladin.qos.mapper.gongwei;

import com.paladin.qos.model.gongwei.Physical;
import com.paladin.qos.model.gongwei.PressureSugar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface PressureSugarManagementMapper {

    //高血压管理规范管理数
    PressureSugar getPressureManageNumber(@Param("startYear")Integer startYear, @Param("endYear")Integer endYear,@Param("unitId") String unitId);

    //高血压管理随访数
    PressureSugar getPressureFollowNumber(@Param("startYear")Integer startYear, @Param("endYear")Integer endYear,@Param("unitId") String unitId);

    //高血糖管理规范管理数
    PressureSugar getSugarManageNumber(@Param("startYear")Integer startYear, @Param("endYear")Integer endYear,@Param("unitId") String unitId);

    //高血糖管理随访数
    PressureSugar getSugarFollowNumber(@Param("startYear")Integer startYear, @Param("endYear")Integer endYear,@Param("unitId") String unitId);

}
