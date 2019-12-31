package com.paladin.qos.service.hospital;

import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.mapper.count.HospitalDataScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <医院上传数据得分>
 *
 * @author Huangguochen
 * @create 2019/12/31 15:50
 */
@Service
public class HospitalDataScoreService {

    @Autowired
    private HospitalDataScoreMapper hospitalDataScoreMapper;


    public List<Map<String, Object>> findData(AnalysisRequest analysisRequest) {
        List<String> ids = analysisRequest.getEventIds();
        List<String> eventIds = ids.stream().filter(eventId -> DataConstantContainer.getEvent(eventId) != null).collect(Collectors.toList());
        int startTime = TimeUtil.getSerialNumberByDay(analysisRequest.getStartTime());
        int endTime = TimeUtil.getSerialNumberByDay(analysisRequest.getEndTime());
        return  hospitalDataScoreMapper.findData(startTime,endTime,eventIds,analysisRequest.getUnitId());
    }
}
