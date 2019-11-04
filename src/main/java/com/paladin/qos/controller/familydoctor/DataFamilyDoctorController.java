package com.paladin.qos.controller.familydoctor;

import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.service.familydoctor.vo.DataFamilyDoctorTeamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.familydoctor.FamilyDoctorTeamService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	public Object teamNumInfo() {
		List<DataFamilyDoctorTeamVo> list=familyDoctorTeamService.teamNum();
		orderByUnit(list);
		return CommonResponse.getSuccessResponse(list);
	}

	private void orderByUnit(List<DataFamilyDoctorTeamVo> list) {
		if (list != null && list.size() > 0) {
			Collections.sort(list, new Comparator<DataFamilyDoctorTeamVo>() {
				@Override
				public int compare(DataFamilyDoctorTeamVo o1, DataFamilyDoctorTeamVo o2) {
					String uid1 = o1.getUnitId();
					String uid2 = o2.getUnitId();
					return DataConstantContainer.getUnit(uid1).getOrderNum() > DataConstantContainer.getUnit(uid2).getOrderNum() ? 1 : -1;
				}
			});
		}
	}

	@RequestMapping("/data/count")
	@ResponseBody
	public Object teamCount() {
		return CommonResponse.getSuccessResponse(familyDoctorTeamService.dataTeamCount());
	}

}
