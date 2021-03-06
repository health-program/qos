package com.paladin.common.model.syst;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;

import com.paladin.framework.common.OffsetPage;

import java.util.Date;

public class SysSwitch extends OffsetPage{


	@Id
	@GeneratedValue(generator = "UUID")
	private String id;


	private String ip;

	
	// 登录账号
	private String account;

	private String loginAction;

    private String state;
	
	@OrderBy("DESC")
	private Date updateTime;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getLoginAction() {
		return loginAction;
	}


	public void setLoginAction(String loginAction) {
		this.loginAction = loginAction;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}

	
}