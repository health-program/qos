package com.paladin.qos.service.gongwei;

import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.mapper.gongwei.ArchivesManagementMapper;
import com.paladin.qos.service.exhibition.BaseExhibitionDataAcquire;
import com.paladin.qos.service.gongwei.vo.ArchivesMonthsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ArchivesManagementService extends BaseExhibitionDataAcquire {

    @Autowired
    private ArchivesManagementMapper archivesManagementMapper;


    public ArchivesMonthsVO find12MonthArchives(String eventId, Integer year, Integer day) {

        return archivesManagementMapper.get12MonthArchives(eventId, year, day);
    }

    public Long findArchivesNumber(String eventId, Date date) {
        Integer dateInt = TimeUtil.getSerialNumberByDay(date);
        Long number = archivesManagementMapper.getArchivesNumber(eventId, dateInt);
        return null == number ? 0l : number;
    }


}
