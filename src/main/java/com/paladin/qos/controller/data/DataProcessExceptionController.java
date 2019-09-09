package com.paladin.qos.controller.data;

import com.paladin.qos.controller.data.dto.DataProcessExceptionExportCondition;
import com.paladin.qos.model.data.DataProcessException;
import com.paladin.qos.service.data.DataProcessExceptionService;
import com.paladin.qos.service.data.dto.DataProcessExceptionQuery;
import com.paladin.qos.service.data.vo.DataProcessExceptionVO;

import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.web.response.CommonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@Controller
@RequestMapping("/qos/data/process/exception")
public class DataProcessExceptionController extends ControllerSupport {

    @Autowired
    private DataProcessExceptionService dataProcessExceptionService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = DataProcessExceptionQuery.class)
    public String index() {
        return "/qos/data/data_process_exception_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = DataProcessExceptionQuery.class, paramIndex = 0)
    public Object findPage(DataProcessExceptionQuery query) {
        return CommonResponse.getSuccessResponse(dataProcessExceptionService.searchPage(query));
    }
    
    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(dataProcessExceptionService.get(id), new DataProcessExceptionVO()));
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/qos/data/data_process_exception_detail";
    }
    
    @PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody DataProcessExceptionExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		DataProcessExceptionQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, dataProcessExceptionService.searchAll(query), DataProcessException.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, dataProcessExceptionService.searchPage(query).getData(), DataProcessException.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}
}