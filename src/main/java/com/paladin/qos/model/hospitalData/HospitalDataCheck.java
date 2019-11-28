package com.paladin.qos.model.hospitalData;

import com.paladin.framework.common.OffsetPage;

public class HospitalDataCheck extends OffsetPage{

	private String orgcode;
	
	private String orgname;
	
	private String name;
	
	private String perc1;
	
	private String perc2;
	
	private String num1;
	
	private String num2;
	
	private String unitId;

    private String [] unitIds;

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerc1() {
		return perc1;
	}

	public void setPerc1(String perc1) {
		this.perc1 = perc1;
	}

	public String getPerc2() {
		return perc2;
	}

	public void setPerc2(String perc2) {
		this.perc2 = perc2;
	}

	public String getNum1() {
		return num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public String getNum2() {
		return num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String[] getUnitIds() {
		return unitIds;
	}

	public void setUnitIds(String[] unitIds) {
		this.unitIds = unitIds;
	}
    
}
