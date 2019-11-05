package com.paladin.qos.service.hospital;

import java.text.ParseException;
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

		Page<Map<String, Object>> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		try {
			List<Map<String, Object>> result = mapper.findZYBRYZB(startTime, endTime);
			if (result == null || result.size() == 0) {
				page.setTotal(0L);
			}
			return new PageResult<Map<String, Object>>(page);
		} finally {
			PageHelper.clearPage();
		}
	}

}
