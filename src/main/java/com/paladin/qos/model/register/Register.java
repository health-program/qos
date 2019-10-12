package com.paladin.qos.model.register;

import java.sql.Date;

public class Register {

	private String regid;//序号
	
	private String orgname;//就诊机构
	
	private String deptname;//就诊科室
	
	private String patientname;//患者姓名
	
	private String sexcode;//患者性别1：男，2：女
	
	private String seedate;//就诊日期
	
	private String paykindname;//结算类别

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getSexcode() {
		return sexcode=="1"?"男":"女";
	}

	public void setSexcode(String sexcode) {
		this.sexcode = sexcode;
	}

	public String getSeedate() {
		return seedate;
	}

	public void setSeedate(String seedate) {
		this.seedate = seedate;
	}

	public String getPaykindname() {
		return paykindname;
	}

	public void setPaykindname(String paykindname) {
		this.paykindname = paykindname;
	}

	
}
