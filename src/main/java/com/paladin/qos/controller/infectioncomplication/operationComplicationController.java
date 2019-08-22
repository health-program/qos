package com.paladin.qos.controller.infectioncomplication;

import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.infectioncomplication.dto.operationComplicationExportCondition;

import com.paladin.qos.model.infectioncomplication.OperationComplication;
import com.paladin.qos.service.infectioncomplication.OperationComplicationService;
import com.paladin.qos.service.infectioncomplication.dto.OperationComplicationDTO;
import com.paladin.qos.service.infectioncomplication.dto.OperationComplicationQueryDTO;
import com.paladin.qos.service.infectioncomplication.vo.OperationComplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/qos/operationComplication")
public class operationComplicationController extends ControllerSupport {

	@Autowired
	OperationComplicationService operationComplicationService;
	

	@GetMapping("/index")
	public String index(Model model) {
		 Boolean canAdd=operationComplicationService.canAdd();
		 model.addAttribute("canAdd",canAdd);

		return "/qos/infectioncomplication/operationComplication_index";
	}

	// 初始化查询所有列表
	@RequestMapping("/find/all")
	@ResponseBody
	public Object findAll(OperationComplicationQueryDTO query) {
		return CommonResponse.getSuccessResponse(operationComplicationService.searchFindPage(query));
	}

	// 新增
	@RequestMapping("/add")
	public String add() {
		return "/qos/infectioncomplication/operationComplication_add";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(OperationComplicationVO vo) {
		try {
			vo.setId(UUIDUtil.createUUID());
			return CommonResponse.getSuccessResponse(operationComplicationService.insertInto(vo));
		} catch (Exception e) {
			return CommonResponse.getErrorResponse(e.getMessage());
		}
	}

	// 查看详情
	@GetMapping("/detail")
	public String detail(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/qos/infectioncomplication/operationComplication_detail";
	}

	@RequestMapping(value = "/get", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object get(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(operationComplicationService.queryById(id));
	}

	// 更新
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@RequestBody OperationComplicationDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}

		return CommonResponse.getSuccessResponse(operationComplicationService.updates(dto));
	}

	// 刪除
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(operationComplicationService.delete(id));
	}

	// 导出

	@RequestMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody operationComplicationExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		OperationComplicationQueryDTO query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition,
							operationComplicationService.searchAll(query), OperationComplication.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition,
							operationComplicationService.searchPage(query).getData(), OperationComplication.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}

}
