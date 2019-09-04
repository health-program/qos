package com.paladin.qos.dynamic.mapper.exhibition;

import com.paladin.qos.dynamic.model.exhibition.ChildHealthCheckup;
import com.paladin.qos.dynamic.model.exhibition.InfantCongenitalHeartDisease;
import com.paladin.qos.dynamic.model.exhibition.InfantVision;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>功能描述</p>：儿童保健管理
 *
 * @author Huangguochen
 * @create 2019/9/3 13:25
 */
public interface ChildCareManagementMapper {

    long getInfantDeathTotal(@Param("date") Date date);

    long getNeonatalBirthDefectsTotal(@Param("date") Date date);

    long getNeonatalDiseaseScreeningTotal(@Param("date") Date date);

    long getChildCardTotal(@Param("date") Date date);

    long getFemaleNewbornChildbirthTotal(@Param("date") Date date);

    long getMaleNewbornChildbirthTotal(@Param("date") Date date);

    long getChildHealthCheckTotal(@Param("date") Date date);

    List<InfantCongenitalHeartDisease> getInfantCongenitalHeartDisease(@Param("startTime") Date startTime, @Param("endDate") Date endDate);

    List<ChildHealthCheckup> getChildHealthCheckup(@Param("startTime") Date startTime, @Param("endDate") Date endDate);

    List<InfantVision> getInfantVisionTotal(@Param("startTime") Date startTime, @Param("endDate") Date endDate);
}
