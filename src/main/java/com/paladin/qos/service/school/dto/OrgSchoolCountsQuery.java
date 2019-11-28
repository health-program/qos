package com.paladin.qos.service.school.dto;

import java.util.List;

import com.paladin.framework.common.OffsetPage;

public class OrgSchoolCountsQuery extends OffsetPage {

    private String schoolYear;
    
    private String district;
    
    private String businessDistrict;
    
    private List<String> affiliations;
    
    private String nature;
    
    private String sicknessClassify;
    
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

	public String getSicknessClassify() {
		return sicknessClassify;
	}

	public void setSicknessClassify(String sicknessClassify) {
		this.sicknessClassify = sicknessClassify;
	}

	public String getBusinessDistrict() {
	    return businessDistrict;
	}

	public void setBusinessDistrict(String businessDistrict) {
	    this.businessDistrict = businessDistrict;
	}
    
}