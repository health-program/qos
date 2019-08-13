package com.paladin.qos.controller.data;

import com.paladin.qos.controller.data.dto.DataProcessedDayExportCondition;
import com.paladin.qos.model.data.DataProcessedDay;
import com.paladin.qos.service.data.DataProcessedDayService;
import com.paladin.qos.service.data.dto.DataProcessedDayQuery;

import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.web.response.CommonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/qos/data/processed/day")
public class DataProcessedDayController extends ControllerSupport {

	@Autowired
	private DataProcessedDayService dataProcessedDayService;

	@GetMapping("/index")
	public String index() {
		return "/qos/data/data_processed_day_index";
	}

	@RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findPage(DataProcessedDayQuery query) {
		return CommonResponse.getSuccessResponse(dataProcessedDayService.searchPage(query));
	}

	@PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody DataProcessedDayExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		DataProcessedDayQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, dataProcessedDayService.searchAll(query), DataProcessedDay.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, dataProcessedDayService.searchPage(query).getData(), DataProcessedDay.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}
}