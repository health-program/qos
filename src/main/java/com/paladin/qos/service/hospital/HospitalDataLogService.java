package com.paladin.qos.service.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.dynamic.mapper.HospitalLogMapper;
import com.paladin.qos.model.count.CountReferral;
import com.paladin.qos.model.hospitalData.HospitalDataCheck;
import com.paladin.qos.model.hospitalData.HospitalDataLog;

import java.util.List;


@Service
public class HospitalDataLogService extends ServiceSupport<CountReferral> {

	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public PageResult<HospitalDataLog> searchFindPage(HospitalDataLog query) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_AADATA);
		HospitalLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalLogMapper.class);
		int count = mapper.countHospitalDataLog(query);
		if(count == 0) {
			return getEmptyPageResult(query);
		}
		List<HospitalDataLog> list = mapper.findHospitalDataLog(query);
		PageResult<HospitalDataLog> result = new PageResult<>();
		int limit = query.getLimit();
		int offset = query.getOffset();
		int page = offset%limit == 0? offset/limit : offset/limit + 1;
		result.setLimit(limit);
		result.setPage(page);
		result.setTotal(count);
		result.setData(list);
		return result;
	}

	public PageResult<HospitalDataCheck> searchFindCheckPage(HospitalDataCheck query) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_CHECK);
		HospitalLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalLogMapper.class);
		int count = mapper.countHospitalDataCheck(query);
		if(count == 0) {
			return getEmptyPageResult(query);
		}
		List<HospitalDataCheck> list = mapper.findHospitalDataCheck(query);
		PageResult<HospitalDataCheck> result = new PageResult<>();
		int limit = query.getLimit();
		int offset = query.getOffset();
		int page = offset%limit == 0? offset/limit : offset/limit + 1;
		result.setLimit(limit);
		result.setPage(page);
		result.setTotal(count);
		result.setData(list);
		return result;
	}

}
