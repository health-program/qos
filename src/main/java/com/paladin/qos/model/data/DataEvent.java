package com.paladin.qos.model.data;

import javax.persistence.Id;

public class DataEvent {

	// 
	@Id
	private String id;

	// 事件名称
	private String name;

	// 
	private String content;

	// 是否启用
	private Integer enabled;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}