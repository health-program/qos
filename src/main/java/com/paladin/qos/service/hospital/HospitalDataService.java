package com.paladin.qos.service.hospital;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.framework.common.PageResult;
import com.paladin.qos.dynamic.mapper.HospitalIssuesMapper;
import com.paladin.qos.service.hospital.dto.HospitalDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class HospitalDataService {

	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	private static Date DEFAULT_START_TIME;
	private static Date DEFAULT_END_TIME;

	static {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			DEFAULT_START_TIME = format.parse("2010-01-01");
			DEFAULT_END_TIME = format.parse("2099-01-01");
		} catch (ParseException e) {
		}
	}

	public Object findIssues(HospitalDataQuery query) {

		sqlSessionContainer.setCurrentDataSource(query.getDataSource());
		HospitalIssuesMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalIssuesMapper.class);
		Date startTime = query.getStartTime();
		Date endTime = query.getEndTime();

		if (startTime == null) {
			startTime = DEFAULT_START_TIME;
		}

		if (endTime == null) {
			endTime = DEFAULT_END_TIME;
		}

		String issuesType = query.getIssuesType();

		Page<Map<String, Object>> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		List<Map<String, Object>> result = null;
		try {
			if ( "BCJL".equals(issuesType)){
				result = mapper.findBCJL(startTime, endTime);
			}else if ("CYJL".equals(issuesType)){
				result = mapper.findCYJL(startTime, endTime);
			}  else if ("FYCB".equals(issuesType)){
				result = mapper.findFYCB(startTime, endTime);
			} else if ("FYZB".equals(issuesType)){
				result = mapper.findFYZB(startTime, endTime);
			} else if ("HCSY".equals(issuesType)){
				result = mapper.findHCSY(startTime, endTime);
			} else if ("GH".equals(issuesType)){
				result = mapper.findGH(startTime, endTime);
			} else if ("HCCK".equals(issuesType)){
				result = mapper.findHCCK(startTime, endTime);
			} else if ("HCRK".equals(issuesType)){
				result = mapper.findHCRK(startTime, endTime);
			} else if ("HZXX".equals(issuesType)){
				result = mapper.findHZXX(startTime, endTime);
			} else if ("JCBG".equals(issuesType)){
				result = mapper.findJCBG(startTime, endTime);
			} else if ("JCSQ".equals(issuesType)){
				result = mapper.findJCSQ(startTime, endTime);
			} else if ("JYBG".equals(issuesType)){
				result = mapper.findJYBG(startTime, endTime);
			} else if ("JYMX".equals(issuesType)){
				result = mapper.findJYMX(startTime, endTime);
			} else if ("JYSQ".equals(issuesType)){
				result = mapper.findJYSQ(startTime, endTime);
			} else if ("JZJL".equals(issuesType)){
				result = mapper.findJZJL(startTime, endTime);
			} else if ("ZYSF".equals(issuesType)){
				result = mapper.findZYSF(startTime, endTime);
			} else if ("RYCY".equals(issuesType)){
				result = mapper.findRYCY(startTime, endTime);
			} else if ("RYJL".equals(issuesType)){
				result = mapper.findRYJL(startTime, endTime);
			} else if ("SSCZ".equals(issuesType)){
				result = mapper.findSSCZ(startTime, endTime);
			} else if ("SXJL".equals(issuesType)){
				result = mapper.findSXJL(startTime, endTime);
			} else if ("SXSQ".equals(issuesType)){
				result = mapper.findSXSQ(startTime, endTime);
			} else if ("XYCFCB".equals(issuesType)){
				result = mapper.findXYCFCB(startTime, endTime);
			} else if ("XYCFZB".equals(issuesType)){
				result = mapper.findXYCFZB(startTime, endTime);
			} else if ("XYBA".equals(issuesType)){
				result = mapper.findXYBA(startTime, endTime);
			} else if ("XYBACB".equals(issuesType)){
				result = mapper.findXYBACB(startTime, endTime);
			} else if ("YPCK".equals(issuesType)){
				result = mapper.findYPCK(startTime, endTime);
			} else if ("YPRK".equals(issuesType)){
				result = mapper.findYPRK(startTime, endTime);
			} else if ("ZLCZ".equals(issuesType)){
				result = mapper.findZLCZ(startTime, endTime);
			} else if ("ZYCFCB".equals(issuesType)){
				result = mapper.findZYCFCB(startTime, endTime);
			} else if ("ZYCFZB".equals(issuesType)){
				result = mapper.findZYCFZB(startTime, endTime);
			} else if ("ZYBA".equals(issuesType)){
				result = mapper.findZYBA(startTime, endTime);
			} else if ("ZYBASS".equals(issuesType)){
				result = mapper.findZYBASS(startTime, endTime);
			} else if ("BRYZ".equals(issuesType)){
				result = mapper.findZYBRYZB(startTime, endTime);
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
