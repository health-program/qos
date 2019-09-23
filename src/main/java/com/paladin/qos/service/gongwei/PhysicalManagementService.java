package com.paladin.qos.service.gongwei;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.mapper.gongwei.PhysicalManagementMapper;
import com.paladin.qos.service.exhibition.BaseExhibitionDataAcquire;
import com.paladin.qos.service.gongwei.vo.PhysicalManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PhysicalManagementService extends BaseExhibitionDataAcquire {

	@Autowired
	private PhysicalManagementMapper physicalManagementMapper;

	public static final String EVENT_ID = "22003";

	public PhysicalManagementVO findPhysical(String managedCenter, Date startDate, Date endDate) {
//		List<PhysicalManagementVO> physicalManagementVOArrayList = new ArrayList<>();
		// List<Archives> archivesList = mapper.getArchivesTotal(startDate, endDate);
		// ArchivesManagementVO archivesManagementVO = null;
		// for (Archives archives : archivesList) {
		// if (!StringUtils.isEmpty(archives.getManagedCenter())){
		// archivesManagementVO.setManagedCenter(getManagedCenterName(archives.getManagedCenter()));
		// archivesManagementVO.setPeopleNumber(getPeopleNumber(archives.getManagedCenter()));
		// }
		// archivesManagementVO.setActiveArchivesNumber(archives.getActiveArchivesNumber());
		// archivesManagementVO.setPublicArchivesNumber(archives.getPublicArchivesNumber());
		// archivesManagementVOList.add(archivesManagementVO);
		// }
		return physicalManagementMapper.getTotalPhysical(managedCenter,EVENT_ID);
	}



}
