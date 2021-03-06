package com.paladin.qos.controller.exhibition;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.analysis.data.DataPointMonth;
import com.paladin.qos.service.analysis.data.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <儿童保健管理>
 *
 * @author Huangguochen
 * @create 2019/8/26 8:53
 */
@Controller
@RequestMapping("/qos/exhibition/child")
public class ChildCareManagementController {

	@Autowired
	private AnalysisService analysisService;

	@GetMapping("/index")
	public Object processIndex() {
		return "/qos/exhibition/child_care_index";
	}

	@GetMapping("/org/index")
	public Object processOrgIndex() {
		return "/qos/exhibition/child_care_org_index";
	}

	@PostMapping("/search/xsrfm")
	@ResponseBody
	public Object searchXsrfm(AnalysisRequest request) {
		List<String> ids = Arrays.asList("13202", "13201", "13211");
		List<DataResult<DataPointMonth>> list = new ArrayList<>(3);
		DataResult<DataPointMonth> set;
		for (String id : ids) {
			set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime(),null);
			list.add(set);
		}
		return CommonResponse.getSuccessResponse(list);
		/*
		 * return CommonResponse.getSuccessResponse(childCareManagementService.
		 * getNewbornChildbirthTotal(request));
		 */
	}

	@PostMapping("/search/all")
	@ResponseBody
	public Object searchAll(AnalysisRequest request) {
		List<String> ids = Arrays.asList("13203", "13204", "13205", "13207", "13206");
		List<DataResult<DataPointMonth>> list = new ArrayList<>(5);
		DataResult<DataPointMonth> set;
		for (String id : ids) {
			set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime(),null);
			list.add(set);
		}
		return CommonResponse.getSuccessResponse(list);
		/*
		 * return CommonResponse.getSuccessResponse(childCareManagementService.
		 * searchAllNumberData(request));
		 */
	}

	@RequestMapping(value = "/search/xzb", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object searchXzb(AnalysisRequest request) {
		return CommonResponse.getSuccessResponse(analysisService.countTotalNumByUnit("13208", request.getStartTime(), request.getEndTime(),null));
	}

	@RequestMapping(value = "/search/ettj", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object searchEttj(AnalysisRequest request) {
		List<String> ids = Arrays.asList("320583467170249","320583467170257","320583467170265","320583467170337","320583467170345","320583467170353","320583467170361"
		,"320583467170417","320583467170441","320583467170468","320583467170476","320583467170505","320583467170513");
		return CommonResponse.getSuccessResponse(analysisService.countTotalNumByUnit("13209", request.getStartTime(), request.getEndTime(),ids));
	}

	@RequestMapping(value = "/search/yesl", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object searchYesl(AnalysisRequest request) {
		List<DataCountUnit> units = analysisService.countTotalNumByUnit("13210", request.getStartTime(), request.getEndTime(), null);
		Collections.reverse(units);
		return CommonResponse.getSuccessResponse(units);
	}

	@RequestMapping(value = "/search/org", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object searchOrg(AnalysisRequest request) {
		List<DataCountUnit> visit = analysisService.countTotalNumByUnit("13321", request.getStartTime(), request.getEndTime(),null);
		List<DataCountUnit> male = analysisService.countTotalNumByUnit("13201", request.getStartTime(), request.getEndTime(),null);
		List<DataCountUnit> female = analysisService.countTotalNumByUnit("13202", request.getStartTime(), request.getEndTime(),null);
		List<DataCountUnit> card = analysisService.countTotalNumByUnit("13203", request.getStartTime(), request.getEndTime(),null);
		HashMap<String, List<DataCountUnit>> map = new HashMap<>(4);
		map.put("13321", visit);
		map.put("13201", male);
		map.put("13202", female);
		map.put("13203", card);
		return CommonResponse.getSuccessResponse(map);
		// return
		// CommonResponse.getSuccessResponse(childCareManagementService.getChildCareDataByOrg(request.getStartTime(),request.getEndTime()));
	}

}
