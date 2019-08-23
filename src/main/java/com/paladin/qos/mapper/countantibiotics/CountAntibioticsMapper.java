package com.paladin.qos.mapper.countantibiotics;



import java.util.Date;
import java.util.List;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.countantibiotics.CountAntibiotics;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsDTO;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;



@Service
public interface CountAntibioticsMapper extends CustomMapper<CountAntibiotics> {


  public List<CountAntibioticsVO> selecttoAll(CountAntibioticsDTO query);


  public int judge(@Param("unitId") String unitId);

  public Object queryById(String id);


   List<CountAntibioticsVO> getReportByQuery(@Param("unitId") String unitId,@Param("month") Date month);

}
