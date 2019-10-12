package com.paladin.qos.service.gongwei;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.paladin.qos.mapper.gongwei.PressureSugarManagementMapper;
import com.paladin.qos.model.gongwei.PressureSugar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.qos.service.exhibition.BaseExhibitionDataAcquire;
import com.paladin.qos.service.gongwei.vo.PressureSugarManagementVO;


@Service
public class PressureSugarManagementService extends BaseExhibitionDataAcquire {

    @Autowired
    private PressureSugarManagementMapper pressureSugarManagementMapper;

    //血压规范数
    public PressureSugar findPressureManage(String unitId, Date startDate, Date endDate) {
        return pressureSugarManagementMapper.getPressureManageNumber(getStartYear(startDate),getEndYear(endDate), unitId);
    }

    //血压随访数
    public PressureSugar findPressureFollow(String unitId, Date startDate, Date endDate) {
        return pressureSugarManagementMapper.getPressureFollowNumber(getStartYear(startDate),getEndYear(endDate), unitId);
    }

    //血糖规范数
    public PressureSugar findSugarManage(String unitId, Date startDate, Date endDate) {
        return pressureSugarManagementMapper.getSugarManageNumber(getStartYear(startDate),getEndYear(endDate), unitId);
    }

    //血糖随访数
    public PressureSugar findSugarFollow(String unitId, Date startDate, Date endDate) {
        return pressureSugarManagementMapper.getSugarFollowNumber(getStartYear(startDate),getEndYear(endDate), unitId);
    }


    public void mergeData(String unitId, Date startDate, Date endDate, PressureSugarManagementVO pressureSugar) {
        pressureSugar.setPressureManageNumber(findPressureManage(unitId, startDate, endDate).getPressureManageNumber());
        pressureSugar.setPressureFollowNumber(findPressureFollow(unitId, startDate, endDate).getPressureFollowNumber());
        pressureSugar.setSugarManageNumber(findSugarManage(unitId, startDate, endDate).getSugarManageNumber());
        pressureSugar.setSugarFollowNumber(findSugarFollow(unitId, startDate, endDate).getSugarFollowNumber());
    }

    private Integer getStartYear(Date date) {
        if (null == date)
            return 0;
        Calendar start = Calendar.getInstance();
        return start.get(Calendar.YEAR);
    }

    private Integer getEndYear(Date date) {
        if (null==date){
            Calendar end = Calendar.getInstance();
            if (end.get(Calendar.MONTH)>1){
                return end.get(Calendar.YEAR)-1;
            }else{
                return end.get(Calendar.YEAR)-2;
            }
        }else{
            Calendar end = Calendar.getInstance();
            end.setTime(date);
            Date current=new Date();
            if (current.before(date)){
                Calendar c = Calendar.getInstance();
                if (c.get(Calendar.MONTH)>1){
                    return c.get(Calendar.YEAR)-1;
                }else{
                    return c.get(Calendar.YEAR)-2;
                }
            }else{
                Calendar c = Calendar.getInstance();
                if (c.get(Calendar.MONTH)>1){
                    return end.get(Calendar.YEAR);
                }else{
                    return end.get(Calendar.YEAR)-1;
                }
            }
        }

    }






}
