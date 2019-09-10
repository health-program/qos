package com.paladin.qos.service.analysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.framework.core.exception.BusinessException;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Unit;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.mapper.analysis.AnalysisMapper;
import com.paladin.qos.service.analysis.data.AnalysisMonth;
import com.paladin.qos.service.analysis.data.AnalysisUnit;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.analysis.data.DataPointDay;
import com.paladin.qos.service.analysis.data.DataPointMonth;
import com.paladin.qos.service.analysis.data.DataPointWeekMonth;
import com.paladin.qos.service.analysis.data.DataPointWeekYear;
import com.paladin.qos.service.analysis.data.DataPointYear;
import com.paladin.qos.service.analysis.data.DataResult;
import com.paladin.qos.service.analysis.data.ValidateResult;
import com.paladin.qos.service.analysis.data.DataPointUnit;

@Service
public class AnalysisService {

	public final static int DATA_TYPE_DAY = 1;
	public final static int DATA_TYPE_MONTH = 2;
	public final static int DATA_TYPE_YEAR = 3;
	public final static int DATA_TYPE_WEEK_MONTH = 4;
	public final static int DATA_TYPE_WEEK_YEAR = 5;

	@Autowired
	private AnalysisMapper analysisMapper;

	// ----------------------------------->查找某事件某医院在时间段内按时间粒度统计数据<-----------------------------------

	public DataPointUnit<DataPointDay> getDataPointUnitOfDay(String eventId, String unitId, Date startDate, Date endDate) {
		List<DataPointDay> points = analysisMapper.getDataPointOfDay(eventId, unitId, TimeUtil.getSerialNumberByDay(startDate),
				TimeUtil.getSerialNumberByDay(endDate));
		return new DataPointUnit<DataPointDay>(unitId, points);
	}

	public DataPointUnit<DataPointMonth> getDataPointUnitOfMonth(String eventId, String unitId, Date startDate, Date endDate) {
		List<DataPointMonth> points = analysisMapper.getDataPointOfMonth(eventId, unitId, TimeUtil.getSerialNumberByDay(startDate),
				TimeUtil.getSerialNumberByDay(endDate));
		return new DataPointUnit<DataPointMonth>(unitId, points);
	}

	public DataPointUnit<DataPointYear> getDataPointUnitOfYear(String eventId, String unitId, int startYear, int endYear) {
		List<DataPointYear> points = analysisMapper.getDataPointOfYear(eventId, unitId, startYear, endYear);
		return new DataPointUnit<DataPointYear>(unitId, points);
	}

	public DataPointUnit<DataPointWeekYear> getDataPointUnitOfWeekYear(String eventId, String unitId, int startYear, int endYear) {
		List<DataPointWeekYear> points = analysisMapper.getDataPointOfWeekYear(eventId, unitId, startYear, endYear);
		return new DataPointUnit<DataPointWeekYear>(unitId, points);
	}

	public DataPointUnit<DataPointWeekMonth> getDataPointUnitOfWeekMonth(String eventId, String unitId, Date startDate, Date endDate) {
		List<DataPointWeekMonth> points = analysisMapper.getDataPointOfWeekMonth(eventId, unitId, TimeUtil.getSerialNumberByDay(startDate),
				TimeUtil.getSerialNumberByDay(endDate));
		return new DataPointUnit<DataPointWeekMonth>(unitId, points);
	}

	// ----------------------------------->查找某事件单个或所有医院在时间段内按时间粒度统计<-----------------------------------

	/** 获取医院，如果为空则全部 */
	private List<Unit> getUnits(List<String> unitIds) {
		List<Unit> units = null;

		if (unitIds == null) {
			// 获取所有
			units = DataConstantContainer.getUnitList();
		} else {
			units = new ArrayList<>();
			for (String unitId : unitIds) {
				Unit unit = DataConstantContainer.getUnit(unitId);
				if (unit != null) {
					units.add(unit);
				}
			}
		}

		return units;
	}

	// 查找多个或所有医院时，可改为在SQL中处理，暂时为遍历医院一个个查找

	public DataResult<DataPointDay> getDataOfDay(String eventId, List<String> unitIds, Date startDate, Date endDate) {
		List<Unit> units = getUnits(unitIds);
		List<DataPointUnit<DataPointDay>> unitPoints = new ArrayList<>(units.size());
		for (Unit unit : units) {
			unitPoints.add(getDataPointUnitOfDay(eventId, unit.getId(), startDate, endDate));
		}
		return new DataResult<DataPointDay>(eventId, DATA_TYPE_DAY, unitPoints);
	}

	public DataResult<DataPointMonth> getDataOfMonth(String eventId, List<String> unitIds, Date startDate, Date endDate) {
		List<Unit> units = getUnits(unitIds);
		List<DataPointUnit<DataPointMonth>> unitPoints = new ArrayList<>(units.size());
		for (Unit unit : units) {
			unitPoints.add(getDataPointUnitOfMonth(eventId, unit.getId(), startDate, endDate));
		}
		return new DataResult<DataPointMonth>(eventId, DATA_TYPE_MONTH, unitPoints);
	}

	public DataResult<DataPointYear> getDataOfYear(String eventId, List<String> unitIds, int startYear, int endYear) {
		List<Unit> units = getUnits(unitIds);
		List<DataPointUnit<DataPointYear>> unitPoints = new ArrayList<>(units.size());
		for (Unit unit : units) {
			unitPoints.add(getDataPointUnitOfYear(eventId, unit.getId(), startYear, endYear));
		}
		return new DataResult<DataPointYear>(eventId, DATA_TYPE_YEAR, unitPoints);
	}

	public DataResult<DataPointWeekYear> getDataOfWeekYear(String eventId, List<String> unitIds, int startYear, int endYear) {
		List<Unit> units = getUnits(unitIds);
		List<DataPointUnit<DataPointWeekYear>> unitPoints = new ArrayList<>(units.size());
		for (Unit unit : units) {
			unitPoints.add(getDataPointUnitOfWeekYear(eventId, unit.getId(), startYear, endYear));
		}
		return new DataResult<DataPointWeekYear>(eventId, DATA_TYPE_WEEK_YEAR, unitPoints);
	}

	public DataResult<DataPointWeekMonth> getDataOfWeekMonth(String eventId, List<String> unitIds, Date startDate, Date endDate) {
		List<Unit> units = getUnits(unitIds);
		List<DataPointUnit<DataPointWeekMonth>> unitPoints = new ArrayList<>(units.size());
		for (Unit unit : units) {
			unitPoints.add(getDataPointUnitOfWeekMonth(eventId, unit.getId(), startDate, endDate));
		}
		return new DataResult<DataPointWeekMonth>(eventId, DATA_TYPE_WEEK_MONTH, unitPoints);
	}

	/**
	 * 按医院分组统计时间段内事件
	 * 
	 * @param eventId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<AnalysisUnit> getAnalysisResultByUnit(String eventId, Date startDate, Date endDate) {
		return analysisMapper.getAnalysisResultGroupByUnit(eventId, TimeUtil.getSerialNumberByDay(startDate), TimeUtil.getSerialNumberByDay(endDate));
	}

	/**
	 * 按时间分组统计时间段内所有医院事件
	 * 
	 * @param eventId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<AnalysisMonth> getAnalysisResultByMonth(String eventId, Date startDate, Date endDate) {
		return analysisMapper.getAnalysisResultGroupByMonth(eventId, TimeUtil.getSerialNumberByDay(startDate), TimeUtil.getSerialNumberByDay(endDate));
	}

	/**
	 * 
	 * 按时间分组统计时间段内某医院事件
	 * 
	 * @param eventId
	 * @param unitId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<AnalysisMonth> getAnalysisResultByMonth(String eventId, String unitId, Date startDate, Date endDate) {
		return analysisMapper.getAnalysisResultOfUnitGroupByMonth(eventId, unitId, TimeUtil.getSerialNumberByDay(startDate),
				TimeUtil.getSerialNumberByDay(endDate));
	}

	/**
	 * 按医院获取时间段内样本数之和
	 * 
	 * @param eventId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<DataCountUnit> countTotalNumByUnit(String eventId, Date startDate, Date endDate) {
		return analysisMapper.countTotalNumByUnit(eventId, TimeUtil.getSerialNumberByDay(startDate), TimeUtil.getSerialNumberByDay(endDate));
	}

	/**
	 * 按医院获取时间段内事件数之和
	 * 
	 * @param eventId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<DataCountUnit> countEventNumByUnit(String eventId, Date startDate, Date endDate) {
		return analysisMapper.countEventNumByUnit(eventId, TimeUtil.getSerialNumberByDay(startDate), TimeUtil.getSerialNumberByDay(endDate));
	}

	/**
	 * 验证处理的数据
	 * 
	 * @param eventId
	 * @param unitId
	 * @return
	 */
	public ValidateResult validateProcessedData(String eventId, String unitId) {
		List<Integer> nums = analysisMapper.getSerialNumbers(eventId, unitId);
		ValidateResult result = new ValidateResult();
		result.setEventId(eventId);
		result.setUnitId(unitId);

		if (nums != null && nums.size() > 0) {
			int size = nums.size();
			int first = nums.get(0);
			int last = nums.get(size - 1);

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date firstDay;
			Date lastDay;
			try {
				firstDay = format.parse(String.valueOf(first));
				lastDay = format.parse(String.valueOf(last));
			} catch (ParseException e) {
				throw new BusinessException(e);
			}

			List<Integer> losts = new ArrayList<>();

			HashSet<Integer> set = new HashSet<>();
			for (Integer num : nums) {
				set.add(num);
			}

			Calendar c = Calendar.getInstance();
			c.setTime(firstDay);

			long lastTime = lastDay.getTime();

			do {
				c.add(Calendar.DAY_OF_MONTH, 1);
				int sn = TimeUtil.getSerialNumberByDay(c);
				if (!set.contains(sn)) {
					losts.add(sn);
				}
			} while (c.getTimeInMillis() < lastTime);

			result.setFirstDay(first);
			result.setLastDay(last);
			result.setLostDays(losts);
		}
		
		return result;
	}

}
