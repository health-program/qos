package com.paladin.qos.model.hospitalData;

import com.paladin.framework.common.OffsetPage;

import java.util.Date;

public class HospitalDataLog extends OffsetPage{

	private String organCode;
	
	private String organName;
	
	private String tableCode;
	
	private String tableName;
	
	private String cntAll;
	
	private Date updateTime;
	
	private Date createTime;
	
	private String unitId;

    private String [] unitIds;

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCntAll() {
		return cntAll;
	}

	public void setCntAll(String cntAll) {
		this.cntAll = cntAll;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
