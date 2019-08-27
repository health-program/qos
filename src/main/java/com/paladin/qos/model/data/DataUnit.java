package com.paladin.qos.model.data;

import javax.persistence.Id;

public class DataUnit {

	// 
	@Id
	private String id;

	// 医院名称
	private String name;

	// 备注说明
	private String note;

	//床位数量
	private Integer bedNumber;

	public Integer getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(Integer bedNumber) {
		this.bedNumber = bedNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}