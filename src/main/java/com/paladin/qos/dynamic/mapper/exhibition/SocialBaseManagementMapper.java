package com.paladin.qos.dynamic.mapper.exhibition;

import com.paladin.qos.dynamic.model.exhibition.Physical;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SocialBaseManagementMapper {

    //档案总数
    List<Physical> getTotalPhysical(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
