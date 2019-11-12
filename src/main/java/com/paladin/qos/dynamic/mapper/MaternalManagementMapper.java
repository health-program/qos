package com.paladin.qos.dynamic.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>功能描述</p>：孕产妇管理
 *
 * @author Huangguochen
 * @create 2019/9/3 15:18
 */
public interface MaternalManagementMapper {

    long getHighriskMaternalNumber(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

}
