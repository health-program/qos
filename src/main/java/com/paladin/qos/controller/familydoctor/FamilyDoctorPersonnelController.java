package com.paladin.qos.controller.familydoctor;

import com.paladin.qos.controller.familydoctor.dto.FamilyDoctorPersonnelExportCondition;
import com.paladin.qos.model.familydoctor.FamilyDoctorPersonnel;
import com.paladin.qos.model.familydoctor.FamilyDoctorTeam;
import com.paladin.qos.service.familydoctor.FamilyDoctorPersonnelService;
import com.paladin.qos.service.familydoctor.FamilyDoctorTeamService;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorPersonnelQuery;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorPersonnelDTO;
import com.paladin.qos.service.familydoctor.vo.FamilyDoctorPersonnelVO;
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
@RequestMapping("/qos/family/doctor/personnel")
public class FamilyDoctorPersonnelController extends ControllerSupport {

    @Autowired
    private FamilyDoctorPersonnelService familyDoctorPersonnelService;
    
    @Autowired
    private FamilyDoctorTeamService familyDoctorTeamService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = FamilyDoctorPersonnelQuery.class)
    public String index(Model model,@RequestParam String teamId) {
	FamilyDoctorTeam team = familyDoctorTeamService.get(teamId);
	model.addAttribute("unitId",team.getUnitId());
	model.addAttribute("teamId",teamId);
        return "/qos/familydoctor/family_doctor_personnel_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = FamilyDoctorPersonnelQuery.class, paramIndex = 0)
    public Object findPage(FamilyDoctorPersonnelQuery query) {
        return CommonResponse.getSuccessResponse(familyDoctorPersonnelService.searchPage(query));
    }
    
    @RequestMapping(value = "/find/all", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findAll() {
        return CommonResponse.getSuccessResponse(familyDoctorPersonnelService.selectFindAll());
    }
    
    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(familyDoctorPersonnelService.get(id), new FamilyDoctorPersonnelVO()));
    }
    
    @GetMapping("/add")
    public String addInput(Model model,@RequestParam String teamId) {
	FamilyDoctorTeam team = familyDoctorTeamService.get(teamId);
	model.addAttribute("unitId",team.getUnitId());
	model.addAttribute("teamId",teamId);
        return "/qos/familydoctor/family_doctor_personnel_add";
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id,@RequestParam String teamId, Model model) {
	FamilyDoctorTeam team = familyDoctorTeamService.get(teamId);
	model.addAttribute("unitId",team.getUnitId());
    	model.addAttribute("id", id);
    	model.addAttribute("teamId",teamId);
        return "/qos/familydoctor/family_doctor_personnel_detail";
    }
    
    @PostMapping("/save")
    @ResponseBody
    public Object save(@Valid FamilyDoctorPersonnelDTO familyDoctorPersonnelDTO,@RequestParam String teamId,BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	if(familyDoctorPersonnelService.countPersonnel(familyDoctorPersonnelDTO.getName())>0){
	    return CommonResponse.getErrorResponse("姓名不能重复！");
	}
	FamilyDoctorPersonnel model = beanCopy(familyDoctorPersonnelDTO,new FamilyDoctorPersonnel());
	String id = UUIDUtil.createUUID();
	model.setId(id);
	model.setTeamId(teamId);
	FamilyDoctorTeam team = familyDoctorTeamService.get(teamId);
	model.setUnitId(team.getUnitId());
	if (familyDoctorPersonnelService.save(model) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy(familyDoctorPersonnelService.get(id),new FamilyDoctorPersonnelVO()));
	}
	return CommonResponse.getFailResponse();
    }

    @PostMapping("/update")
    @ResponseBody
    public Object update(@Valid FamilyDoctorPersonnelDTO familyDoctorPersonnelDTO,BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	String id = familyDoctorPersonnelDTO.getId();
	FamilyDoctorPersonnel model = beanCopy(familyDoctorPersonnelDTO,familyDoctorPersonnelService.get(id));
	if (familyDoctorPersonnelService.update(model) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy(familyDoctorPersonnelService.get(id),new FamilyDoctorPersonnelVO()));
	}
	return CommonResponse.getFailResponse();
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(familyDoctorPersonnelService.removeByPrimaryKey(id));
    }
    
    @RequestMapping("/import")
    @ResponseBody
    public Object importPersonnel(@RequestParam("filePersonnel") MultipartFile filePersonnel) {
	try {
	    return CommonResponse.getSuccessResponse(familyDoctorPersonnelService.importPersonnel(filePersonnel.getInputStream()));
	} catch (IOException e) {
	    return CommonResponse.getFailResponse("导入异常");
	}
    }
    
    @PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody FamilyDoctorPersonnelExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		FamilyDoctorPersonnelQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, familyDoctorPersonnelService.searchAll(query), FamilyDoctorPersonnel.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, familyDoctorPersonnelService.searchPage(query).getData(), FamilyDoctorPersonnel.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}
}