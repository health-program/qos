package com.paladin.qos.mapper.statistics;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.paladin.qos.service.statistics.vo.ExpensesVO;

/**   
 * @author MyKite
 * @version 2019年8月30日 上午10:22:01 
 */
public interface ExpensesMapper {

    public List<ExpensesVO> inOutPersonCount(@Param("startTime")String startTime,@Param("endTime")String endTime);
    
}
