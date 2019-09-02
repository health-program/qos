package com.paladin.data.dynamic;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceFacade {

	protected String name;
	protected boolean enabled;
	protected HikariDataSource realDataSource;
	protected DataSourceConfig config;

	public DataSourceFacade(DataSourceConfig config) {
		if (config == null) {
			throw new IllegalArgumentException("DataSource Config can't be null");
		}

		this.config = config;
		this.name = config.getName();

		setEnabled(config.getEnabled());
	}

	/**
	 * 根据数据库配置创建一个真实的数据源，该方法会在被对象创建时调用
	 */
	private HikariDataSource createRealDataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(config.getUrl());
		hikariConfig.setUsername(config.getUsername());
		hikariConfig.setPassword(config.getPassword());
		return new HikariDataSource(hikariConfig);
	}

	/**
	 * 设置是否启用
	 * 
	 * @param enabled
	 */
	public synchronized void setEnabled(boolean enabled) {
		if (this.enabled != enabled) {
			if (this.enabled) {
				// 关闭
				if (realDataSource != null) {
					realDataSource.close();
					realDataSource = null;
				}
				this.enabled = false;
			} else {
				// 打开
				if (realDataSource != null) {
					// 如果有数据源尝试关闭
					realDataSource.close();
					realDataSource = null;
				}
				realDataSource = createRealDataSource();
				this.enabled = true;
			}
		}
	}

	/**
	 * 是否启用
	 * 
	 * @return
	 */
	public synchronized boolean isEnabled() {
		return enabled;
	}

	/**
	 * 获取数据库配置
	 * 
	 * @return
	 */
	public DataSourceConfig getDataBaseConfig() {
		return config;
	}

	/**
	 * 获取真正的数据源操作
	 * 
	 * @return
	 */
	public DataSource getRealDataSource() {
		return realDataSource;
	}

	/**
	 * 数据源名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
}
