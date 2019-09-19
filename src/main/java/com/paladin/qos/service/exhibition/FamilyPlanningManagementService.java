package com.paladin.qos.service.exhibition;

import com.paladin.common.util.DateFormatUtil;
import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.dynamic.mapper.exhibition.FamilyPlanningManagementMapper;
import com.paladin.qos.service.exhibition.vo.DataDemonstrationVO;
import com.paladin.qos.service.exhibition.vo.FamilyPlanningManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <计划生育管理>
 *
 * @author Huangguochen
 * @create 2019/8/28 9:20
 */
@Service
public class FamilyPlanningManagementService extends BaseExhibitionDataAcquire {

	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	private static SimpleDateFormat format = DateFormatUtil.getThreadSafeFormat("yyyy-MM");

	public FamilyPlanningManagementVO getContraceptivemeasuresTotal(AnalysisRequest request) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);

		FamilyPlanningManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(FamilyPlanningManagementMapper.class);
		Date startTime = request.getStartTime();
		Date endTime = request.getEndTime();
		List<Date> dateList = getSearchTimeList(startTime, endTime, false);
		List<DataDemonstrationVO> condomDistributionTotal = getCondomDistributionTotal(dateList, mapper);
		List<DataDemonstrationVO> birthControlPillsTotal = getBirthControlPillsTotal(dateList, mapper);
		FamilyPlanningManagementVO vo = new FamilyPlanningManagementVO();
		vo.setCondomDistribution(condomDistributionTotal);
		vo.setBirthControlPills(birthControlPillsTotal);
		return vo;
	}

	public FamilyPlanningManagementVO getIntrauterineDeviceTotal(AnalysisRequest request) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);

		FamilyPlanningManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(FamilyPlanningManagementMapper.class);
		Date startTime = request.getStartTime();
		Date endTime = request.getEndTime();
		List<Date> dateList = getSearchTimeList(startTime, endTime, false);
		List<DataDemonstrationVO> intrauterineDevicePlacementTotal = getIntrauterineDevicePlacementTotal(dateList, mapper);
		List<DataDemonstrationVO> intrauterineDeviceRemovalTotal = getIntrauterineDeviceRemovalTotal(dateList, mapper);
		FamilyPlanningManagementVO vo = new FamilyPlanningManagementVO();
		vo.setIntrauterineDevicePlacement(intrauterineDevicePlacementTotal);
		vo.setIntrauterineDeviceRemoval(intrauterineDeviceRemovalTotal);
		return vo;
	}

	/**
	 * 功能描述: <避孕套发放数量>
	 * 
	 * @param startTime
	 * @param endTime
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getCondomDistributionTotal(List<Date> dateList, FamilyPlanningManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : dateList) {
			long total = mapper.getCondomDistributionTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			dataDemonstrationVO.setSearchTime(format.format(date));
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <避孕药发放数量>
	 * 
	 * @param startTime
	 * @param endTime
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getBirthControlPillsTotal(List<Date> dateList, FamilyPlanningManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : dateList) {
			long total = mapper.getBirthControlPillsTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			dataDemonstrationVO.setSearchTime(format.format(date));
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <药物流产人数>
	 * 
	 * @param startTime
	 * @param endTime
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getMedicalAbortionTotal(Date startTime, Date endTime) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);

		FamilyPlanningManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(FamilyPlanningManagementMapper.class);
		List<Date> dateList = getSearchTimeList(startTime, endTime, false);
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : dateList) {
			long total = mapper.getMedicalAbortionTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setSearchTime(format.format(date));
			dataDemonstrationVO.setTotal(total);
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <负压吸宫人数>
	 * 
	 * @param startTime
	 * @param endTime
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getNegativePressureSuctionTotal(Date startTime, Date endTime) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);

		FamilyPlanningManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(FamilyPlanningManagementMapper.class);
		List<Date> dateList = getSearchTimeList(startTime, endTime, false);
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : dateList) {
			long total = mapper.getNegativePressureSuctionTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			dataDemonstrationVO.setSearchTime(format.format(date));
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <宫内节育器放置>
	 * 
	 * @param startTime
	 * @param endTime
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getIntrauterineDevicePlacementTotal(List<Date> dateList, FamilyPlanningManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : dateList) {
			long total = mapper.getIntrauterineDevicePlacementTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			dataDemonstrationVO.setSearchTime(format.format(date));
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <宫内节育器取出>
	 * 
	 * @param startTime
	 * @param endTime
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getIntrauterineDeviceRemovalTotal(List<Date> dateList, FamilyPlanningManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : dateList) {
			long total = mapper.getIntrauterineDeviceRemovalTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setSearchTime(format.format(date));
			dataDemonstrationVO.setTotal(total);
			list.add(dataDemonstrationVO);
		}
		return list;
	}

}
