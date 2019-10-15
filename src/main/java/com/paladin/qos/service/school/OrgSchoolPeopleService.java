package com.paladin.qos.service.school;

import java.util.List;
import org.springframework.stereotype.Service;
import com.paladin.qos.model.school.OrgSchoolPeople;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class OrgSchoolPeopleService extends ServiceSupport<OrgSchoolPeople> {

	public List<OrgSchoolPeople> findSchoolPeople(String schoolId) {
		return searchAll(new Condition(OrgSchoolPeople.COLUMN_SCHOOL_ID, QueryType.EQUAL, schoolId));
	}

	public int deletePeople(String schoolId) {
		return removeByExample(new Condition(OrgSchoolPeople.COLUMN_SCHOOL_ID, QueryType.EQUAL, schoolId));
	}
	
	public List<OrgSchoolPeople> getGrade(String schoolId,int schoolSection,int grade,String klass){
	    return searchAll(new Condition[]{
		    new Condition(OrgSchoolPeople.COLUMN_SCHOOL_ID, QueryType.EQUAL, schoolId),
		    new Condition(OrgSchoolPeople.COLUMN_SCHOOL_SECTION, QueryType.EQUAL, schoolSection),
		    new Condition(OrgSchoolPeople.COLUMN_SCHOOL_GRADE, QueryType.EQUAL, grade),
		    new Condition(OrgSchoolPeople.COLUMN_SCHOOL_KLASS, QueryType.EQUAL, klass),
	    });
	}; 

}