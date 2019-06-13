package com.paladin.qos.service.epidemic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.qos.mapper.epidemic.EpidemicSituationMapper;
import com.paladin.qos.model.epidemic.EpidemicSituation;
import com.paladin.qos.service.epidemic.dto.EpidemicSituationDTO;
import com.paladin.qos.service.epidemic.dto.EpidemicSituationQueryDTO;
import com.paladin.qos.service.epidemic.vo.EpidemicSituationVO;

/**   
 * @author 黄伟华
 * @version 2019年6月11日 下午3:56:28 
 */
@Service
public class EpidemicSituationService extends ServiceSupport<EpidemicSituation>{
    
    @Autowired
    private EpidemicSituationMapper epidemicSituationMapper;
    
   public PageResult<EpidemicSituationVO> searchFindPage(EpidemicSituationQueryDTO query){
       Page<EpidemicSituationVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit()); 
       epidemicSituationMapper.searchFindPage(query);
       return new PageResult<>(page);
   }
   
    public int updateEpidemic(EpidemicSituationDTO dto) {
	String id = dto.getId();
	
	if (StringUtil.isEmpty(id)) {
	    throw new BusinessException("找不到更新的疫情信息");
	}
	
	EpidemicSituation es = get(id);
	
	if (es == null) {
	    throw new BusinessException("找不到更新的疫情信息");
	}
	
	SimpleBeanCopyUtil.simpleCopy(dto, es);
	return update(es);
    }

}
