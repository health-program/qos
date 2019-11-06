package com.paladin.qos.service.hospital;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.framework.common.PageResult;
import com.paladin.qos.dynamic.mapper.HospitalIssuesMapper;
import com.paladin.qos.service.hospital.dto.HospitalDataQuery;

@Service
public class HospitalDataService {

	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	private static String DEFAULT_START_TIME = "2010-01-01";
	private static String DEFAULT_END_TIME = "2099-01-01";

	public Object findIssues(HospitalDataQuery query) {

		sqlSessionContainer.setCurrentDataSource(query.getDataSource());
		HospitalIssuesMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalIssuesMapper.class);
		
		Date startTime = query.getStartTime();
		Date endTime = query.getEndTime();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String startDate = null;
		String endDate = null;

		if (startTime == null) {
			startDate = DEFAULT_START_TIME;
		} else {
			startDate = format.format(startTime);
		}

		if (endTime == null) {
			endDate = DEFAULT_END_TIME;
		} else {
			endDate = format.format(endTime);
		}

		String issuesType = query.getIssuesType();

		Page<Map<String, Object>> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		List<Map<String, Object>> result = null;
		try {
			if ( "BCJL".equals(issuesType)){
				result = mapper.findBCJL(startDate, endDate);
			}else if ("CYJL".equals(issuesType)){
				result = mapper.findCYJL(startDate, endDate);
			}  else if ("FYCB".equals(issuesType)){
				result = mapper.findFYCB(startDate, endDate);
			} else if ("FYZB".equals(issuesType)){
				result = mapper.findFYZB(startDate, endDate);
			} else if ("HCSY".equals(issuesType)){
				result = mapper.findHCSY(startDate, endDate);
			} else if ("GH".equals(issuesType)){
				result = mapper.findGH(startDate, endDate);
			} else if ("HCCK".equals(issuesType)){
				result = mapper.findHCCK(startDate, endDate);
			} else if ("HCRK".equals(issuesType)){
				result = mapper.findHCRK(startDate, endDate);
			} else if ("HZXX".equals(issuesType)){
				result = mapper.findHZXX(startDate, endDate);
			} else if ("JCBG".equals(issuesType)){
				result = mapper.findJCBG(startDate, endDate);
			} else if ("JCSQ".equals(issuesType)){
				result = mapper.findJCSQ(startDate, endDate);
			} else if ("JYBG".equals(issuesType)){
				result = mapper.findJYBG(startDate, endDate);
			} else if ("JYMX".equals(issuesType)){
				result = mapper.findJYMX(startDate, endDate);
			} else if ("JYSQ".equals(issuesType)){
				result = mapper.findJYSQ(startDate, endDate);
			} else if ("JZJL".equals(issuesType)){
				result = mapper.findJZJL(startDate, endDate);
			} else if ("ZYSF".equals(issuesType)){
				result = mapper.findZYSF(startDate, endDate);
			} else if ("RYCY".equals(issuesType)){
				result = mapper.findRYCY(startDate, endDate);
			} else if ("RYJL".equals(issuesType)){
				result = mapper.findRYJL(startDate, endDate);
			} else if ("SSCZ".equals(issuesType)){
				result = mapper.findSSCZ(startDate, endDate);
			} else if ("SXJL".equals(issuesType)){
				result = mapper.findSXJL(startDate, endDate);
			} else if ("SXSQ".equals(issuesType)){
				result = mapper.findSXSQ(startDate, endDate);
			} else if ("XYCFCB".equals(issuesType)){
				result = mapper.findXYCFCB(startDate, endDate);
			} else if ("XYCFZB".equals(issuesType)){
				result = mapper.findXYCFZB(startDate, endDate);
			} else if ("XYBA".equals(issuesType)){
				result = mapper.findXYBA(startDate, endDate);
			} else if ("XYBACB".equals(issuesType)){
				result = mapper.findXYBACB(startDate, endDate);
			} else if ("YPCK".equals(issuesType)){
				result = mapper.findYPCK(startDate, endDate);
			} else if ("YPRK".equals(issuesType)){
				result = mapper.findYPRK(startDate, endDate);
			} else if ("ZLCZ".equals(issuesType)){
				result = mapper.findZLCZ(startDate, endDate);
			} else if ("ZYCFCB".equals(issuesType)){
				result = mapper.findZYCFCB(startDate, endDate);
			} else if ("ZYCFZB".equals(issuesType)){
				result = mapper.findZYCFZB(startDate, endDate);
			} else if ("ZYBA".equals(issuesType)){
				result = mapper.findZYBA(startDate, endDate);
			} else if ("ZYBASS".equals(issuesType)){
				result = mapper.findZYBASS(startDate, endDate);
			} else if ("BRYZ".equals(issuesType)){
				result = mapper.findZYBRYZB(startDate, endDate);
			}
			if (result == null || result.size() == 0) {
				page.setTotal(0L);
			}
			return new PageResult<Map<String, Object>>(page);
		} finally {
			PageHelper.clearPage();
		}
	}

}
