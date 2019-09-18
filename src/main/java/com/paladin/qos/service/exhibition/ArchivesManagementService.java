package com.paladin.qos.service.exhibition;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.mapper.gongwei.ArchivesManagementMapper;
import com.paladin.qos.model.gongwei.Archives;
import com.paladin.qos.service.exhibition.vo.ArchivesManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ArchivesManagementService extends BaseExhibitionDataAcquire {

    @Autowired
    private SqlSessionContainer sqlSessionContainer;

    public List<ArchivesManagementVO> findArchives(String managedCenter,Date startDate,Date endDate) {
        ArchivesManagementMapper mapper = sqlSessionContainer.getMapper(ArchivesManagementMapper.class);
        sqlSessionContainer.setCurrentDataSource("sqlserver");
        List<ArchivesManagementVO> archivesManagementVOList = new ArrayList<>();
        List<Archives> archivesList = mapper.getArchivesTotal(startDate, endDate);
        ArchivesManagementVO archivesManagementVO = null;
        for (Archives archives : archivesList) {
            if (!StringUtils.isEmpty(archives.getManagedCenter())){
                archivesManagementVO.setManagedCenter(getManagedCenterName(archives.getManagedCenter()));
                archivesManagementVO.setPeopleNumber(getPeopleNumber(archives.getManagedCenter()));
            }
            archivesManagementVO.setActiveArchivesNumber(archives.getActiveArchivesNumber());
            archivesManagementVO.setPublicArchivesNumber(archives.getPublicArchivesNumber());
            archivesManagementVOList.add(archivesManagementVO);
        }
        return archivesManagementVOList;
    }


    private String getManagedCenterName(String managedCode) {

        //todo 根据辖区code找对应辖区名称
        return "";
    }

    private Long getPeopleNumber(String managedCode){
        //todo 根据辖区code找对应辖区人数

        return null;
    }


}
