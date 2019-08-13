package com.paladin.qos.controller.data;

import com.paladin.qos.controller.data.dto.DataProcessedYearExportCondition;
import com.paladin.qos.model.data.DataProcessedYear;
import com.paladin.qos.service.data.DataProcessedYearService;
import com.paladin.qos.service.data.dto.DataProcessedYearQuery;

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
@RequestMapping("/qos/data/processed/year")
public class DataProcessedYearController extends ControllerSupport {

    @Autowired
    private DataProcessedYearService dataProcessedYearService;

    @GetMapping("/index")
    public String index() {
        return "/qos/data/data_processed_year_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findPage(DataProcessedYearQuery query) {
        return CommonResponse.getSuccessResponse(dataProcessedYearService.searchPage(query));
    }
    
    @PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody DataProcessedYearExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		DataProcessedYearQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, dataProcessedYearService.searchAll(query), DataProcessedYear.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, dataProcessedYearService.searchPage(query).getData(), DataProcessedYear.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}
}