package com.paladin.qos.controller.epidemic;

import com.paladin.common.controller.syst.SysControllerLog;
import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.epidemic.dto.EpidemicExportCondition;
import com.paladin.qos.model.epidemic.EpidemicSituation;
import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.model.school.OrgSchoolPeople;
import com.paladin.qos.service.epidemic.EpidemicSituationService;
import com.paladin.qos.service.epidemic.dto.EpidemicSituationDTO;
import com.paladin.qos.service.epidemic.dto.EpidemicSituationQueryDTO;
import com.paladin.qos.service.epidemic.vo.EpidemicSituationVO;
import com.paladin.qos.service.school.OrgSchoolNameService;
import com.paladin.qos.service.school.OrgSchoolPeopleService;
import com.paladin.qos.service.school.OrgSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * 疫情管理
 * 
 * @author 黄伟华
 * @version 2019年6月11日 下午1:39:24
 */
@Controller
@RequestMapping("/qos/epidemic")
public class EpidemicController extends ControllerSupport {

    @Autowired
    private EpidemicSituationService epidemicSituationService;

    @Autowired
    private OrgSchoolNameService orgSchoolNameService;
    @Autowired
    private OrgSchoolPeopleService orgSchoolPeopleService;
    @Autowired
    private OrgSchoolService orgSchoolService;

    @RequestMapping("/index")
    @QueryInputMethod(queryClass = EpidemicSituationQueryDTO.class)
    public String index() {
	return "/qos/epidemic/epidemic_situation_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    @QueryOutputMethod(queryClass = EpidemicSituationQueryDTO.class, paramIndex = 0)
    public Object find(EpidemicSituationQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicSituationService
		.searchFindPage(query));
    }

    @RequestMapping("/find/select")
    @ResponseBody
    public Object schoolName() {
	return CommonResponse.getSuccessResponse(orgSchoolNameService
		.schoolNameFind());
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
	model.addAttribute("id", id);
	return "/qos/epidemic/epidemic_situation_detail";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id) {
	return CommonResponse.getSuccessResponse(beanCopy(
		epidemicSituationService.get(id), new EpidemicSituationVO()));
    }

    @RequestMapping("/add")
    public String add() {
	return "/qos/epidemic/epidemic_situation_add";
    }

    @RequestMapping("/traceability/index")
    public String traceabilityIndex() {
	return "/qos/epidemic/epidemic_situation_statistics_count";
    }

    /**
     * 溯源指标
     * 
     * @param query
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/traceability/count")
    @ResponseBody
    public Object traceabilityStatistics(EpidemicSituationQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicSituationService.dataTraceabilityRate(query));
    }

    @RequestMapping("/save")
    @ResponseBody
    @SysControllerLog(action = "新增学校疫情")
    public Object save(@Valid EpidemicSituationDTO dto,BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	String id = UUIDUtil.createUUID();
	dto.setId(id);

	EpidemicSituation eSituation = beanCopy(dto, new EpidemicSituation());

	// 验证班级id
	OrgSchoolPeople orgSchoolPeople = orgSchoolPeopleService.get(eSituation.getGrade());
	if (orgSchoolPeople == null) {
	    return CommonResponse.getFailResponse("只可以选择班级项！");
	}

	OrgSchool orgSchool = orgSchoolService.get(orgSchoolPeople.getSchoolId());
	if (orgSchool == null) {
	    return CommonResponse.getFailResponse("该学校信息不存在，请确认！");
	}

	eSituation.setSchoolYear(orgSchool.getSchoolYear());
	eSituation.setIncidentUnit(orgSchool.getParentSchoolId());

	if (epidemicSituationService.save(eSituation) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy(epidemicSituationService.get(id),new EpidemicSituationVO()));
	}
	return CommonResponse.getFailResponse();
    }

    @RequestMapping("/update")
    @SysControllerLog(action = "修改学校疫情")
    @ResponseBody
    public Object update(@Valid EpidemicSituationDTO dto,
	    BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	String id = dto.getId();
	try {

	    EpidemicSituation eSituation = epidemicSituationService.get(id);
	    if (eSituation == null) {
		return CommonResponse.getFailResponse("没写选择需要修改的信息！");
	    }

	    eSituation = beanCopy(dto, eSituation);

	    // 验证班级id
	    OrgSchoolPeople orgSchoolPeople = orgSchoolPeopleService.get(eSituation.getGrade());
	    if (orgSchoolPeople == null) {
		return CommonResponse.getFailResponse("只可以选择班级项！");
	    }

	    OrgSchool orgSchool = orgSchoolService.get(orgSchoolPeople.getSchoolId());
	    if (orgSchool == null) {
		return CommonResponse.getFailResponse("该学校信息不存在，请确认！");
	    }

	    eSituation.setSchoolYear(orgSchool.getSchoolYear());
	    eSituation.setIncidentUnit(orgSchool.getParentSchoolId());

	    if (epidemicSituationService.update(eSituation) > 0) {
		return CommonResponse.getSuccessResponse(beanCopy(epidemicSituationService.get(id),new EpidemicSituationVO()));
	    }
	} catch (Exception e) {
	    return CommonResponse.getFailResponse(e.getMessage());
	}
	return CommonResponse.getFailResponse();
    }

    @RequestMapping("/remove")
    @SysControllerLog(action = "删除学校疫情")
    @ResponseBody
    public Object remove(@RequestParam String id) {
	return CommonResponse.getSuccessResponse(epidemicSituationService.removeByPrimaryKey(id));
    }

    @RequestMapping("/import")
    @ResponseBody
    public Object importEpidemic(@RequestParam("file") MultipartFile file) {
	try {
	    return CommonResponse.getSuccessResponse(epidemicSituationService.importEpidemic(file.getInputStream()));
	} catch (IOException e) {
	    return CommonResponse.getFailResponse("导入异常");
	}
    }

    @PostMapping(value = "/export")
    @ResponseBody
    public Object export(@RequestBody EpidemicExportCondition condition) {
	if (condition == null) {
	    return CommonResponse.getFailResponse("导出失败：请求参数异常");
	}
	condition.sortCellIndex();
	EpidemicSituationQueryDTO query = condition.getQuery();
	try {
	    if (query != null) {
		if (condition.isExportAll()) {
		    return CommonResponse.getSuccessResponse("success",ExportUtil.export(condition,epidemicSituationService.searchAll(query),EpidemicSituation.class));
		} else if (condition.isExportPage()) {
		    return CommonResponse.getSuccessResponse("success",ExportUtil.export(condition, epidemicSituationService.searchPage(query).getData(),EpidemicSituation.class));
		}
	    }
	    return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
	} catch (IOException | ExcelWriteException e) {
	    return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
	}
    }

}
