package com.paladin.common.specific;

import com.paladin.common.core.container.DefaultVersionContainerDAO;
import com.paladin.common.core.exception.CommonHandlerExceptionResolver;
import com.paladin.common.core.template.TontoDialect;
import com.paladin.framework.core.VersionContainerDAO;
import com.paladin.framework.core.configuration.shiro.ShiroCasProperties;
import io.buji.pac4j.realm.Pac4jRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class CommonConfiguration {

	/**
	 * 启动模板自定义方言
	 * 
	 * @return
	 */
	@Bean
	public TontoDialect getTontoDialect() {
		return new TontoDialect();
	}

	/**
	 * 启用单点登录Realm
	 * 
	 * @return
	 */
	@Bean("casRealm")
	@ConditionalOnProperty(prefix = "paladin", value = "cas-enabled", havingValue = "true", matchIfMissing = false)
	public Pac4jRealm getCasRealm(ShiroCasProperties shiroCasProperties) {
		return new CommonCasUserRealm(shiroCasProperties);
	}

	/**
	 * 启用默认本地登录Realm
	 * 
	 * @return
	 */
	@Bean("localRealm")
	public AuthorizingRealm getLocalRealm() {
		return new CommonUserRealm();
	}

	/**
	 * 启用异常统一处理
	 * 
	 * @return
	 */
	@Bean
	public HandlerExceptionResolver getHandlerExceptionResolver() {
		return new CommonHandlerExceptionResolver();
	}

	/**
	 * 登录登出验证监听
	 * 
	 * @return
	 */
	@Bean
	public CommonAuthenticationListener getCommonAuthenticationListener() {
		return new CommonAuthenticationListener();
	}

	@Bean
	public VersionContainerDAO getVersionContainerDAO() {
		return new DefaultVersionContainerDAO();
	}
}
