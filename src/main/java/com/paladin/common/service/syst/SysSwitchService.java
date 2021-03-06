package com.paladin.common.service.syst;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.common.mapper.syst.SysSwitchMapper;
import com.paladin.common.model.syst.SysSwitch;
import com.paladin.common.util.IPUtil;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.session.UserSession;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.qos.core.QosUserSession;

@Service
public class SysSwitchService extends ServiceSupport<SysSwitch> {
	
	@Autowired
	private SysSwitchMapper sysSwitchMapper;

	public PageResult<SysSwitch> findAll(SysSwitch query) {
	   	Page<SysSwitch> page = PageHelper.offsetPage(query.getOffset(),query.getLimit());
	   	QosUserSession session = QosUserSession.getCurrentUserSession();
		
	   	sysSwitchMapper.findAll(query);
	   	return new PageResult<>(page);
	   	
    }

	public String change() {
		
        //1.查询最近一条记录的state 值
		SysSwitch sysSwitch = this.getLastOne();
		//2.插入一条新记录，并返回state值
		WebSubject webSubject = (WebSubject) SecurityUtils.getSubject();
        HttpServletRequest request = (HttpServletRequest) webSubject.getServletRequest();
        String  ip = IPUtil.getIpAddress(request);

        UserSession userSession = QosUserSession.getCurrentUserSession();

        String account = userSession.getAccount();
        
        LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm a");
		String loginTime = now.format(formatter);
		
		SysSwitch model = new SysSwitch();
		model.setId(UUIDUtil.createUUID());
		model.setIp(ip);
		model.setAccount(account);
		
		String message = null;
		if(sysSwitch.getState().equals("1")){
			model.setState("0");
			message = "检查类型";
		}else{
			model.setState("1");
			message = "疾病类型";
		}
		model.setLoginAction(userSession.getUserName()+"：已切换为:"+message+"("+loginTime+")");
		model.setUpdateTime(new Date());
		sysSwitchMapper.save(model);
		return model.getState();
	}

	
	public SysSwitch getLastOne(){
		SysSwitch sysSwitch = sysSwitchMapper.getLastOne();
		return sysSwitch;
	}
}