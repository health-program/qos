package com.paladin.qos.service.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.paladin.qos.mapper.school.OrgSchoolNameMapper;
import com.paladin.qos.model.school.OrgSchoolName;
import com.paladin.qos.service.school.vo.SchoolNameVO;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class OrgSchoolNameService extends ServiceSupport<OrgSchoolName> {
    
    @Autowired
    private OrgSchoolNameMapper orgSchoolNameMapper;
    
    public int deleteSchoolName(String id) {
	return removeByExample(new Condition(OrgSchoolName.COLUMN_PARENT_ID,QueryType.EQUAL, id));
    }
    
    public List<OrgSchoolName> findSchoolName(String parentId) {
	return searchAll(new Condition(OrgSchoolName.COLUMN_PARENT_ID,QueryType.EQUAL, parentId));
    }

    public int removeBySchoolName(String id){
	if(StringUtil.isEmpty(id)){
	    throw new BusinessException("该记录不存在");
	}
	List<OrgSchoolName> list = findSchoolName(id);
	if(list.size()>0){
	    throw new BusinessException("请先删除下属分校或分部");
	}
	return removeByPrimaryKey(id);
    }
    
    public List<SchoolNameVO> schoolNameFind(){
	return orgSchoolNameMapper.schoolNameFind();
    }
}