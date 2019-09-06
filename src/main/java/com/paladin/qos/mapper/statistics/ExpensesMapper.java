package com.paladin.qos.mapper.statistics;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.qos.service.statistics.dto.ExpensesQuery;
import com.paladin.qos.service.statistics.vo.ExpensesPersonVO;
import com.paladin.qos.service.statistics.vo.ExpensesVO;

/**   
 * @author MyKite
 * @version 2019年8月30日 上午10:22:01 
 */
public interface ExpensesMapper {

    public List<ExpensesVO> oPDCount(@Param("query")ExpensesQuery query);
    
    public List<ExpensesVO> inPatientCount(@Param("query")ExpensesQuery query);
    
    public List<ExpensesVO> oPDInPatientMoney(@Param("query")ExpensesQuery query);
    
    public List<ExpensesVO> inOutPersonCount(@Param("query")ExpensesQuery query);
    
    public List<ExpensesVO> drugCount(@Param("query")ExpensesQuery query);
    
    public List<ExpensesPersonVO> opFindMonth(@Param("query")ExpensesQuery query);
    
    public List<ExpensesPersonVO> inFindMonth(@Param("query")ExpensesQuery query);
    
    
}
