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
	private SqlSessionContainer sqlSessionContainer;

	public List<PhysicalManagementVO> findPhysical(String managedCenter, Date startDate, Date endDate) {
		sqlSessionContainer.setCurrentDataSource("sqlserver");
		PhysicalManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(PhysicalManagementMapper.class);

		List<PhysicalManagementVO> physicalManagementVOArrayList = new ArrayList<>();
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
		return physicalManagementVOArrayList;
	}

	private String getManagedCenterName(String managedCode) {

		// todo 根据辖区code找对应辖区名称
		return "";
	}

	private Long getPeopleNumber(String managedCode) {
		// todo 根据辖区code找对应辖区人数

		return null;
	}

}