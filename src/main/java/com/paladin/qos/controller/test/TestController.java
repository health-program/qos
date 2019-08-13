package com.paladin.qos.controller.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.spring.DevCondition;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.analysis.DataProcessContainer;

@Controller
@RequestMapping("/test")
@Conditional(DevCondition.class)
public class TestController {

	@Autowired
	private DataProcessContainer dataProcessorManager;

	@GetMapping(value = "/index")
	public String index(HttpServletRequest request) {
		return "/test/index";
	}

	@RequestMapping("/process")
	@ResponseBody
	public Object process(TestDTO testDTO) {
		DataProcessor processor = dataProcessorManager.getDataProcessor(testDTO.getProcessorId());
		if (processor != null) {
			return CommonResponse.getSuccessResponse(processor.process(testDTO.getTimeType(), testDTO.getTime(), testDTO.getUnitId()));
		}
		return CommonResponse.getFailResponse();
	}

}
