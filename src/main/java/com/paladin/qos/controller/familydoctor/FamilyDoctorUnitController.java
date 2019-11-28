package com.paladin.qos.controller.familydoctor;

import com.paladin.common.controller.syst.SysControllerLog;
import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.controller.familydoctor.dto.FamilyDoctorUnitExportCondition;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.model.org.OrgPerson;
import com.paladin.qos.service.familydoctor.FamilyDoctorUnitService;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorUnitDTO;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorUnitQuery;
import com.paladin.qos.service.familydoctor.vo.FamilyDoctorUnitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/qos/family/doctor/unit")
public class FamilyDoctorUnitController extends ControllerSupport {

    @Autowired
    private FamilyDoctorUnitService familyDoctorUnitService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = FamilyDoctorUnitQuery.class)
    public String index() {
        return "/qos/familydoctor/family_doctor_unit_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = FamilyDoctorUnitQuery.class, paramIndex = 0)
    public Object findPage(FamilyDoctorUnitQuery query) {
		List<FamilyDoctorUnit> list=familyDoctorUnitService.searchPage(query).getData();
		orderByUnit(list);
        return CommonResponse.getSuccessResponse(list);
    }

	private void orderByUnit(List<FamilyDoctorUnit> list) {
		if (list != null && list.size() > 0) {
			Collections.sort(list, new Comparator<FamilyDoctorUnit>() {
				@Override
				public int compare(FamilyDoctorUnit o1, FamilyDoctorUnit o2) {
					String uid1 = o1.getId();
					String uid2 = o2.getId();
					return DataConstantContainer.getUnit(uid1).getOrderNum() > DataConstantContainer.getUnit(uid2).getOrderNum() ? 1 : -1;
				}
			});
		}
	}


	@RequestMapping(value = "/find/all", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findPage() {
		List<FamilyDoctorUnit> list=familyDoctorUnitService.findAll();
		orderByUnit(list);
    	return CommonResponse.getSuccessResponse(list);
    }
    
    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(familyDoctorUnitService.get(id), new FamilyDoctorUnitVO()));
    }
    
    @GetMapping("/add")
    public String addInput() {
        return "/qos/familydoctor/family_doctor_unit_add";
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/qos/familydoctor/family_doctor_unit_detail";
    }
    
    @PostMapping("/save")
	@ResponseBody
    public Object save(@Valid FamilyDoctorUnitDTO familyDoctorUnitDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        FamilyDoctorUnit model = beanCopy(familyDoctorUnitDTO, new FamilyDoctorUnit());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (familyDoctorUnitService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(familyDoctorUnitService.get(id), new FamilyDoctorUnitVO()));
		}
		return CommonResponse.getFailResponse();
	}

    @PostMapping("/update")
	@SysControllerLog(action = "修改家庭医生社区")
	@ResponseBody
    public Object update(@Valid FamilyDoctorUnitDTO familyDoctorUnitDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = familyDoctorUnitDTO.getId();
		FamilyDoctorUnit model = beanCopy(familyDoctorUnitDTO, familyDoctorUnitService.get(id));
		if (familyDoctorUnitService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(familyDoctorUnitService.get(id), new FamilyDoctorUnitVO()));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@SysControllerLog(action = "删除家庭医生社区")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(familyDoctorUnitService.removeByPrimaryKey(id));
    }
    
    @PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody FamilyDoctorUnitExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		FamilyDoctorUnitQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, familyDoctorUnitService.searchAll(query), FamilyDoctorUnit.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, familyDoctorUnitService.searchPage(query).getData(), FamilyDoctorUnit.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}
}