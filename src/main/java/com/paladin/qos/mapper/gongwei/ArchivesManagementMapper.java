package com.paladin.qos.mapper.gongwei;

import com.paladin.qos.model.gongwei.Archives;
import com.paladin.qos.service.gongwei.vo.ArchivesManagementVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ArchivesManagementMapper {

    //档案总数
    ArchivesManagementVO getArchivesTotal (@Param("eventId1") String eventId1, @Param("eventId1") String eventId2, @Param("unitId") String unitId);

}
