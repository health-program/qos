package com.paladin.common.mapper.syst;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.common.model.syst.SysUser;
import com.paladin.common.service.syst.dto.SysUserQuery;
import com.paladin.common.service.syst.vo.SysUserVO;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface SysUserMapper extends CustomMapper<SysUser> {

	public int updateAccount(@Param("userId") String userId, @Param("originAccount") String originAccount, @Param("nowAccount") String nowAccount);
	
	public List<SysUserVO> sysUserAll(@Param("query") SysUserQuery query);
}
