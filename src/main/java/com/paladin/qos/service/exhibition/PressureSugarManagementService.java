package com.paladin.qos.service.exhibition;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.dynamic.mapper.exhibition.ArchivesManagementMapper;
import com.paladin.qos.dynamic.model.exhibition.Archives;
import com.paladin.qos.service.exhibition.vo.ArchivesManagementVO;
import com.paladin.qos.service.exhibition.vo.PressureSugarManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PressureSugarManagementService extends BaseExhibitionDataAcquire {

    @Autowired
    private SqlSessionContainer sqlSessionContainer;

    public List<PressureSugarManagementVO> findPressureAndSugar (String managedCenter, Date startDate, Date endDate) {
        List<PressureSugarManagementVO> pressureSugarManagementVOList=new ArrayList<>();

        //todo merge

        return pressureSugarManagementVOList;
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
