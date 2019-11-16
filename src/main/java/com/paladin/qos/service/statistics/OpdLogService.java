package com.paladin.qos.service.statistics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.framework.common.PageResult;
import com.paladin.qos.dynamic.mapper.OpdLogMapper;
import com.paladin.qos.service.statistics.dto.OpdQuery;

/**   
 * @author MyKite
 * @version 2019年11月16日 下午1:25:02 
 */
@Service
public class OpdLogService {

	@Autowired
	private SqlSessionContainer sqlSessionContainer;


	public Object searchFindPage(OpdQuery query) {

		sqlSessionContainer.setCurrentDataSource(query.getDataSource());
		OpdLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(OpdLogMapper.class);

		String startTime = query.getStartTime();
		String endTime = query.getEndTime();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		if (startTime == null) {
		    Calendar c = Calendar.getInstance();
		    c.add(Calendar.DATE, -1);
		    Date start = c.getTime();
		    query.setStartTime(format.format(start));//前一天

		} else {
		    query.setStartTime(startTime);
		}

		if (endTime == null) {
		    Date date = new Date();
		    query.setEndTime(format.format(date));
		} else {
		    query.setEndTime(endTime);
		}

		Page<Map<String, Object>> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		List<Map<String, Object>> result = null;
		try {
		    result = mapper.opdLogFindList(query);
			if (result == null || result.size() == 0) {
				page.setTotal(0L);
			}
			return new PageResult<Map<String, Object>>(page);
		} finally {
			PageHelper.clearPage();
		}
	}
}
