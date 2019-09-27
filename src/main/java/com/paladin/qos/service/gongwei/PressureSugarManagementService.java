package com.paladin.qos.service.gongwei;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paladin.qos.service.exhibition.BaseExhibitionDataAcquire;
import com.paladin.qos.service.gongwei.vo.PressureSugarManagementVO;


@Service
public class PressureSugarManagementService extends BaseExhibitionDataAcquire {

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
