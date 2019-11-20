package com.paladin.qos.service.school;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.common.core.container.ConstantsContainer;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.ExcelImportResult;
import com.paladin.framework.common.ExcelImportResult.ExcelImportError;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.excel.DefaultSheet;
import com.paladin.framework.excel.EnumContainer;
import com.paladin.framework.excel.read.DefaultReadColumn;
import com.paladin.framework.excel.read.ExcelReadException;
import com.paladin.framework.excel.read.ExcelReader;
import com.paladin.framework.excel.read.ReadColumn;
import com.paladin.framework.utils.StringUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.qos.mapper.school.OrgSchoolMapper;
import com.paladin.qos.mapper.school.OrgSchoolNameMapper;
import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.model.school.OrgSchoolName;
import com.paladin.qos.model.school.OrgSchoolPeople;
import com.paladin.qos.service.school.dto.ExcelOrgSchool;
import com.paladin.qos.service.school.dto.OrgSchoolCountsQuery;
import com.paladin.qos.service.school.dto.OrgSchoolDTO;
import com.paladin.qos.service.school.dto.OrgSchoolPeopleDTO;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import com.paladin.qos.service.school.vo.OrgSchoolCountsGroupByNatureVO;
import com.paladin.qos.service.school.vo.OrgSchoolDoctorCountVO;
import com.paladin.qos.service.school.vo.OrgSchoolPersonnelCountVO;
import com.paladin.qos.service.school.vo.OrgSchoolVO;
import com.paladin.qos.service.school.vo.SchoolNameVO;

@Service
public class OrgSchoolService extends ServiceSupport<OrgSchool> {
    
    @Autowired
    private OrgSchoolPeopleService orgSchoolPeopleService;
    
    @Autowired
    private OrgSchoolMapper orgSchoolMapper;
    
    @Autowired
    private OrgSchoolNameMapper orgSchoolNameMapper;
    @Autowired
    private OrgSchoolNameService orgSchoolNameService;
    
    public PageResult<OrgSchoolVO> searchFindPage(OrgSchoolQuery query) {
	Page<OrgSchoolVO> page = PageHelper.offsetPage(query.getOffset(),query.getLimit());
	orgSchoolMapper.findSchool(query);
	return new PageResult<>(page);
    }
    
    public OrgSchool orgSchoolDetail(String id, String schoolYear){
    	List<Condition> conditions=new ArrayList<Condition>();
    	conditions.add(new Condition(OrgSchool.PARENT_SCHOOL_ID,QueryType.EQUAL, id));
    	conditions.add(new Condition(OrgSchool.SCHOOL_YEAR,QueryType.EQUAL, schoolYear));
    	return searchOne(conditions);
    }
    
    public OrgSchoolVO getSchool(String id){
	if(StringUtil.isEmpty(id)){
	    throw new BusinessException("找不到对应学校信息");
	}
	
	OrgSchool school = get(id);
	if (school == null) {
		throw new BusinessException("找不到对应学校信息");
	}
	OrgSchoolVO vo = new OrgSchoolVO();
	SimpleBeanCopyUtil.simpleCopy(school, vo);
	List<OrgSchoolPeople> people = orgSchoolPeopleService
		.findSchoolPeople(id).stream()
		.sorted(Comparator.comparing(OrgSchoolPeople::getGrade))
		.collect(Collectors.toList());
	vo.setPeople(people);
	return vo;
    }
    
    @Transactional
    public int schoolSave(OrgSchoolDTO dto){
	String schoolId = dto.getId();
	
	if (schoolId == null || schoolId.length() == 0) {
	    schoolId = UUIDUtil.createUUID();
		dto.setId(schoolId);
	}
	OrgSchool school = new OrgSchool();
	SimpleBeanCopyUtil.simpleCopy(dto, school);
	//修改时验证学校信息是否已添加
	OrgSchool orgSchoolDetail = this.orgSchoolDetail(dto.getParentSchoolId(),dto.getSchoolYear());
	if(orgSchoolDetail!=null){
		throw new BusinessException("该年度学校信息已录入");
	}
	//该学校不是父节点
	List<OrgSchoolName> orgSchoolNameList = orgSchoolNameService.searchAll(new Condition(OrgSchoolName.COLUMN_PARENT_ID, QueryType.EQUAL, school.getParentSchoolId()));
	if(!CollectionUtils.isEmpty(orgSchoolNameList)){
		throw new BusinessException("该学校下有子项，只能添加子项的数据！");
	}
	List<OrgSchoolPeopleDTO> peoples = dto.getPeople();
	if (peoples == null || peoples.size() == 0) {
		throw new BusinessException("人数不能为空");
	}
	for (OrgSchoolPeopleDTO orgSchoolPeopleDTO : peoples) {
	    OrgSchoolPeople people = new OrgSchoolPeople();
	    SimpleBeanCopyUtil.simpleCopy(orgSchoolPeopleDTO, people);
	    List<OrgSchoolPeople> list = orgSchoolPeopleService.getGrade(schoolId,people.getSchoolSection(),people.getGrade(),people.getKlass());
	    if(!CollectionUtils.isEmpty(list)){
	    	throw new BusinessException("该班级["+people.getKlass()+"]信息重复！");
	    }
	    people.setSchoolId(schoolId);
	    orgSchoolPeopleService.save(people);
	}
	int result= save(school);
	this.updateSchoolTotal(schoolId);
	return result;
    }
    
    @Transactional
    public int schoolUpdate(OrgSchoolDTO dto){
	String schoolId = dto.getId();
	if (schoolId == null || schoolId.length() == 0) {
		throw new BusinessException("没有对应需要更新的学校信息");
	}

	OrgSchool school = get(schoolId);
	if (school == null) {
		throw new BusinessException("没有对应需要更新的学校信息");
	}
	//修改时验证学校信息是否已添加
	if(!StringUtils.equals(school.getSchoolYear(), dto.getSchoolYear()) ){
		OrgSchool orgSchoolDetail = this.orgSchoolDetail(dto.getParentSchoolId(),dto.getSchoolYear());
		if(orgSchoolDetail!=null){
			throw new BusinessException("该年度学校信息已录入");
		}
	}

	SimpleBeanCopyUtil.simpleCopy(dto, school);
	//该学校不是父节点
	List<OrgSchoolName> orgSchoolNameList = orgSchoolNameService.searchAll(new Condition(OrgSchoolName.COLUMN_PARENT_ID, QueryType.EQUAL, school.getParentSchoolId()));
	if(!CollectionUtils.isEmpty(orgSchoolNameList)){
		throw new BusinessException("该学校下有子项，只能添加子项的数据！");
	}
	List<OrgSchoolPeopleDTO> dtos = dto.getPeople();
	
	if (dtos == null || dtos.size() == 0) {
		throw new BusinessException("人数不能为空");
	}
	orgSchoolPeopleService.deletePeople(schoolId);
	for (OrgSchoolPeopleDTO orgSchoolPeopleDTO : dtos) {
	    OrgSchoolPeople people = new OrgSchoolPeople();
	    SimpleBeanCopyUtil.simpleCopy(orgSchoolPeopleDTO, people);
	    List<OrgSchoolPeople> list = orgSchoolPeopleService.getGrade(schoolId,people.getSchoolSection(),people.getGrade(),people.getKlass());
	    if(!CollectionUtils.isEmpty(list)){
	    	throw new BusinessException("该班级["+people.getKlass()+"]信息重复！");
	    }
	    people.setSchoolId(schoolId);
	    orgSchoolPeopleService.save(people);
	}
	int result= update(school);
	this.updateSchoolTotal(schoolId);
	return result;
    }
    
    /**
     * 学校学额统计
     * @param query
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<OrgSchoolPersonnelCountVO> schoolPersonnelCount(OrgSchoolQuery query){
	return orgSchoolMapper.schoolPersonnelCount(query);
    }
    
    /**
     * 学校校医配备核查情况统计
     * @param query
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<OrgSchoolDoctorCountVO> schoolDoctorCount(OrgSchoolQuery query){
	return orgSchoolMapper.schoolDoctorCount(query);
    }
    
    @Transactional
    public int deleteSchoolAndPeople(String id){
	removeByPrimaryKey(id);
	return orgSchoolPeopleService.deletePeople(id);
    }
    
    private static final List<ReadColumn> schoolImportColumns = DefaultReadColumn.createReadColumn(ExcelOrgSchool.class, new EnumContainer(){
 	
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
     * 导入学校信息    
     * @param excelInputStream
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("resource")
    @Transactional
    public ExcelImportResult importOrgSchool(InputStream excelInputStream) {
    XSSFWorkbook workbook;
    try {
        workbook = new XSSFWorkbook(excelInputStream);
    } catch (IOException e1) {
        throw new BusinessException("导入异常");
    }
    
    ExcelReader<ExcelOrgSchool> reader = new ExcelReader<>(ExcelOrgSchool.class, schoolImportColumns,new DefaultSheet(workbook.getSheetAt(0)), 2);
    List<ExcelImportError> errors = new ArrayList<>();
    
    int i = 0;
    
	while (reader.hasNext()) {
	    i++;
	    if (i > 500) {
		break;
	    }
	    ExcelOrgSchool excelOrgSchool = null;
	    try {
		excelOrgSchool = reader.readRow();
	    } catch (ExcelReadException e) {
		errors.add(new ExcelImportError(i, e));
		continue;
	    }
	    
	    if(excelOrgSchool == null){
		continue;
	    }

	    String schoolName = excelOrgSchool.getParentSchoolName();

	    if (StringUtil.isEmpty(schoolName)) {
		errors.add(new ExcelImportError(i, "学校名称不能为空"));
		continue;
	    }

	    SchoolNameVO schoolNameVO = orgSchoolNameMapper.getSchoolName(schoolName);
	    
	    if (schoolNameVO == null) {
		errors.add(new ExcelImportError(i, "" + schoolName + ":学校名称不存在"));
		continue;
	    }

	    try {
		OrgSchoolPeople people = new OrgSchoolPeople();
		SimpleBeanCopyUtil.simpleCopy(excelOrgSchool, people);
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("id", schoolNameVO.getId());
		param.put("schoolYear", excelOrgSchool.getSchoolYear());
		OrgSchoolVO schoolVO = orgSchoolMapper.parentSchoolId(param);
		if (schoolVO != null) {
		  List<OrgSchoolPeople> list = orgSchoolPeopleService.getGrade(schoolVO.getId(),people.getSchoolSection(),people.getGrade(),people.getKlass());
		    if(!CollectionUtils.isEmpty(list)){
			errors.add(new ExcelImportError(i, "年级已经存在"));
			continue;
		    }
		    
		    String id = UUIDUtil.createUUID();
		    people.setId(id);
		    people.setTotal(excelOrgSchool.getGradeTotal());
		    people.setSchoolId(schoolVO.getId());
		    orgSchoolPeopleService.save(people);
		    //更新学校总人数
		    this.updateSchoolTotal(schoolVO.getId());
		} else {
		    String OrgSchoolId = UUIDUtil.createUUID();
		    OrgSchool orgSchool = new OrgSchool();
		    SimpleBeanCopyUtil.simpleCopy(excelOrgSchool, orgSchool);
		    orgSchool.setId(OrgSchoolId);
		    orgSchool.setParentSchoolId(schoolNameVO.getId());
		    save(orgSchool);
		    String peopleId = UUIDUtil.createUUID();
		    people.setId(peopleId);
		    people.setSchoolId(OrgSchoolId);
		    people.setTotal(excelOrgSchool.getGradeTotal());
		    orgSchoolPeopleService.save(people);
		    //更新学校总人数
		    this.updateSchoolTotal(OrgSchoolId);
		}

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

    @Transactional
    public void updateSchoolTotal(String orgSchoolId) {
		if(StringUtil.isEmpty(orgSchoolId)){
			return;
		}
    	orgSchoolMapper.updateSchoolTotal(orgSchoolId);
	}

	/**
     * 按学校性质，统计学校数量
     * @param query
     * @return
     */
	public List<OrgSchoolCountsGroupByNatureVO> schoolCountsGroupByNature(
			OrgSchoolCountsQuery query) {
		return orgSchoolMapper.schoolCountsGroupByNature(query);
	}

	/**
	 * 按隶属关系，统计学校数量
	 * @param query
	 * @return
	 */
	public List<OrgSchoolCountsGroupByNatureVO> schoolCountsGroupByAffiliation(
			OrgSchoolCountsQuery query) {
		return orgSchoolMapper.schoolCountsGroupByAffiliation(query);
	}

	/**
	 * 按学校性质，统计学生人数
	 * @param query
	 * @return
	 */
	public List<OrgSchoolCountsGroupByNatureVO> schoolPeopleCountsGroupByNature(
			OrgSchoolCountsQuery query) {
		return orgSchoolMapper.schoolPeopleCountsGroupByNature(query);
	}
	/**
	 * 按隶属关系，统计学生人数
	 * @param query
	 * @return
	 */
	public List<OrgSchoolCountsGroupByNatureVO> schoolPeopleCountsGroupByAffiliation(
			OrgSchoolCountsQuery query) {
		
		return orgSchoolMapper.schoolPeopleCountsGroupByAffiliation(query);
	}

}