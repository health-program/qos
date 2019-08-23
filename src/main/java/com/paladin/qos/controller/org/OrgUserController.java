package com.paladin.qos.controller.org;

import com.paladin.qos.service.org.OrgUserService;
import com.paladin.qos.service.org.dto.OrgUserQuery;
import com.paladin.qos.service.org.dto.OrgUserDTO;
import com.paladin.qos.service.org.vo.OrgUserVO;

import com.paladin.common.service.syst.SysUserService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/qos/org/user")
public class OrgUserController extends ControllerSupport {

    @Autowired
    private OrgUserService orgUserService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/index")
    public String index() {
        return "/qos/org/org_user_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(OrgUserQuery query) {
        return CommonResponse.getSuccessResponse(orgUserService.findOwnUsersPage(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {
        return CommonResponse.getSuccessResponse(beanCopy(orgUserService.get(id), new OrgUserVO()));
    }
    
	@RequestMapping("/find/own/role")
	@ResponseBody
	public Object findOwnRoles() {
		return CommonResponse.getSuccessResponse(orgUserService.searchOwnedRoles());
	}
    @RequestMapping("/add")
    public String addInput(Model model) {
        return "/qos/org/org_user_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/qos/org/org_user_detail";
    }

    @RequestMapping("/find/own/tree")
	@ResponseBody
	public Object findOwnTree() {
		return CommonResponse.getSuccessResponse(orgUserService.findOwnedAgencies());
	}
    
    @RequestMapping("/validate")
    @ResponseBody
    public Object validate(String account) {
        return CommonResponse.getResponse(sysUserService.validateAccount(account));
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid OrgUserDTO orgUserDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        return CommonResponse.getSuccessResponse(orgUserService.createUser(orgUserDTO));   
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid OrgUserDTO orgUserDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		
        return CommonResponse.getSuccessResponse(orgUserService.updateUser(orgUserDTO)); 
	}

    @RequestMapping("/reset")
    @ResponseBody
    public Object reset(@RequestParam String account) {
        return CommonResponse.getResponse(sysUserService.resetPassword(account));
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(orgUserService.deleteUserById(id));
    }
}