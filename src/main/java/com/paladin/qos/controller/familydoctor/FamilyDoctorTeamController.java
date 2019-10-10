package com.paladin.qos.controller.familydoctor;

import com.paladin.qos.controller.familydoctor.dto.FamilyDoctorTeamExportCondition;
import com.paladin.qos.model.familydoctor.FamilyDoctorTeam;
import com.paladin.qos.service.familydoctor.FamilyDoctorTeamService;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorTeamQuery;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorTeamDTO;
import com.paladin.qos.service.familydoctor.vo.FamilyDoctorTeamVO;
import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.framework.utils.uuid.UUIDUtil;

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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import javax.validation.Valid;

@Controller
@RequestMapping("/qos/family/doctor/team")
public class FamilyDoctorTeamController extends ControllerSupport {

    @Autowired
    private FamilyDoctorTeamService familyDoctorTeamService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = FamilyDoctorTeamQuery.class)
    public String index(Model model,@RequestParam String unitId) {
	model.addAttribute("unitId",unitId);
        return "/qos/familydoctor/family_doctor_team_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = FamilyDoctorTeamQuery.class, paramIndex = 0)
    public Object findPage(FamilyDoctorTeamQuery query) {
        return CommonResponse.getSuccessResponse(familyDoctorTeamService.searchPage(query));
    }
    
    @RequestMapping(value = "/data/count", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object DataTeamCount(){
	return CommonResponse.getSuccessResponse(familyDoctorTeamService.DataTeamCount());
    }
    
    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(familyDoctorTeamService.get(id), new FamilyDoctorTeamVO()));
    }
    
    @GetMapping("/add")
    public String addInput(Model model,@RequestParam String unitId) {
	model.addAttribute("unitId",unitId);
        return "/qos/familydoctor/family_doctor_team_add";
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id,@RequestParam String unitId, Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("unitId", unitId);
        return "/qos/familydoctor/family_doctor_team_detail";
    }
    
    @PostMapping("/save")
    @ResponseBody
    public Object save(@Valid FamilyDoctorTeamDTO familyDoctorTeamDTO, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
		return validErrorHandler(bindingResult);
	}
	if(familyDoctorTeamService.countTaem(familyDoctorTeamDTO.getTeamName()) != null){
	    return CommonResponse.getErrorResponse("团队名称已存在");
	}
	FamilyDoctorTeam model = beanCopy(familyDoctorTeamDTO, new FamilyDoctorTeam());
	String id = UUIDUtil.createUUID();
	model.setId(id);
	if (familyDoctorTeamService.save(model) > 0) {
		return CommonResponse.getSuccessResponse(beanCopy(familyDoctorTeamService.get(id), new FamilyDoctorTeamVO()));
	}
	return CommonResponse.getFailResponse();
	}

    @PostMapping("/update")
    @ResponseBody
    public Object update(@Valid FamilyDoctorTeamDTO familyDoctorTeamDTO, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
		return validErrorHandler(bindingResult);
	}
	String id = familyDoctorTeamDTO.getId();
	FamilyDoctorTeam model = beanCopy(familyDoctorTeamDTO, familyDoctorTeamService.get(id));
	if (familyDoctorTeamService.update(model) > 0) {
		return CommonResponse.getSuccessResponse(beanCopy(familyDoctorTeamService.get(id), new FamilyDoctorTeamVO()));
	}
	return CommonResponse.getFailResponse();
	}

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(familyDoctorTeamService.removeTeam(id));
    }
    
    @RequestMapping("/import")
    @ResponseBody
    public Object importTeam(@RequestParam("file") MultipartFile file,@RequestParam("unitId") String unitId) {
	try {
	    return CommonResponse.getSuccessResponse(familyDoctorTeamService.importTeam(file.getInputStream(), unitId));
	} catch (IOException e) {
	    return CommonResponse.getFailResponse("导入异常");
	}
    }
    
    @PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody FamilyDoctorTeamExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		FamilyDoctorTeamQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, familyDoctorTeamService.searchAll(query), FamilyDoctorTeam.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, familyDoctorTeamService.searchPage(query).getData(), FamilyDoctorTeam.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}
}