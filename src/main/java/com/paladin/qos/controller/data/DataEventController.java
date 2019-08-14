package com.paladin.qos.controller.data;

import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.controller.data.dto.DataEventExportCondition;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.service.data.DataEventService;
import com.paladin.qos.service.data.dto.DataEventQuery;
import com.paladin.qos.service.data.dto.DataEventDTO;
import com.paladin.qos.service.data.vo.DataEventVO;

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

import java.io.IOException;

import javax.validation.Valid;

@Controller
@RequestMapping("/qos/data/event")
public class DataEventController extends ControllerSupport {

    @Autowired
    private DataEventService dataEventService;

    @GetMapping("/index")
    public String index() {
        return "/qos/data/data_event_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findPage(DataEventQuery query) {
        return CommonResponse.getSuccessResponse(dataEventService.searchPage(query));
    }
    
    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(dataEventService.get(id), new DataEventVO()));
    }
    
    @GetMapping("/add")
    public String addInput() {
        return "/qos/data/data_event_add";
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/qos/data/data_event_detail";
    }
    
    @PostMapping("/save")
	@ResponseBody
    public Object save(@Valid DataEventDTO dataEventDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        DataEvent model = beanCopy(dataEventDTO, new DataEvent());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (dataEventService.save(model) > 0) {
			DataConstantContainer.updateData();
			return CommonResponse.getSuccessResponse(beanCopy(dataEventService.get(id), new DataEventVO()));
		}
		return CommonResponse.getFailResponse();
	}

    @PostMapping("/update")
	@ResponseBody
    public Object update(@Valid DataEventDTO dataEventDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = dataEventDTO.getId();
		DataEvent model = beanCopy(dataEventDTO, dataEventService.get(id));
		if (dataEventService.update(model) > 0) {
			DataConstantContainer.updateData();
			return CommonResponse.getSuccessResponse(beanCopy(dataEventService.get(id), new DataEventVO()));
		}
		return CommonResponse.getFailResponse();
	}

    @PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody DataEventExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		DataEventQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, dataEventService.searchAll(query), DataEvent.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, dataEventService.searchPage(query).getData(), DataEvent.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}
}