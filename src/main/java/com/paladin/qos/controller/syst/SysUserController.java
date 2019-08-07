package com.paladin.qos.controller.syst;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.common.model.syst.SysUser;
import com.paladin.common.service.org.OrgRoleService;
import com.paladin.common.service.syst.SysUserService;
import com.paladin.common.service.syst.dto.SysUserDTO;
import com.paladin.common.service.syst.dto.SysUserQuery;
import com.paladin.common.service.syst.vo.SysUserVO;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.GlobalProperties;
import com.paladin.framework.core.configuration.PaladinProperties;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.secure.SecureUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;

/**   
 * @author MyKite
 * @version 2019年7月29日 上午9:54:01 
 */
@Controller
@RequestMapping("/" + GlobalProperties.project+"/sys/user")
public class SysUserController extends ControllerSupport{
    
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private OrgRoleService orgRoleService;
    
    @Resource
    private PaladinProperties paladinProperties;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = SysUserQuery.class)
    public String index() {
        return "/qos/syst/sys_user_index";
    }
    
    @RequestMapping("/find/all")
    @ResponseBody
    @QueryOutputMethod(queryClass = SysUserQuery.class, paramIndex = 0)
    public Object findAll(SysUserQuery query) {
	return CommonResponse.getSuccessResponse(sysUserService.sysUserAll(query));
    }
    
    @RequestMapping("/find/role")
    @ResponseBody
    public Object find(){
	return CommonResponse.getSuccessResponse(orgRoleService.findAll());
    }
    
    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
	model.addAttribute("id", id);
	return "/qos/syst/sys_user_detail";
    }
    
    @GetMapping("/get")
    @ResponseBody
    public Object get(@RequestParam String id){
	return CommonResponse.getSuccessResponse(sysUserService.get(id));
    }
    
    @GetMapping("/add")
    public String addInput() {
	return "/qos/syst/sys_user_add";
    }
    
    @PostMapping("/save")
    @ResponseBody
    public Object save(@Valid SysUserDTO sysUserDTO, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	String account = sysUserDTO.getAccount();
	if (account == null || !sysUserService.validateAccount(account))
	throw new BusinessException("账号不符合(必须在5个字符以上)规则或者已经存在该账号");
	
	String salt = SecureUtil.createSalte();
	String password = paladinProperties.getDefaultPassword();
	String casPassword = new SimpleHash("md5", password).toBase64();
	password = SecureUtil.createPassword(password, salt);
	
	String id = UUIDUtil.createUUID();
	sysUserDTO.setId(id);
	SysUser user = new SysUser();
	user.setAccount(account);
	user.setPassword(password);
	user.setSalt(salt);
	user.setType(0);
	user.setState(SysUser.STATE_ENABLED);
	user.setCasPassword(casPassword);
	
	SimpleBeanCopyUtil.simpleCopy(sysUserDTO, user);
	
	if (sysUserService.save(user) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy( sysUserService.get(id), new SysUserVO()));
	}
	return CommonResponse.getFailResponse();
    }
    
    @PostMapping("/update")
    @ResponseBody
    public Object update(@Valid SysUserDTO sysUserDTO, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	String id = sysUserDTO.getId();
	SysUser user = sysUserService.get(id);
	if(user == null)
	    throw new BusinessException("账号不存在或信息有误");
	user.setId(id);
	SimpleBeanCopyUtil.simpleCopy(sysUserDTO, user);
	if (sysUserService.update(user) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy(sysUserService.get(id), new SysUserVO()));
	}
	return CommonResponse.getFailResponse();
    }
    
    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object delete(@RequestParam String id) {
	return CommonResponse.getResponse(sysUserService.removeByPrimaryKey(id));
    }
    
    /**
	 * 重置密码
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/reset/password")
	@ResponseBody
	public Object resetPassword(@RequestParam(required = true) String userId) {
		return CommonResponse.getResponse(sysUserService.resetOrgUserPassword(userId));
	}
}
