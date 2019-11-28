package com.paladin.qos.mapper.gongwei;

import com.paladin.qos.service.gongwei.vo.ArchivesMonthsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.paladin.qos.service.gongwei.vo.ArchivesManagementVO;

@Service
public interface ArchivesManagementMapper {

    //档案总数
    Long getArchivesTotal ();


    //一个月档案数
    ArchivesMonthsVO get12MonthArchives(@Param("eventId") String eventId, @Param("year") Integer year, @Param("day") Integer day);

    //获得12个月前所有档案数
    Long getArchivesNumber(@Param("eventId") String eventId, @Param("date") Integer date);
}
