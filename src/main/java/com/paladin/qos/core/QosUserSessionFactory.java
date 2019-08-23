package com.paladin.qos.core;

import com.google.common.base.Strings;
import com.paladin.common.core.permission.PermissionContainer;
import com.paladin.common.core.permission.Role;
import com.paladin.common.model.syst.SysUser;
import com.paladin.framework.core.session.UserSession;
import com.paladin.qos.model.org.OrgUser;
import com.paladin.qos.service.org.OrgUserService;

import org.apache.shiro.authc.DisabledAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QosUserSessionFactory {

	@Autowired
	private PermissionContainer permissionContainer;

	@Autowired
	private OrgUserService orgUserService;

	public UserSession createSession(SysUser sysUser) {
		int type = sysUser.getType();
		QosUserSession userSession = null;

		if (type == SysUser.TYPE_ADMIN) {
			userSession = new QosUserSession(sysUser.getId(), "系统管理员", sysUser.getAccount());
			userSession.isSystemAdmin = true;
			userSession.roleLevel = QosUserSession.ROLE_LEVEL_ADMIN;
		} else if (type == SysUser.TYPE_ORG_USER) {
			// 机构
			String userId = sysUser.getUserId();
			OrgUser orgUser = orgUserService.get(userId);

			String agencyId = orgUser.getAgencyId();
			String roleId = orgUser.getRoleId();

			if (roleId == null || roleId.length() == 0) {
				throw new DisabledAccountException("账号异常，没有角色");
			}
			
			Role role = permissionContainer.getRole(roleId);
			List<String> roleIds = new ArrayList<>();
			int roleLevel = -1;
			if (role != null) {
				roleIds.add(roleId);
				roleLevel = role.getRoleLevel();
			} else {
				throw new DisabledAccountException("账号异常，没有角色");
			}

			userSession = new QosUserSession(userId, orgUser.getName(), orgUser.getAccount());

			String[] agencyIds = null;
			if (!Strings.isNullOrEmpty(agencyId)) {
				agencyIds = agencyId.split(",");
			}
			
			if (agencyIds == null) {
				throw new DisabledAccountException("账号异常，未配置管理机构");
			}

			userSession.roleIds = roleIds;
			userSession.roleLevel = roleLevel;
			userSession.agencyIds = agencyIds;
		}

		if (userSession == null) {
			throw new DisabledAccountException();
		}

		return userSession;
	}

}
