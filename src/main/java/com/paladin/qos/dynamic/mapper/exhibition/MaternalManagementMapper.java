package com.paladin.qos.dynamic.mapper.exhibition;

import com.paladin.qos.dynamic.model.exhibition.MaternalCheckup;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>功能描述</p>：孕产妇管理
 *
 * @author Huangguochen
 * @create 2019/9/3 15:18
 */
public interface MaternalManagementMapper {

    long getMalePremaritalCheckTotal(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    long getFemalePremaritalCheckTotal(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    long getPrepregnancyCheckTotal(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    long getMaternalScreeningTotal(@Param("date") Date date);

    long getHighriskMaternalTotal(@Param("date") Date date);

    long getMaternalDeathTotal(@Param("date") Date date);

    long getCervicalCancerScreeningTotal(@Param("date") Date date);

    long getBreastCancerScreeningTotal(@Param("date") Date date);

    long getSyphilisTestTotal(@Param("date") Date date);

    long getSyphilisInfectionTotal(@Param("date") Date date);

    long getHepatitisBtestTotal(@Param("date") Date date);

    long getHepatitisBdeterminesInfectionTotal(@Param("date") Date date);

    long getWomenDiseaseScreeningTotal(@Param("date") Date date);

    long getPostpartumVisitTotal(@Param("date") Date date);

    List<MaternalCheckup> getMaternalCheckupData(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    long getNumberOfFolates(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    long getFolicAcidDispensingBottle(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
