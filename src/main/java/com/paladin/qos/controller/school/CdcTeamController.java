package com.paladin.qos.controller.school;

import com.paladin.qos.controller.school.dto.CdcTeamExportCondition;
import com.paladin.qos.model.school.CdcTeam;
import com.paladin.qos.service.school.CdcTeamService;
import com.paladin.qos.service.school.dto.CdcTeamQuery;
import com.paladin.qos.service.school.dto.CdcTeamDTO;
import com.paladin.qos.service.school.vo.CdcTeamVO;
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
@RequestMapping("/qos/cdc/team")
public class CdcTeamController extends ControllerSupport {

    @Autowired
    private CdcTeamService cdcTeamService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = CdcTeamQuery.class)
    public String index() {
        return "/qos/school/cdc_team_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = CdcTeamQuery.class, paramIndex = 0)
    public Object findPage(CdcTeamQuery query) {
        return CommonResponse.getSuccessResponse(cdcTeamService.searchPage(query));
    }
    
    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(cdcTeamService.get(id), new CdcTeamVO()));
    }
    
    @GetMapping("/add")
    public String addInput() {
        return "/qos/school/cdc_team_add";
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/qos/school/cdc_team_detail";
    }
    
    @PostMapping("/save")
    @ResponseBody
    public Object save(@Valid CdcTeamDTO cdcTeamDTO, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	CdcTeam model = beanCopy(cdcTeamDTO, new CdcTeam());
	String id = UUIDUtil.createUUID();
	model.setId(id);
	if (cdcTeamService.save(model) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy( cdcTeamService.get(id), new CdcTeamVO()));
	}
	return CommonResponse.getFailResponse();
    }
    
    @RequestMapping("/import")
    @ResponseBody
    public Object importOrgSchool(@RequestParam("file") MultipartFile file) {
	try {
	    return CommonResponse.getSuccessResponse(cdcTeamService.importCdcTeam(file.getInputStream()));
	} catch (IOException e) {
	    return CommonResponse.getFailResponse("导入异常");
	}
    }

    @PostMapping("/update")
    @ResponseBody
    public Object update(@Valid CdcTeamDTO cdcTeamDTO,BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	String id = cdcTeamDTO.getId();
	CdcTeam model = beanCopy(cdcTeamDTO, cdcTeamService.get(id));
	if (cdcTeamService.update(model) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy(cdcTeamService.get(id), new CdcTeamVO()));
	}
	return CommonResponse.getFailResponse();
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(cdcTeamService.removeByPrimaryKey(id));
    }
    
    @PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody CdcTeamExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		CdcTeamQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, cdcTeamService.searchAll(query), CdcTeam.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, cdcTeamService.searchPage(query).getData(), CdcTeam.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}
}