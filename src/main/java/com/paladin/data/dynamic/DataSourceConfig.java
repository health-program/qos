package com.paladin.data.dynamic;


public class DataSourceConfig {
	
	/**
	 * 数据库名称，唯一
	 */
	private String name;

	/**
	 * 数据链接URL
	 */
	private String url;

	/**
	 * 用户
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 是否启用
	 */
	private boolean enabled = true;
	
	/**  
	 * 数据库名称，唯一  
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 数据库名称，唯一  
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 数据链接URL  
	 */
	public String getUrl() {
		return url;
	}

	/**  
	 * 数据链接URL  
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**  
	 * 用户  
	 */
	public String getUsername() {
		return username;
	}

	/**  
	 * 用户  
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**  
	 * 密码  
	 */
	public String getPassword() {
		return password;
	}

	/**  
	 * 密码  
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
