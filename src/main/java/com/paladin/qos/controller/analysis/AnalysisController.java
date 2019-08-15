package com.paladin.qos.controller.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataProcessContainer;

@Controller
@RequestMapping("/qos/analysis")
public class AnalysisController {

	@Autowired
	private DataProcessContainer dataProcessContainer;

	@GetMapping("/process/index")
	public Object index() {
		return "/qos/analysis/index";
	}
	
	
	@PostMapping("/process")
	@ResponseBody
	public Object process(DataProcessRequest request) {
		dataProcessContainer.processData(request.getStartTime(), request.getEndTime(), request.getUnitIds(), request.getEventId());
		return CommonResponse.getSuccessResponse();
	}
	
	
	@PostMapping("/process/data")
	@ResponseBody
	public Object getProcessData() {
		return CommonResponse.getSuccessResponse();
	}
}
