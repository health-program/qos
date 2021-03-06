package com.paladin.qos.controller.school;

import com.github.pagehelper.util.StringUtil;
import com.paladin.common.controller.syst.SysControllerLog;
import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.school.dto.OrgSchoolNameExportCondition;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.model.school.OrgSchoolName;
import com.paladin.qos.service.school.OrgSchoolNameService;
import com.paladin.qos.service.school.dto.OrgSchoolNameDTO;
import com.paladin.qos.service.school.dto.OrgSchoolNameQuery;
import com.paladin.qos.service.school.vo.OrgSchoolNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * < 学校管理>
 * @author  MyKite
 * @version  [版本号, 2019年7月23日]
 */
@Controller
@RequestMapping("/qos/org/school/name")
public class OrgSchoolNameController extends ControllerSupport {

    @Autowired
    private OrgSchoolNameService orgSchoolNameService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = OrgSchoolNameQuery.class)
    public String index() {
        return "/qos/school/org_school_name_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = OrgSchoolNameQuery.class, paramIndex = 0)
    public Object findPage(OrgSchoolNameQuery query) {
        return CommonResponse.getSuccessResponse(orgSchoolNameService.searchPage(query));
    }
    
    @RequestMapping(value = "/find/all", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findAll() {
    	return CommonResponse.getSuccessResponse(orgSchoolNameService.searchFind());
    }
    
    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(orgSchoolNameService.get(id), new OrgSchoolNameVO()));
    }
    
    @GetMapping("/add")
    public String addInput() {
        return "/qos/school/org_school_name_add";
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/qos/school/org_school_name_detail";
    }
    
    @PostMapping("/save")
	@SysControllerLog(action = "新增学校名称")
    @ResponseBody
    public Object save(@Valid OrgSchoolNameDTO dto,BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	if(orgSchoolNameService.judge(dto.getSchoolFullName()) !=null){
	    return CommonResponse.getErrorResponse("学校名称已存在");
	}
	OrgSchoolName model = beanCopy(dto, new OrgSchoolName());
	String id = UUIDUtil.createUUID();
	if(StringUtil.isEmpty(model.getParentId())){
	    model.setParentId(null);
	}
	model.setId(id);
	if (orgSchoolNameService.save(model) > 0) {
		QosUserSession userSession = QosUserSession.getCurrentUserSession();
		String[] agencyId = userSession.getAgencyIds();
		if(agencyId.length >0 && agencyId != null){
			model.setRegion(agencyId);
		}
	    return CommonResponse.getSuccessResponse(beanCopy(orgSchoolNameService.get(id), new OrgSchoolNameVO()));
	}
	return CommonResponse.getFailResponse();
    }

    @PostMapping("/update")
	@ResponseBody
    public Object update(@Valid OrgSchoolNameDTO orgSchoolNameDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = orgSchoolNameDTO.getId();
		OrgSchoolName model = beanCopy(orgSchoolNameDTO, orgSchoolNameService.get(id));
		if (orgSchoolNameService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(orgSchoolNameService.get(id), new OrgSchoolNameVO()));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@SysControllerLog(action = "删除学校名称")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(orgSchoolNameService.removeBySchoolName(id));
    }
    
    @PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody OrgSchoolNameExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		OrgSchoolNameQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, orgSchoolNameService.searchAll(query), OrgSchoolName.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, orgSchoolNameService.searchPage(query).getData(), OrgSchoolName.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}
}