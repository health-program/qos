package com.paladin.qos.service.statistics;

import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paladin.common.util.DateFormatUtil;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.mapper.statistics.ExpensesMapper;
import com.paladin.qos.service.statistics.vo.ExpensesVO;

/**   
 * @author MyKite
 * @version 2019年8月30日 上午10:21:31 
 */
@Service
public class ExpensesService {

    @Autowired
    private ExpensesMapper expensesMapper;
    
    private static SimpleDateFormat format = DateFormatUtil.getThreadSafeFormat("yyyy-MM-dd");
    
    /**
     * 转入转出人次统计
     * 表名:count_referral
     * 字段:up_out_number(转入),down_out_number(转出)
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ExpensesVO> inOutPersonCount(AnalysisRequest request){
	String startTime = format.format(request.getStartTime());
	String endTime = format.format(request.getEndTime());
	return expensesMapper.inOutPersonCount(startTime,endTime);
    }

}
