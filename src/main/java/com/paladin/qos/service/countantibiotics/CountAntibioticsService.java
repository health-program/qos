package com.paladin.qos.service.countantibiotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.mapper.countantibiotics.countAntibioticsMapper;
import com.paladin.qos.model.countantibiotics.CountAntibiotics;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsDTO;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsVO;

@Service
public class CountAntibioticsService  extends ServiceSupport<CountAntibiotics> {// @Autowired
    @Autowired
	countAntibioticsMapper CountAntibioticsMapper; 
	  public PageResult<CountAntibioticsVO> searchFindPage(CountAntibioticsDTO query){
	       Page<CountAntibioticsVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit()); 
	       CountAntibioticsMapper.selecttoAll(query);
	       return new PageResult<>(page);
	   }
 
	    
}
