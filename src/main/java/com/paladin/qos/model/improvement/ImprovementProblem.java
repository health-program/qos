package com.paladin.qos.model.improvement;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import java.util.Date;

public class ImprovementProblem {

	public static final String COLUMN_FIELD_CREATE_TIME = "createTime";

	@Id
	private String id;

	// 医院名称
	private String orgName;

	// 业务编码
	private String businessNo;

	// 原因
	private String reason;

	@OrderBy("DESC")
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}