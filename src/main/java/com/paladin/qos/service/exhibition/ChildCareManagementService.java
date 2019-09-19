package com.paladin.qos.service.exhibition;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.dynamic.DSConstant;
import com.paladin.qos.dynamic.mapper.exhibition.ChildCareManagementMapper;
import com.paladin.qos.dynamic.model.exhibition.ChildCareOrgData;
import com.paladin.qos.dynamic.model.exhibition.ChildHealthCheckup;
import com.paladin.qos.dynamic.model.exhibition.InfantCongenitalHeartDisease;
import com.paladin.qos.dynamic.model.exhibition.InfantVision;
import com.paladin.qos.service.exhibition.vo.ChildCareManagementVO;
import com.paladin.qos.service.exhibition.vo.DataDemonstrationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <儿童保健管理>
 *
 * @author Huangguochen
 * @create 2019/8/28 9:20
 */
@Service
public class ChildCareManagementService extends BaseExhibitionDataAcquire {

	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public ChildCareManagementVO searchAllNumberData(AnalysisRequest request) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);

		ChildCareManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(ChildCareManagementMapper.class);
		Date startTime = request.getStartTime();
		Date endTime = request.getEndTime();
		List<Date> defaultDateList = getSearchTimeList(startTime, endTime, true);
		ChildCareManagementVO vo = new ChildCareManagementVO();
		List<DataDemonstrationVO> childCardTotal = getChildCardTotal(defaultDateList, mapper);
		List<DataDemonstrationVO> neonatalDiseaseScreeningTotal = getNeonatalDiseaseScreeningTotal(defaultDateList, mapper);
		List<DataDemonstrationVO> neonatalBirthDefectsTotal = getNeonatalBirthDefectsTotal(defaultDateList, mapper);
		List<DataDemonstrationVO> infantDeathTotal = getInfantDeathTotal(defaultDateList, mapper);
		List<DataDemonstrationVO> childHealthCheckTotal = getChildHealthCheckTotal(defaultDateList, mapper);
		vo.setInfantDeath(infantDeathTotal);
		vo.setNeonatalBirthDefects(neonatalBirthDefectsTotal);
		vo.setNeonatalDiseaseScreening(neonatalDiseaseScreeningTotal);
		vo.setChildCard(childCardTotal);
		vo.setChildHealthCheck(childHealthCheckTotal);
		return vo;
	}

	public ChildCareManagementVO getNewbornChildbirthTotal(AnalysisRequest request) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);

		ChildCareManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(ChildCareManagementMapper.class);
		Date startTime = request.getStartTime();
		Date endTime = request.getEndTime();
		List<Date> defaultDateList = getSearchTimeList(startTime, endTime, true);
		List<DataDemonstrationVO> maleNewbornChildbirthTotal = getMaleNewbornChildbirthTotal(defaultDateList, mapper);
		List<DataDemonstrationVO> femaleNewbornChildbirthTotal = getFemaleNewbornChildbirthTotal(defaultDateList, mapper);
		List<DataDemonstrationVO> newbornHearingTotal = getNewbornHearingTotal(defaultDateList, mapper);
		ChildCareManagementVO vo = new ChildCareManagementVO();
		vo.setMaleNewbornChildbirth(maleNewbornChildbirthTotal);
		vo.setFemaleNewbornChildbirth(femaleNewbornChildbirthTotal);
		vo.setNewbornHearing(newbornHearingTotal);
		return vo;
	}

	/**
	 * 功能描述: <男性新生儿分娩数>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getMaleNewbornChildbirthTotal(List<Date> defaultDateList, ChildCareManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : defaultDateList) {
			long total = mapper.getMaleNewbornChildbirthTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <女性新生儿分娩数>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getFemaleNewbornChildbirthTotal(List<Date> defaultDateList, ChildCareManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : defaultDateList) {
			long total = mapper.getFemaleNewbornChildbirthTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <儿童建卡人数>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getChildCardTotal(List<Date> defaultDateList, ChildCareManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : defaultDateList) {
			long total = mapper.getChildCardTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <新生儿疾病筛查人数>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getNeonatalDiseaseScreeningTotal(List<Date> defaultDateList, ChildCareManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : defaultDateList) {
			long total = mapper.getNeonatalDiseaseScreeningTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <新生儿出生缺陷人数>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getNeonatalBirthDefectsTotal(List<Date> defaultDateList, ChildCareManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : defaultDateList) {
			long total = mapper.getNeonatalBirthDefectsTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <婴幼儿死亡人数>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getInfantDeathTotal(List<Date> defaultDateList, ChildCareManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : defaultDateList) {
			long total = mapper.getInfantDeathTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <儿童入园健康检查数>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getChildHealthCheckTotal(List<Date> defaultDateList, ChildCareManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : defaultDateList) {
			long total = mapper.getChildHealthCheckTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <婴幼儿先天性心脏病>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<InfantCongenitalHeartDisease> getInfantCongenitalHeartDisease(Date startDate, Date endDate) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);
		ChildCareManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(ChildCareManagementMapper.class);
		return mapper.getInfantCongenitalHeartDisease(checkStartTime(startDate), endDate);
	}

	/**
	 * 功能描述: <儿童健康体检>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<ChildHealthCheckup> getChildHealthCheckup(Date startDate, Date endDate) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);
		ChildCareManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(ChildCareManagementMapper.class);
		return mapper.getChildHealthCheckup(checkStartTime(startDate), endDate);
	}

	/**
	 * 功能描述: <婴幼儿视力>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<InfantVision> getInfantVisionTotal(Date startDate, Date endDate) {
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);
		ChildCareManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(ChildCareManagementMapper.class);
		return mapper.getInfantVisionTotal(checkStartTime(startDate), endDate);
	}

	/**
	 * 功能描述: <新生儿听力人数>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<DataDemonstrationVO> getNewbornHearingTotal(List<Date> defaultDateList, ChildCareManagementMapper mapper) {
		List<DataDemonstrationVO> list = new ArrayList<>(12);
		DataDemonstrationVO dataDemonstrationVO;
		for (Date date : defaultDateList) {
			long total = mapper.getNewbornHearingTotal(date);
			dataDemonstrationVO = new DataDemonstrationVO();
			dataDemonstrationVO.setTotal(total);
			list.add(dataDemonstrationVO);
		}
		return list;
	}

	/**
	 * 功能描述: <儿童保健按机构统计>
	 * 
	 * @param defaultDateList
	 * @return List<DataDemonstrationVO>
	 */
	public List<ChildCareOrgData> getChildCareDataByOrg(Date startDate, Date endDate) {
		Date date = checkStartTime(startDate);
		if (endDate != null && date.after(endDate)) {
			throw new BusinessException("请选择正确的日期区间");
		}
		sqlSessionContainer.setCurrentDataSource(DSConstant.DS_FUYOU);
		ChildCareManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(ChildCareManagementMapper.class);
		return mapper.getChildCareDataByOrg(date, endDate);
	}

}
