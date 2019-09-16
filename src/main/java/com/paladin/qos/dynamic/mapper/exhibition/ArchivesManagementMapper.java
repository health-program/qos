package com.paladin.qos.dynamic.mapper.exhibition;

import com.paladin.qos.dynamic.model.exhibition.Archives;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ArchivesManagementMapper {

    //档案总数
    List<Archives> getArchivesTotal (@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
