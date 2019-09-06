package com.paladin.qos.service.statistics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.paladin.qos.mapper.statistics.ExpensesMapper;
import com.paladin.qos.service.statistics.dto.ExpensesQuery;
import com.paladin.qos.service.statistics.vo.ExpensesPersonVO;
import com.paladin.qos.service.statistics.vo.ExpensesVO;

/**   
 * @author MyKite
 * @version 2019年8月30日 上午10:21:31 
 */
@Service
public class ExpensesService {

    @Autowired
    private ExpensesMapper expensesMapper;
    
    /**
     * 门急诊总人次统计
     * 表名:OP_WORKLOAD
     * 字段:FD_NUMB(门诊人次数),ED_NUMB(急诊人次数)
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ExpensesVO> oPDCount(ExpensesQuery query){
	if(StringUtil.isNotEmpty(query.getDate())){
	   String date = query.getDate();
	   String year =date.substring(0, date.indexOf("-"));
	   String month =date.substring(date.indexOf("-")+1);
	   query.setYear(year);
	   query.setMonth(month);
	}
	return expensesMapper.oPDCount(query);
    }
    
    /**
     * 住院人次统计
     * 表名:InHospitalRecord
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ExpensesVO> inPatientCount(ExpensesQuery query){
	return expensesMapper.inPatientCount(query);
    }
    
    /**
     * 住院费用(元)统计,门急诊总费用(元)统计
     * 表名:ED_Outpa_Inpa_Charge_Stat
     * 字段:COST_01(门诊费用),COST_02(住院费用)
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ExpensesVO> oPDInPatientMoney(ExpensesQuery query){
	return expensesMapper.oPDInPatientMoney(query);
    }
    
    /**
     * 转入转出人次统计
     * 表名:count_referral
     * 字段:up_out_number(转入),down_out_number(转出)
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ExpensesVO> inOutPersonCount(ExpensesQuery query){
	return expensesMapper.inOutPersonCount(query);
    }
    
    /**
     * 住院,门急诊患者使用药品数统计
     * 表名:WM_PrescriptionDetails
     * 字段:OP_EM_HP_MARK(1、门诊，2、急诊，3、住院)
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ExpensesVO> drugCount(ExpensesQuery query){
	return expensesMapper.drugCount(query);
    }
    
    
    public List<ExpensesPersonVO> opFindMonth(ExpensesQuery query){
	if(StringUtil.isNotEmpty(query.getDate())){
	    String date = query.getDate();
	    String year =date.substring(0, date.indexOf("-"));
	    String month =date.substring(date.indexOf("-")+1);
	    query.setYear(year);
	    query.setMonth(month);
	}
	return expensesMapper.opFindMonth(query);
    }
    
    public List<ExpensesPersonVO> inFindMonth(ExpensesQuery query){
	return expensesMapper.inFindMonth(query);
    }
    
    
    public List<ExpensesPersonVO> findMonth(ExpensesQuery query){
	List<ExpensesPersonVO> vos = new ArrayList<ExpensesPersonVO>();
	
	List<ExpensesPersonVO> opFindMonth =opFindMonth(query);
	List<ExpensesPersonVO> inFindMonth= inFindMonth(query);
	if(CollectionUtils.isNotEmpty(opFindMonth)){
	    vos.addAll(opFindMonth);
	}
	if(CollectionUtils.isNotEmpty(inFindMonth)){
	    vos.addAll(inFindMonth);
	}
	return vos;
    }
    
    public List<ExpensesVO> findExpensesCount(ExpensesQuery query){
	
	List<ExpensesVO> vos = new ArrayList<ExpensesVO>();
	
	List<ExpensesVO> opdCount = oPDCount(query);
	if(CollectionUtils.isNotEmpty(opdCount)){
	    vos.addAll(opdCount);
	}
	List<ExpensesVO> inPatientCount = inPatientCount(query);
	if(CollectionUtils.isNotEmpty(inPatientCount)){
	    vos.addAll(inPatientCount);
	}
	List<ExpensesVO> oPDInPatientMoney = oPDInPatientMoney(query);
	if(CollectionUtils.isNotEmpty(oPDInPatientMoney)){
	    vos.addAll(oPDInPatientMoney);
	}
	List<ExpensesVO> inOutPersonCount = inOutPersonCount(query);
	if(CollectionUtils.isNotEmpty(inOutPersonCount)){
	    vos.addAll(inOutPersonCount);
	}
	vos = vos.stream().sorted(Comparator.comparing(ExpensesVO::getoPDCount).reversed()).collect(Collectors.toList());
	return vos;
    }
    
    
    public List<ExpensesVO> findRotionalCount(ExpensesQuery query){
	
	List<ExpensesVO> vos = new ArrayList<ExpensesVO>();
	
	List<ExpensesVO> opdCount = oPDCount(query);
	if(CollectionUtils.isNotEmpty(opdCount)){
	    vos.addAll(opdCount);
	}
	List<ExpensesVO> inPatientCount = inPatientCount(query);
	if(CollectionUtils.isNotEmpty(inPatientCount)){
	    vos.addAll(inPatientCount);
	}
	List<ExpensesVO> oPDInPatientMoney = oPDInPatientMoney(query);
	if(CollectionUtils.isNotEmpty(oPDInPatientMoney)){
	    vos.addAll(oPDInPatientMoney);
	}
	List<ExpensesVO> drugCount = drugCount(query);
	if(CollectionUtils.isNotEmpty(drugCount)){
	    vos.addAll(drugCount);
	}	
	vos = vos.stream().sorted(Comparator.comparing(ExpensesVO::getoPDCount).reversed()).collect(Collectors.toList());
	return vos;
    }
}
