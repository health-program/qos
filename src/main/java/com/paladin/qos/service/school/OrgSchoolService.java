package com.paladin.qos.service.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.paladin.qos.mapper.school.OrgSchoolMapper;
import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.model.school.OrgSchoolPeople;
import com.paladin.qos.service.school.dto.OrgSchoolDTO;
import com.paladin.qos.service.school.dto.OrgSchoolPeopleDTO;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import com.paladin.qos.service.school.vo.OrgSchoolVO;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;

@Service
public class OrgSchoolService extends ServiceSupport<OrgSchool> {
    
    @Autowired
    private OrgSchoolPeopleService orgSchoolPeopleService;
    
    @Autowired
    private OrgSchoolMapper orgSchoolMapper;
    
    
    public PageResult<OrgSchoolVO> searchFindPage(OrgSchoolQuery query) {
	Page<OrgSchoolVO> page = PageHelper.offsetPage(query.getOffset(),query.getLimit());
	orgSchoolMapper.findSchool(query);
	return new PageResult<>(page);
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
	vo.setPeople(orgSchoolPeopleService.findSchoolPeople(id));
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
	
	List<OrgSchoolPeopleDTO> peoples = dto.getPeople();
	if (peoples == null || peoples.size() == 0) {
		throw new BusinessException("人数不能为空");
	}
	
	for (OrgSchoolPeopleDTO orgSchoolPeopleDTO : peoples) {
	    OrgSchoolPeople people = new OrgSchoolPeople();
	    SimpleBeanCopyUtil.simpleCopy(orgSchoolPeopleDTO, people);
	    people.setSchoolId(schoolId);
	    orgSchoolPeopleService.save(people);
	}
	
	return save(school);
    }
    
    @Transactional
    public int schoolUpdate(OrgSchoolDTO dto){
	String schoolId = dto.getId();
	if (schoolId == null || schoolId.length() == 0) {
		throw new BusinessException("没有对应需要更新学校信息");
	}

	OrgSchool school = get(schoolId);
	if (school == null) {
		throw new BusinessException("没有对应需要更新学校信息");
	}

	SimpleBeanCopyUtil.simpleCopy(dto, school);
	
	List<OrgSchoolPeopleDTO> dtos = dto.getPeople();
	
	if (dtos == null || dtos.size() == 0) {
		throw new BusinessException("人数不能为空");
	}
	orgSchoolPeopleService.deletePeople(schoolId);
	
	for (OrgSchoolPeopleDTO orgSchoolPeopleDTO : dtos) {
	    OrgSchoolPeople people = new OrgSchoolPeople();
	    SimpleBeanCopyUtil.simpleCopy(orgSchoolPeopleDTO, people);
	    people.setSchoolId(schoolId);
	    orgSchoolPeopleService.save(people);
	}
	
	return update(school);
	
    }
    
    
    
    @Transactional
    public int deleteSchoolAndPeople(String id){
	removeByPrimaryKey(id);
	return orgSchoolPeopleService.deletePeople(id);
    }

}