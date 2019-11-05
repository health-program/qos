package com.paladin.qos.service.hospitalData;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.mapper.hospitalData.HospitalDataMapper;
import com.paladin.qos.model.count.CountReferral;
import com.paladin.qos.model.hospitalData.HospitalDataCheck;
import com.paladin.qos.model.hospitalData.HospitalDataLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HospitalDataLogService extends ServiceSupport<CountReferral>{
	@Autowired
    private HospitalDataMapper hospitalDataMapper;
	
	 public PageResult<HospitalDataLog> searchFindPage(HospitalDataLog query) {
	        Page<HospitalDataLog> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
	        QosUserSession userSession=QosUserSession.getCurrentUserSession();
	        if (!userSession.isAdminRoleLevel()){
	            String[] agencyIds = userSession.getAgencyIds();
	            query.setUnitIds(agencyIds);
	        }
	        hospitalDataMapper.findHospitalDataLog(query);
	        return new PageResult<>(page);
	    }

	public PageResult<HospitalDataCheck> searchFindCheckPage(HospitalDataCheck query) {

		Page<HospitalDataCheck> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        QosUserSession userSession=QosUserSession.getCurrentUserSession();
        if (!userSession.isAdminRoleLevel()){
            String[] agencyIds = userSession.getAgencyIds();
            query.setUnitIds(agencyIds);
        }
        hospitalDataMapper.findHospitalDataCheck(query);
        return new PageResult<>(page);
	}

	public List<String> selectCheckType() {

		return hospitalDataMapper.selectCheckType();
	}

}
