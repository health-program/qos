package com.paladin.qos.mapper.gongwei;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.paladin.qos.service.gongwei.vo.ArchivesManagementVO;

@Service
public interface ArchivesManagementMapper {

    //档案总数
    ArchivesManagementVO getArchivesTotal (@Param("eventId1") String eventId1, @Param("eventId2") String eventId2, @Param("unitId") String unitId);

}
