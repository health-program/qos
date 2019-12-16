package com.paladin.qos.service.epidemic;

import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.mapper.epidemic.DiseaseClassificationMapper;
import com.paladin.qos.model.epidemic.EpidemicSituation;
import com.paladin.qos.service.epidemic.vo.DiseaseClassificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <疾病分类>
 *
 * @author Huangguochen
 * @create 2019/12/13 11:11
 */
@Service
public class DiseaseClassificationService extends ServiceSupport<EpidemicSituation> {

    @Autowired
    private DiseaseClassificationMapper diseaseClassificationMapper;


    public List<DiseaseClassificationVO> searchOutbreakHappening(String  sickness, List<Integer> codes, Date startTime, Date endTime) {
        return diseaseClassificationMapper.searchOutbreakHappening(sickness,codes,startTime,endTime);
    }

}
