package com.paladin.qos.service.epidemic;

import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.mapper.epidemic.TimeDistributionMapper;
import com.paladin.qos.model.epidemic.EpidemicSituation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <时间分布>
 *
 * @author Huangguochen
 * @create 2019/12/17 15:44
 */
@Service
public class TimeDistributionService extends ServiceSupport<EpidemicSituation> {

    @Autowired
    private TimeDistributionMapper timeDistributionMapper;


    /**
     * 功能描述: <按机构查疫情数>
     * @param startTime
     * @param endTime
     * @return  java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author  Huangguochen
     * @date  2019/12/17
     */
    public List<Map<String,Object>> searchEpidemicDataByMonth(Date startTime, Date endTime, String  sickness) {
      return   timeDistributionMapper.searchEpidemicDataByMonth(startTime,endTime,sickness);
    }


    /**
     * 功能描述: <按机构查发病总人数>
     * @param startTime
     * @param endTime
     * @return  java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author  Huangguochen
     * @date  2019/12/17
     */
    public List<Map<String,Object>> searchPeoplesDataByMonth(Date startTime, Date endTime, String  sickness) {
        return   timeDistributionMapper.searchPeoplesDataByMonth(startTime,endTime,sickness);
    }


    /**
     * 功能描述: <按区域查疫情数>
     * @param startTime
     * @param endTime
     * @return  java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author  Huangguochen
     * @date  2019/12/17
     */
    public List<Map<String,Object>> searchRegionEpidemicDataByMonth(Date startTime, Date endTime, String  sickness) {
        return   timeDistributionMapper.searchRegionEpidemicDataByMonth(startTime,endTime,sickness);
    }


    /**
     * 功能描述: <按区域查发病总人数>
     * @param startTime
     * @param endTime
     * @return  java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author  Huangguochen
     * @date  2019/12/17
     */
    public List<Map<String,Object>> searchRegionPeoplesDataByMonth(Date startTime, Date endTime, String  sickness) {
        return   timeDistributionMapper.searchRegionPeoplesDataByMonth(startTime,endTime,sickness);
    }


}
