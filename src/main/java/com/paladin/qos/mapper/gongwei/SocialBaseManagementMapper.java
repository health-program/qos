package com.paladin.qos.mapper.gongwei;

import com.paladin.qos.model.gongwei.Physical;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SocialBaseManagementMapper {

    //档案总数
    List<Physical> getTotalPhysical(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
