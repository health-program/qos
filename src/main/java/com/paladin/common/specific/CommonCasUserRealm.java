package com.paladin.common.specific;

import com.paladin.common.model.syst.SysUser;
import com.paladin.common.service.syst.SysUserService;
import com.paladin.framework.core.configuration.shiro.ShiroCasProperties;
import com.paladin.framework.core.session.UserSession;
import com.paladin.qos.core.QosUserSessionFactory;
import com.paladin.qos.model.org.OrgUser;
import com.paladin.qos.service.org.OrgUserService;
import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import io.buji.pac4j.token.Pac4jToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class CommonCasUserRealm extends Pac4jRealm {

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonCasUserRealm.class);

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private QosUserSessionFactory qosUserSessionFactory;

	@Autowired
	private OrgUserService orgUserService;

	private String idCardField;

	public CommonCasUserRealm(ShiroCasProperties shiroCasProperties) {
		this.setAuthenticationTokenClass(Pac4jToken.class);
		this.idCardField = "idcard";
	}

	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

		LOGGER.debug("后台登录：SysUserRealm.doGetAuthenticationInfo()");

		final Pac4jToken token = (Pac4jToken) authenticationToken;
        final List<CommonProfile> profiles = token.getProfiles();
        final Pac4jPrincipal principal = new Pac4jPrincipal(profiles, getPrincipalNameAttribute());

		String idcard = (String) principal.getProfile().getAttribute(idCardField);
		if(idcard == null || idcard.length() == 0) {
			throw new UnknownAccountException();
		}

		SysUser sysUser = sysUserService.getUserByAccount(idcard);

		if (sysUser == null) {
			OrgUser orgUser = orgUserService.geUserByIdCard(idcard);
			if(orgUser != null) {
				String username = orgUser.getAccount();
				if(username != null && username.length() > 0) {
					sysUser = sysUserService.getUserByAccount(username);
				}
			}
		}

		if (sysUser == null) {
			throw new UnknownAccountException();
		}

		if (!SysUser.STATE_ENABLED.equals(sysUser.getState())) {
			throw new LockedAccountException();
		}

		UserSession session = qosUserSessionFactory.createSession(sysUser);

		List<Object> principals = Arrays.asList(session,principal);
        PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, getName());       
        SimpleAuthenticationInfo authenticationInfo=  new SimpleAuthenticationInfo(principalCollection, token.getCredentials());

		LOGGER.info("===>用户[" + sysUser.getAccount() + ":" + session.getUserName() + "]登录系统<===");

		// 登录日志与更新最近登录时间
		sysUserService.updateLastTime(sysUser.getAccount());
        
		return authenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) throws AuthenticationException {
		// 废弃shiro缓存验证信息策略
		return null;
	}

	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			return null;
		}
		return (AuthorizationInfo) principals.getPrimaryPrincipal();
	}
}
