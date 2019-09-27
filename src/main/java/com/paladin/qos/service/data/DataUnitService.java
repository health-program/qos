package com.paladin.qos.service.data;

import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.mapper.data.DataUnitMapper;
import com.paladin.qos.service.analysis.data.AnalysisMonth;
import com.paladin.qos.service.data.vo.BedReportVO;
import com.paladin.qos.service.data.vo.DataUnitVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.qos.model.data.DataUnit;
import com.paladin.framework.core.ServiceSupport;

import java.util.Date;
import java.util.List;

@Service
public class DataUnitService extends ServiceSupport<DataUnit> {

	@Autowired
	private DataUnitMapper dataUnitMapper;

	public AnalysisMonth  getBedReportByQuery(String unitId, String eventId, Date startDate, Date endDate) {
		return dataUnitMapper.getBedReportByQuery(eventId,unitId, TimeUtil.getSerialNumberByDay(startDate),TimeUtil.getSerialNumberByDay(endDate));
	}

	public List<DataUnitVO> selectData() {
		QosUserSession session = QosUserSession.getCurrentUserSession();
		String[] ids = null;
		if (!session.isAdminRoleLevel()) {
			String[] agencyIds = session.getAgencyIds();
			ids = agencyIds;
		}
		return dataUnitMapper.selectData(ids);
	}
}