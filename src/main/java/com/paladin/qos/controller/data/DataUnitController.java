package com.paladin.qos.controller.data;

import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.controller.countAntibiotics.CountAntibioticsRequest;
import com.paladin.qos.controller.data.dto.DataUnitExportCondition;
import com.paladin.qos.controller.data.dto.DataUtilRequest;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.data.dto.DataUnitQuery;
import com.paladin.qos.service.data.dto.DataUnitDTO;
import com.paladin.qos.service.data.vo.DataUnitVO;

import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
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
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.validation.Valid;

@Controller
@RequestMapping("/qos/data/unit")
public class DataUnitController extends ControllerSupport {

	@Autowired
	private DataUnitService dataUnitService;

	@GetMapping("/index")
	public String index() {
		return "/qos/data/data_unit_index";
	}

	@RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findPage(DataUnitQuery query) {
		return CommonResponse.getSuccessResponse(dataUnitService.searchPage(query));
	}
	
	@RequestMapping(value = "/find/select", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object find() {
		return CommonResponse.getSuccessResponse(dataUnitService.findAll());
	}

	@GetMapping("/get")
	@ResponseBody
	public Object getDetail(@RequestParam String id, Model model) {
		return CommonResponse.getSuccessResponse(beanCopy(dataUnitService.get(id), new DataUnitVO()));
	}

	@GetMapping("/add")
	public String addInput() {
		return "/qos/data/data_unit_add";
	}

	@GetMapping("/detail")
	public String detailInput(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/qos/data/data_unit_detail";
	}

	@PostMapping("/save")
	@ResponseBody
	public Object save(@Valid DataUnitDTO dataUnitDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		DataUnit model = beanCopy(dataUnitDTO, new DataUnit());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (dataUnitService.save(model) > 0) {
			DataConstantContainer.updateData();
			return CommonResponse.getSuccessResponse(beanCopy(dataUnitService.get(id), new DataUnitVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@PostMapping("/update")
	@ResponseBody
	public Object update(@Valid DataUnitDTO dataUnitDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = dataUnitDTO.getId();
		DataUnit model = beanCopy(dataUnitDTO, dataUnitService.get(id));
		if (dataUnitService.update(model) > 0) {
			DataConstantContainer.updateData();
			return CommonResponse.getSuccessResponse(beanCopy(dataUnitService.get(id), new DataUnitVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object delete(@RequestParam String id) {
		if (dataUnitService.removeByPrimaryKey(id) > 0) {
			DataConstantContainer.updateData();
			return CommonResponse.getSuccessResponse();
		}
		return CommonResponse.getFailResponse();
	}

	@PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody DataUnitExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		DataUnitQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, dataUnitService.searchAll(query), DataUnit.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, dataUnitService.searchPage(query).getData(), DataUnit.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}

	//报表页面
	@GetMapping("/bed/index")
	public Object dataIndex(Model model) {
		model.addAttribute("unit", dataUnitService.findAll());
		return "/qos/data/bed_data_index";
	}

	@RequestMapping(value = "/bed/processing", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getAntibioticsData(DataUtilRequest request) {
		String unitId=request.getUnitId();
		String dateStr=request.getDate();
		String yearStr="";
		String monthStr="";
		if (!StringUtils.isEmpty(dateStr)){
			String[] arr = dateStr.split("-");
			yearStr=arr[0];
			monthStr=arr[1];
		}
		return CommonResponse.getSuccessResponse(dataUnitService.getBedReportByQuery(unitId,monthStr,yearStr));
		}
}