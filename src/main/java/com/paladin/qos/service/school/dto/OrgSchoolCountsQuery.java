package com.paladin.qos.service.school.dto;

import java.util.List;

import com.paladin.framework.common.OffsetPage;

public class OrgSchoolCountsQuery extends OffsetPage {

    private String schoolYear;
    
    private String district;
    
    private List<String> affiliations;
    
    private String nature;
    
    public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

	public List<String> getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(List<String> affiliations) {
		this.affiliations = affiliations;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}
    
}