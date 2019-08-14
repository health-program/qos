package com.paladin.qos.controller.indication;

import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.GlobalProperties;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.indication.dto.InfectIndicationExportCondition;
import com.paladin.qos.controller.school.dto.OrgSchoolExportCondition;
import com.paladin.qos.model.infectIndication.InfectIndication;
import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.service.infectIndication.InfectIndicationService;
import com.paladin.qos.service.infectIndication.dto.InfectIndicationDTO;
import com.paladin.qos.service.infectIndication.dto.InfectIndicationQuery;
import com.paladin.qos.service.infectIndication.vo.InfectIndicationVO;
import com.paladin.qos.service.school.dto.OrgSchoolDTO;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import com.paladin.qos.service.school.vo.OrgSchoolVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;

/**   
 * @author MyKite
 * @version 2019年6月24日 下午3:04:58 
 */
@Controller
@RequestMapping("/qos/infectIndication")
public class InfectIndicationController extends ControllerSupport {

    @Autowired
    private InfectIndicationService infectIndicationService;

	@GetMapping("/index")
	@QueryInputMethod(queryClass = InfectIndicationQuery.class)
	public String index(Model model) {
		Boolean canAdd=infectIndicationService.canAdd();
		model.addAttribute("canAdd",canAdd);
		return "/qos/infectIndication/infectIndication_index";
	}

	@RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@QueryOutputMethod(queryClass = InfectIndicationQuery.class, paramIndex = 0)
	public Object findPage(InfectIndicationQuery query) {
		return CommonResponse.getSuccessResponse(infectIndicationService.searchFindPage(query));
	}

	@GetMapping("/get")
	@ResponseBody
	public Object getDetail(@RequestParam String id, Model model) {
		return CommonResponse.getSuccessResponse(beanCopy(infectIndicationService.get(id),new InfectIndicationVO()));
	}

	@GetMapping("/add")
	public String addInput(Model model) {
		return "/qos/infectIndication/infectIndication_add";
	}

	@GetMapping("/detail")
	public String detailInput(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/qos/infectIndication/infectIndication_detail";
	}

	@PostMapping("/save")
	@ResponseBody
	public Object save(@Valid InfectIndicationDTO infectIndicationDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = UUIDUtil.createUUID();
		infectIndicationDTO.setId(id);
		if (infectIndicationService.saveInfectIndication(infectIndicationDTO) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(infectIndicationService.get(id), new InfectIndicationVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@PostMapping("/update")
	@ResponseBody
	public Object update(@Valid @RequestBody InfectIndicationDTO infectIndicationDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = infectIndicationDTO.getId();
		if (infectIndicationService.updateInfectIndication(infectIndicationDTO) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(infectIndicationService.get(id), new InfectIndicationVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object delete(@RequestParam String id) {
		return CommonResponse.getResponse(infectIndicationService.delete(id));
	}

	@PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody InfectIndicationExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		InfectIndicationQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition,infectIndicationService.searchAll(query),InfectIndication.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, infectIndicationService.searchPage(query).getData(), InfectIndication.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}

}


 