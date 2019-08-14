package com.paladin.qos.controller.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataProcessContainer;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {

	@Autowired
	private DataProcessContainer dataProcessContainer;

	@PostMapping("/process")
	@ResponseBody
	public Object findPage(DataProcessRequest request) {
		dataProcessContainer.processData(request.getStartTime(), request.getEndTime(), request.getUnitIds(), request.getEventId());
		return CommonResponse.getSuccessResponse();
	}
}
