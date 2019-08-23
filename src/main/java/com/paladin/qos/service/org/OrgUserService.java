package com.paladin.qos.service.org;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Unit;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.model.org.OrgUser;
import com.paladin.common.model.org.OrgRole;
import com.paladin.common.model.syst.SysUser;
import com.paladin.common.service.org.OrgRoleService;
import com.paladin.common.service.syst.SysUserService;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.qos.service.org.dto.OrgUserDTO;
import com.paladin.qos.service.org.dto.OrgUserQuery;
import com.paladin.qos.service.org.vo.OrgUserVO;

@Service
public class OrgUserService extends ServiceSupport<OrgUser> {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private OrgRoleService orgRoleService;


	@SuppressWarnings("unchecked")
	public PageResult<OrgUserVO> findOwnUsersPage(OrgUserQuery query) {
		QosUserSession session = QosUserSession.getCurrentUserSession();
		if (session.isAdminRoleLevel()) {

		} else if (session.getRoleLevel() >= QosUserSession.ROLE_LEVEL_AGENCY) {		
			String[] ids = session.getAgencyIds();
			
			if (ids == null || ids.length == 0) {
				return getEmptyPageResult(query);
			}
			
			if (ids.length == 1) {
				query.setAgencyId(ids[0]);
			} else {
				query.setAgencyIds(Arrays.asList(ids));
			}
		} else {
			return getEmptyPageResult(query);
		}

		return searchPage(query).convert(OrgUserVO.class);
	}
	
	
	@Transactional
	public OrgUser createUser(OrgUserDTO orgUserDTO) {
		OrgUser model = new OrgUser();
		SimpleBeanCopyUtil.simpleCopy(orgUserDTO, model);
		String id = UUIDUtil.createUUID();
		model.setId(id);
		
		String agencyId = orgUserDTO.getAgencyId();
		if (agencyId != null && agencyId.length() > 0) {
			if (!ownAgency(agencyId)) {
				throw new BusinessException("没有权限选择该医院");
			}
		}
		
		if (sysUserService.createUserAccount(orgUserDTO.getAccount(), id, SysUser.TYPE_ORG_USER) > 0) {
			if (save(model) > 0) {
				return model;
			}
		}
		return null;
	}

	@Transactional
	public OrgUser updateUser(OrgUserDTO orgUserDTO) {
		String id = orgUserDTO.getId();
		if (id == null || id.length() == 0) {
			throw new BusinessException("没有找到需要更新的用户");
		}

		String agencyId = orgUserDTO.getAgencyId();
		if (agencyId != null && agencyId.length() > 0) {
			if (!ownAgency(agencyId)) {
				throw new BusinessException("没有权限选择该医院");
			}
		}

		OrgUser model = get(id);
		String account = model.getAccount();
		SimpleBeanCopyUtil.simpleCopy(orgUserDTO, model);

		String newAccount = model.getAccount();
		if (newAccount == null || newAccount.length() == 0) {
			throw new BusinessException("账号不能为空");
		}

		if (!account.equals(newAccount)) {
			sysUserService.updateAccount(id, account, newAccount);
		}

		if (update(model) > 0) {
			return model;
		}

		return null;
	}

	public boolean ownAgency(String agencyId) {
		QosUserSession session = QosUserSession.getCurrentUserSession();
		if (session.isAdminRoleLevel()) {
			return true;
		} else if (session.getRoleLevel() >= QosUserSession.ROLE_LEVEL_AGENCY) {
			String[] agencyIds = session.getAgencyIds();
			if (agencyIds != null) {
				for (String aid : agencyIds) {
					if (aid.equals(agencyId)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public List<Unit> findOwnedAgencies() {
		QosUserSession session = QosUserSession.getCurrentUserSession();
		if (session.isAdminRoleLevel()) {
			return DataConstantContainer.getUnitList();
		} else if (session.getRoleLevel() >= QosUserSession.ROLE_LEVEL_AGENCY) {
			String[] agencyIds = session.getAgencyIds();
			if (agencyIds != null && agencyIds.length > 0) {
				List<Unit> result = new ArrayList<>(agencyIds.length);
				for (String agencyId : agencyIds) {
					Unit agency = DataConstantContainer.getUnit(agencyId);
					if (agency != null) {
						result.add(agency);
					}
				}
				return result;
			}
		}
		return null;
	}

	public List<OrgRole> searchOwnedRoles() {
		QosUserSession session = QosUserSession.getCurrentUserSession();
		int roleLevel = session.getRoleLevel();
		return orgRoleService.getOwnGrantRoles(roleLevel, false);
	}

	@Transactional
	public int deleteUserById(String id) {
		List<SysUser> sysUsers = sysUserService.searchAll(new Condition(SysUser.COLUMN_FIELD_USER_ID, QueryType.EQUAL, id));
		int i = 0;

		if (sysUsers != null && sysUsers.size() > 0) {
			i = sysUserService.removeByPrimaryKey(sysUsers.get(0).getId());
		}

		if (i > 0) {
			i += removeByPrimaryKey(id);
		}
		return i;
	}

	public OrgUser geUserByIdCard(String idcard) {
		return searchOne(new Condition(OrgUser.COLUMN_FIELD_IDENTIFICATION_ID, QueryType.EQUAL, idcard));
	}
}