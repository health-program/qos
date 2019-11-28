package com.paladin.qos.model.gongwei;

public class Disease {
    
	public String unitId;
	
	public String unitName;
	
	public String diseasecode;
	
	public String diseasecodeName;
	
	public String orgcode;
	
	public long count;

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getDiseasecode() {
		return diseasecode;
	}

	public void setDiseasecode(String diseasecode) {
		this.diseasecode = diseasecode;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getDiseasecodeName() {
		return diseasecodeName;
	}

	public void setDiseasecodeName(String diseasecodeName) {
		this.diseasecodeName = diseasecodeName;
	}
	
}
