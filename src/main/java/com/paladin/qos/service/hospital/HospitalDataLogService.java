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


@Service
public class HospitalDataLogService extends ServiceSupport<CountReferral> {

	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public PageResult<HospitalDataLog> searchFindPage(HospitalDataLog query) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_AADATA);
		HospitalLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalLogMapper.class);
		Page<HospitalDataLog> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		mapper.findHospitalDataLog(query);
		return new PageResult<>(page);
	}

	public PageResult<HospitalDataCheck> searchFindCheckPage(HospitalDataCheck query) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_CHECK);
		HospitalLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalLogMapper.class);
		Page<HospitalDataCheck> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		mapper.findHospitalDataCheck(query);
		return new PageResult<>(page);
	}

}
