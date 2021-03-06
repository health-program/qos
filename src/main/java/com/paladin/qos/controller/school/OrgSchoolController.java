package com.paladin.qos.controller.school;

import com.paladin.common.controller.syst.SysControllerLog;
import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.school.dto.OrgSchoolExportCondition;
import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.school.OrgSchoolService;
import com.paladin.qos.service.school.dto.OrgSchoolDTO;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import com.paladin.qos.service.school.vo.OrgSchoolVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * < 学校基础情况管理>
 * @author  MyKite
 * @version  [版本号, 2019年7月23日]
 */
@Controller
@RequestMapping("/qos/org/school")
public class OrgSchoolController extends ControllerSupport {

    @Autowired
    private OrgSchoolService orgSchoolService;

    @Autowired
	private DataUnitService dataUnitService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = OrgSchoolQuery.class)
    public String index(Model model) {
		model.addAttribute("unit", dataUnitService.byDataUnit());
		return "/qos/school/org_school_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = OrgSchoolQuery.class, paramIndex = 0)
    public Object findPage(OrgSchoolQuery query) {
	return CommonResponse.getSuccessResponse(orgSchoolService.searchFindPage(query));
    }

	@GetMapping("/data/unit")
	@ResponseBody
	public Object selectDataUnit() {
		return CommonResponse.getSuccessResponse(dataUnitService.byDataUnit());
	}

    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {
	return CommonResponse.getSuccessResponse(orgSchoolService.getSchool(id));
    }
    
    @GetMapping("/info")
    @ResponseBody
    public Object orgSchoolDetail(@RequestParam String id,@RequestParam String schoolYear){
	return CommonResponse.getSuccessResponse(orgSchoolService.orgSchoolDetail(id,schoolYear));
    }

    @GetMapping("/add")
    public String addInput() {
	return "/qos/school/org_school_add";
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
	model.addAttribute("id", id);
	return "/qos/school/org_school_detail";
    }

    @PostMapping("/save")
	@SysControllerLog(action = "新增学校信息")
    @ResponseBody
    public Object save(@Valid @RequestBody OrgSchoolDTO orgSchoolDTO, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	String id = UUIDUtil.createUUID();
	orgSchoolDTO.setId(id);
	try {
		if (orgSchoolService.schoolSave(orgSchoolDTO) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy( orgSchoolService.get(id), new OrgSchoolVO()));
		}
	} catch (Exception e) {
		return CommonResponse.getFailResponse(e.getMessage());
	}
	return CommonResponse.getFailResponse();
    }

    @PostMapping("/update")
	@SysControllerLog(action = "修改学校信息")
    @ResponseBody
    public Object update(@Valid @RequestBody OrgSchoolDTO orgSchoolDTO, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	String id = orgSchoolDTO.getId();
	if (orgSchoolService.schoolUpdate(orgSchoolDTO) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy(orgSchoolService.get(id), new OrgSchoolVO()));
	}
	return CommonResponse.getFailResponse();
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@SysControllerLog(action = "删除学校信息")
    @ResponseBody
    public Object delete(@RequestParam String id) {
	return CommonResponse.getResponse(orgSchoolService.deleteSchoolAndPeople(id));
    }
    
    @RequestMapping("/import")
    @ResponseBody
    public Object importOrgSchool(@RequestParam("file") MultipartFile file) {
	try {
	    return CommonResponse.getSuccessResponse(orgSchoolService.importOrgSchool(file.getInputStream()));
	} catch (IOException e) {
	    return CommonResponse.getFailResponse("导入异常");
	}
    }

    @PostMapping(value = "/export")
    @ResponseBody
    public Object export(@RequestBody OrgSchoolExportCondition condition) {
	if (condition == null) {
	    return CommonResponse.getFailResponse("导出失败：请求参数异常");
	}
	condition.sortCellIndex();
	OrgSchoolQuery query = condition.getQuery();
	try {
	    if (query != null) {
		if (condition.isExportAll()) {
		    return CommonResponse.getSuccessResponse("success",ExportUtil.export(condition,orgSchoolService.searchAll(query),OrgSchool.class));
		} else if (condition.isExportPage()) {
		    return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, orgSchoolService.searchPage(query).getData(), OrgSchool.class));
		}
	    }
	    return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
	} catch (IOException | ExcelWriteException e) {
	    return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
	}
    }
}