package com.paladin.qos.service.epidemic;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.paladin.common.core.container.ConstantsContainer;
import com.paladin.framework.common.ExcelImportResult;
import com.paladin.framework.common.ExcelImportResult.ExcelImportError;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.excel.DefaultSheet;
import com.paladin.framework.excel.EnumContainer;
import com.paladin.framework.excel.read.DefaultReadColumn;
import com.paladin.framework.excel.read.ExcelReadException;
import com.paladin.framework.excel.read.ExcelReader;
import com.paladin.framework.excel.read.ReadColumn;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.qos.mapper.epidemic.EpidemicSituationMapper;
import com.paladin.qos.mapper.school.OrgSchoolNameMapper;
import com.paladin.qos.model.epidemic.EpidemicSituation;
import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.model.school.OrgSchoolPeople;
import com.paladin.qos.service.epidemic.dto.EpidemicSituationDTO;
import com.paladin.qos.service.epidemic.dto.EpidemicSituationQueryDTO;
import com.paladin.qos.service.epidemic.dto.ExcelEpidemicSituation;
import com.paladin.qos.service.epidemic.vo.DataEpidemicSituationVO;
import com.paladin.qos.service.epidemic.vo.EpidemicSituationVO;
import com.paladin.qos.service.school.OrgSchoolPeopleService;
import com.paladin.qos.service.school.OrgSchoolService;
import com.paladin.qos.service.school.dto.OrgSchoolCountsQuery;
import com.paladin.qos.service.school.vo.OrgSchoolCountsGroupByNatureVO;
import com.paladin.qos.service.school.vo.OrgSchoolEpidemicRateVO;
import com.paladin.qos.service.school.vo.SchoolNameVO;

/**
 * @author 黄伟华
 * @version 2019年6月11日 下午3:56:28
 */
@Service
public class EpidemicSituationService extends ServiceSupport<EpidemicSituation> {

    @Autowired
    private EpidemicSituationMapper epidemicSituationMapper;

    @Autowired
    private OrgSchoolNameMapper orgSchoolNameMapper;
    @Autowired
    private OrgSchoolPeopleService orgSchoolPeopleService;
    @Autowired
    private OrgSchoolService orgSchoolService;

    public PageResult<EpidemicSituationVO> searchFindPage(
	    EpidemicSituationQueryDTO query) {
	Page<EpidemicSituationVO> page = PageHelper.offsetPage(
		query.getOffset(), query.getLimit());
	epidemicSituationMapper.searchFindPage(query);
	return new PageResult<>(page);
    }

    public List<DataEpidemicSituationVO> dataTraceabilityRate(
	    EpidemicSituationQueryDTO query) {
	return epidemicSituationMapper.dataTraceabilityRate(query);
    }

    public int updateEpidemic(EpidemicSituationDTO dto) {
	String id = dto.getId();

	if (StringUtil.isEmpty(id)) {
	    throw new BusinessException("找不到更新的疫情信息");
	}
	// 验证班级id
	OrgSchoolPeople orgSchoolPeople = orgSchoolPeopleService.get(dto
		.getGrade());
	if (orgSchoolPeople == null) {
	    throw new BusinessException("只可以选择班级项！");
	}
	OrgSchool orgSchool = orgSchoolService.get(orgSchoolPeople
		.getSchoolId());
	if (orgSchool == null) {
	    throw new BusinessException("该学校信息不存在，请确认！");
	}
	if (!StringUtils.equals(dto.getSchoolYear(), orgSchool.getSchoolYear())) {
	    throw new BusinessException("该学校该学年没有该班级，请确认!");
	}
	dto.setIncidentUnit(orgSchool.getParentSchoolId());
	EpidemicSituation es = get(id);

	if (es == null) {
	    throw new BusinessException("找不到更新的疫情信息");
	}

	SimpleBeanCopyUtil.simpleCopy(dto, es);

	return update(es);
    }

    private static final List<ReadColumn> epidemicImportColumns = DefaultReadColumn
	    .createReadColumn(ExcelEpidemicSituation.class,
		    new EnumContainer() {

			@Override
			public String getEnumName(String type, String key) {
			    return ConstantsContainer.getTypeValue(type, key);
			}

			@Override
			public String getEnumKey(String type, String name) {
			    return ConstantsContainer.getTypeKey(type, name);
			}
		    });

    /**
     * 导入学校疫情信息
     * 
     * @param excelInputStream
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("resource")
    @Transactional
    public ExcelImportResult importEpidemic(InputStream excelInputStream) {
	XSSFWorkbook workbook;
	try {
	    workbook = new XSSFWorkbook(excelInputStream);
	} catch (IOException e1) {
	    throw new BusinessException("导入异常");
	}

	ExcelReader<ExcelEpidemicSituation> reader = new ExcelReader<>(
		ExcelEpidemicSituation.class, epidemicImportColumns,
		new DefaultSheet(workbook.getSheetAt(0)), 1);
	List<ExcelImportError> errors = new ArrayList<>();

	int i = 0;

	while (reader.hasNext()) {
	    i++;
	    if (i > 500) {
		break;
	    }
	    ExcelEpidemicSituation excelEpidemicSituation = null;
	    try {
		excelEpidemicSituation = reader.readRow();
	    } catch (ExcelReadException e) {
		errors.add(new ExcelImportError(i, e));
		continue;
	    }
	    EpidemicSituation situation = new EpidemicSituation();
	    SimpleBeanCopyUtil.simpleCopy(excelEpidemicSituation, situation);

	    String incidentUnit = excelEpidemicSituation.getIncidentUnit();

	    if (StringUtil.isEmpty(incidentUnit)) {
		errors.add(new ExcelImportError(i, "事发单位名称不能为空"));
		continue;
	    }

	    SchoolNameVO name = orgSchoolNameMapper.getSchoolName(incidentUnit);

	    if (name == null) {
		errors.add(new ExcelImportError(i, "" + incidentUnit
			+ "学校名称不存在"));
		continue;
	    }

	    String id = UUIDUtil.createUUID();
	    situation.setId(id);
	    situation.setIncidentUnit(name.getId());
	    try {
		save(situation);
	    } catch (BusinessException e) {
		errors.add(new ExcelImportError(i, e.getMessage()));
		continue;
	    } catch (Exception e) {
		errors.add(new ExcelImportError(i, "保存失败"));
		continue;
	    }

	}
	return new ExcelImportResult(i, errors);
    }

    /**
     * 按学校统计疫情次数
     * 
     * @param query
     * @return
     */
    public List<OrgSchoolCountsGroupByNatureVO> epidemicCountsGroupByUnit(
	    OrgSchoolCountsQuery query) {
	return epidemicSituationMapper.epidemicCountsGroupByUnit(query);
    }

    /**
     * 按学校统计疫情人数
     * 
     * @param query
     * @return
     */
    public List<OrgSchoolCountsGroupByNatureVO> epidemicPeopleCountsGroupByUnit(
	    OrgSchoolCountsQuery query) {
	return epidemicSituationMapper.epidemicPeopleCountsGroupByUnit(query);
    }

    public List<OrgSchoolEpidemicRateVO> queryEpidemicRatesByAffiliation(
	    OrgSchoolCountsQuery query) {

	return epidemicSituationMapper.queryEpidemicRatesByAffiliation(query);
    }

    public List<OrgSchoolEpidemicRateVO> queryEpidemicRatesByNature(
	    OrgSchoolCountsQuery query) {

	return epidemicSituationMapper.queryEpidemicRatesByNature(query);
    }

}
