package com.paladin.qos.controller.hospitalData;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.common.controller.syst.SysControllerLog;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.model.hospitalData.HospitalDataCheck;
import com.paladin.qos.model.hospitalData.HospitalDataLog;
import com.paladin.qos.model.hospitalData.HospitalDataRule;
import com.paladin.qos.model.org.OrgPerson;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.hospital.HospitalDataLogService;
import com.paladin.qos.service.org.dto.OrgPersonDTO;
import com.paladin.qos.service.org.vo.OrgPersonVO;

@Controller
@RequestMapping("/qos/hospitalData")
public class HospitalDataController {
	@Autowired
	private HospitalDataLogService hospitalDataLogService;

	@Autowired
	private DataUnitService dataUnitService;

	@GetMapping("/log/index")
	public Object logInex(Model model) {

		List<Integer> types = new ArrayList<>();
		types.add(DataUnit.TYPE_HOSPITAL);
		model.addAttribute("unit", dataUnitService.selectData(types));
		return "/qos/hospitalData/log";
	}

	@RequestMapping(value = "/findLog/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findLog(HospitalDataLog query) {
		return CommonResponse.getSuccessResponse(hospitalDataLogService.searchFindPage(query));
	}

	@GetMapping("/check/index")
	public Object checkInex(Model model) {
		List<Integer> types = new ArrayList<>();
		types.add(DataUnit.TYPE_HOSPITAL);
		model.addAttribute("unit", dataUnitService.selectData(types));
		return "/qos/hospitalData/check";
	}

	@RequestMapping(value = "/findCheck/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findPage(HospitalDataCheck query) {
		return CommonResponse.getSuccessResponse(hospitalDataLogService.searchFindCheckPage(query));
	}
	
	//数据质量校验规则管理
	@GetMapping("/rule/index")
	public Object ruleInex(Model model) {
		return "/qos/hospitalData/medical_score_index";
	}
	
	@RequestMapping(value = "/findRule/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findRule(HospitalDataRule query) {
		return CommonResponse.getSuccessResponse(hospitalDataLogService.searchRulePage(query));
	}
	
	@RequestMapping(value = "/close", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object close(@RequestParam String id) {
	return CommonResponse.getResponse(hospitalDataLogService.close(id));
    }
	
	@RequestMapping(value = "/open", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object open(@RequestParam String id) {
	return CommonResponse.getResponse(hospitalDataLogService.open(id));
    }
	
	@GetMapping("/add")
    public String addInput() {
	return "/qos/hospitalData/medical_score_add";
    }
	
	@PostMapping("/save")
	@SysControllerLog(action = "新增学校信息")
    @ResponseBody
    public Object save(HospitalDataRule hospitalDataRule) {
	
	String id = UUIDUtil.createUUID();
	hospitalDataRule.setId(id);
	hospitalDataRule.setState("1");
	try {
		if (hospitalDataLogService.save(hospitalDataRule) > 0) {
			return CommonResponse.getSuccessResponse();
		}
	} catch (Exception e) {
		return CommonResponse.getFailResponse(e.getMessage());
	}
	return CommonResponse.getFailResponse();
    }
	
	@RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/qos/hospitalData/medical_score_detail";
    }
	
	@RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {
        return CommonResponse.getSuccessResponse(hospitalDataLogService.getHospitalDataRule(id));
    }
	
	@PostMapping("/update")
    @ResponseBody
    public Object update(HospitalDataRule hospitalDataRule) {
		
		//HospitalDataRule model = hospitalDataLogService.getHospitalDataRule(hospitalDataRule.getId());
        if (hospitalDataLogService.updateHospitalDataRule(hospitalDataRule) > 0) {
        	return CommonResponse.getSuccessResponse();
        }
        return CommonResponse.getFailResponse();
    }
}
