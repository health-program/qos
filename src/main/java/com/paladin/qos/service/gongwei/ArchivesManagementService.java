package com.paladin.qos.service.gongwei;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.mapper.gongwei.ArchivesManagementMapper;
import com.paladin.qos.model.gongwei.Archives;
import com.paladin.qos.service.exhibition.BaseExhibitionDataAcquire;
import com.paladin.qos.service.gongwei.vo.ArchivesManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ArchivesManagementService extends BaseExhibitionDataAcquire {

    @Autowired
    private ArchivesManagementMapper archivesManagementMapper;


    public static final String EVENT_ID1 = "22001";

    public static final String EVENT_ID2 = "22002";



    public ArchivesManagementVO findArchives(String managedCenter, Date startDate, Date endDate) {
        ArchivesManagementVO  archivesManagementVO= archivesManagementMapper.getArchivesTotal(EVENT_ID1,EVENT_ID2,managedCenter);
//        if (null!=archivesManagementVO){
//            Double peopleD=archivesManagementVO.getPeopleNumber();
//        }
        return archivesManagementVO;
    }





}
