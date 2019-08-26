package com.paladin.qos.mapper.report;

import io.lettuce.core.dynamic.annotation.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.report.ReportData;

/**   
 * @author MyKite
 * @version 2019年8月23日 下午4:08:58 
 */
public interface ReportDataMapper extends CustomMapper<ReportData>{

    public int judgePerinatal(@Param("unitId")String unitId);
    
    public int judge(@Param("type")int type);
}
