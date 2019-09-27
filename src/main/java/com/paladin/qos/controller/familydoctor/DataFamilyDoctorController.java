package com.paladin.qos.controller.familydoctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.familydoctor.FamilyDoctorTeamService;

/**
 * @author MyKite
 * @version 2019年9月9日 下午1:33:49
 */
@Controller
@RequestMapping("/qos/data/familydoctor")
public class DataFamilyDoctorController extends ControllerSupport {

	@Autowired
	private FamilyDoctorTeamService familyDoctorTeamService;

	@GetMapping("/index")
	public String index() {
		return "/qos/familydoctor/data_family_doctor";
	}

	@GetMapping("/service/index")
	public String serviceIndex() {
		return "/qos/familydoctor/data_family_doctor_service";
	}

	@GetMapping("/patient/index")
	public String patientIndex() {
		return "/qos/familydoctor/data_family_doctor_outpatient";
	}

	@GetMapping("/signing/index")
	public String signingIndex() {
		return "/qos/familydoctor/data_family_doctor_signingrate";
	}

	@GetMapping("/unit/index")
	public String teamIndex() {
		return "/qos/familydoctor/data_family_doctor_unit";
	}

	@RequestMapping("/unit/find")
	@ResponseBody
	public Object teamNum() {
		return CommonResponse.getSuccessResponse(familyDoctorTeamService.teamNum());
	}

}
