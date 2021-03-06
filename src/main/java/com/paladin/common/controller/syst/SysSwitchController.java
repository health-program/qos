package com.paladin.common.controller.syst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.common.model.syst.SysSwitch;
import com.paladin.common.service.syst.SysSwitchService;
import com.paladin.common.service.syst.dto.SysLoggerLoginQuery;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.web.response.CommonResponse;

@Controller
@RequestMapping("/common/sys/logger/switch")
public class SysSwitchController extends ControllerSupport {
	
	@Autowired
	private SysSwitchService sysSwitchService;
	
	@GetMapping("/index")
	@QueryInputMethod(queryClass = SysLoggerLoginQuery.class)
	public String index() {
		return "/common/syst/switch";
	}
	
	@RequestMapping("/find/all")
    @ResponseBody
    public Object findAll(SysSwitch query) {
	
		return CommonResponse.getSuccessResponse(sysSwitchService.findAll(query));
    }
	
	@RequestMapping("/change")
    @ResponseBody
    public Object change() {
	
		return CommonResponse.getSuccessResponse(sysSwitchService.change());
    }
	
	@RequestMapping("/findLastOne")
    @ResponseBody
    public Object findLastOne() {
	
		return CommonResponse.getSuccessResponse(sysSwitchService.getLastOne());
    }
}