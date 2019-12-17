package com.paladin.qos.mapper.epidemic;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.epidemic.EpidemicSituation;
import com.paladin.qos.service.epidemic.vo.DiseaseClassificationVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>功能描述</p>：
 *
 * @author Huangguochen
 * @create 2019/12/13 11:10
 */
public interface DiseaseClassificationMapper extends CustomMapper<EpidemicSituation> {

    List<DiseaseClassificationVO> searchOutbreakHappening(@Param("sickness") String sickness, @Param("codes") List<Integer> codes, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
