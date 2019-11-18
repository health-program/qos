package com.paladin.qos.service.hospital;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.common.controller.syst.SysControllerLog;
import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.spring.SpringBeanHelper;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.dynamic.mapper.HospitalLogMapper;
import com.paladin.qos.model.count.CountReferral;
import com.paladin.qos.model.hospitalData.HospitalDataCheck;
import com.paladin.qos.model.hospitalData.HospitalDataLog;
import com.paladin.qos.model.hospitalData.HospitalDataRule;


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



	public Object searchRulePage(HospitalDataRule query) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.Local);
		HospitalLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalLogMapper.class);
		Page<HospitalDataRule> page = PageHelper.offsetPage(query.getOffset(),query.getLimit());
		mapper.findRulePage(query);
		return new PageResult<>(page);
	}

	public int close(String id) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.Local);
		HospitalLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalLogMapper.class);
		return mapper.close(id);
	}

	public int open(String id) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.Local);
		HospitalLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalLogMapper.class);
		return mapper.open(id);
	}

	public int save(HospitalDataRule hospitalDataRule) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.Local);
		HospitalLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalLogMapper.class);
		hospitalDataRule.setCreateTime(new Date());
		hospitalDataRule.setUpdateTime(new Date());
		return mapper.save(hospitalDataRule);
	}

	public HospitalDataRule getHospitalDataRule(String id) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.Local);
		HospitalLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalLogMapper.class);
		return mapper.getHospitalDataRule(id);
	}

	public int updateHospitalDataRule(HospitalDataRule hospitalDataRule) {

		sqlSessionContainer.setCurrentDataSource(DSConstant.Local);
		HospitalLogMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(HospitalLogMapper.class);
		hospitalDataRule.setUpdateTime(new Date());
		return mapper.updateHospitalDataRule(hospitalDataRule);
	}
	

}
