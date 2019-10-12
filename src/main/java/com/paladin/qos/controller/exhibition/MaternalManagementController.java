package com.paladin.qos.controller.exhibition;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.analysis.data.DataPointMonth;
import com.paladin.qos.service.analysis.data.DataResult;
import com.paladin.qos.service.exhibition.MaternalManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <孕产妇管理>
 * 
 * @author Huangguochen
 * @create 2019/8/22 14:12
 */

@Controller
@RequestMapping("/qos/exhibition/maternal")
public class MaternalManagementController {

	@Autowired
	private AnalysisService analysisService;

	@Autowired
	private MaternalManagementService maternalManagementService;

	@GetMapping("/index")
	public Object processIndex() {
		return "/qos/exhibition/maternal_index";
	}

	@GetMapping("/org/index")
	public Object processOrgIndex() {
		return "/qos/exhibition/maternal_org_index";
	}

	@PostMapping("/search/left")
	@ResponseBody
	public Object searchLeft(AnalysisRequest request) {
		List<String> ids = Arrays.asList("13305", "13303", "13314", "13321", "13306");
		List<DataResult<DataPointMonth>> list = new ArrayList<>(5);
		DataResult<DataPointMonth> set;
		for (String id : ids) {
			set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime(),null);
			list.add(set);
		}
		return CommonResponse.getSuccessResponse(list);
	}

	@PostMapping("/search/up")
	@ResponseBody
	public Object searchRightUp(AnalysisRequest request) {
		List<String> ids = Arrays.asList("13317", "13318", "13307", "13308");
		List<DataResult<DataPointMonth>> list = new ArrayList<>(4);
		DataResult<DataPointMonth> set;
		for (String id : ids) {
			set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime(),null);
			list.add(set);
		}
		return CommonResponse.getSuccessResponse(list);
	}

	@PostMapping("/search/down")
	@ResponseBody
	public Object searchRightDown(AnalysisRequest request) {
		List<String> ids = Arrays.asList("13309", "13310", "13311", "13312");
		List<DataResult<DataPointMonth>> list = new ArrayList<>(4);
		DataResult<DataPointMonth> set;
		for (String id : ids) {
			set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime(),null);
			list.add(set);
		}
		return CommonResponse.getSuccessResponse(list);
	}

	@PostMapping("/search/hqjc")
	@ResponseBody
	public Object searchHqjc(AnalysisRequest request) {
		List<String> ids = Arrays.asList("13301", "13302");
		List<DataResult<DataPointMonth>> list = new ArrayList<>(2);
		DataResult<DataPointMonth> set;
		for (String id : ids) {
			set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime(),null);
			list.add(set);
		}
		return CommonResponse.getSuccessResponse(list);
		/*
		 * return CommonResponse.getSuccessResponse(maternalManagementService.
		 * searchPremaritalCheckTotal(request));
		 */
	}

	@PostMapping("/search/yfsc")
	@ResponseBody
	public Object searchYfsc(AnalysisRequest request) {
		return CommonResponse.getSuccessResponse(analysisService.getDataSetOfMonth("13304", request.getStartTime(), request.getEndTime(),null));
	}

	@PostMapping("/search/fnb")
	@ResponseBody
	public Object searchFnb(AnalysisRequest request) {
		return CommonResponse.getSuccessResponse(analysisService.getDataSetOfMonth("13313", request.getStartTime(), request.getEndTime(),null));
	}

	@RequestMapping(value = "/search/yftj", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object searchYftj(AnalysisRequest request) {
		List<DataCountUnit> first = analysisService.countTotalNumByUnit("13315", request.getStartTime(), request.getEndTime(),null);
		List<DataCountUnit> cycle = analysisService.countTotalNumByUnit("13316", request.getStartTime(), request.getEndTime(),null);
		HashMap<String, List<DataCountUnit>> map = new HashMap<>(2);
		map.put("13315", first);
		map.put("13316", cycle);
		return CommonResponse.getSuccessResponse(map);
		/*
		 * return CommonResponse.getSuccessResponse(maternalManagementService.
		 * getMaternalCheckupData(request.getStartTime(),request.getEndTime()));
		 */
	}

	@RequestMapping(value = "/search/yfjk", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object searchYfjk(AnalysisRequest request) {
		List<String> ids = Arrays.asList("13319", "13320");
		List<DataResult<DataPointMonth>> list = new ArrayList<>(2);
		DataResult<DataPointMonth> set;
		for (String id : ids) {
			set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime(),null);
			list.add(set);
		}
		return CommonResponse.getSuccessResponse(list);
		/*
		 * return CommonResponse.getSuccessResponse(maternalManagementService.
		 * getBuildCardNumber(request.getStartTime(),request.getEndTime()));
		 */
	}

	@RequestMapping(value = "/search/wcq", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object searchWcq() {
		return CommonResponse.getSuccessResponse(maternalManagementService.getWcqData());
	}

	@RequestMapping(value = "/search/org", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object searchOrg(AnalysisRequest request) {
		List<DataCountUnit> early = analysisService.countTotalNumByUnit("13320", request.getStartTime(), request.getEndTime(),null);
		List<DataCountUnit> pregnant = analysisService.countTotalNumByUnit("13319", request.getStartTime(), request.getEndTime(),null);
		List<DataCountUnit> maternal = analysisService.countTotalNumByUnit("13314", request.getStartTime(), request.getEndTime(),null);
		HashMap<String, List<DataCountUnit>> map = new HashMap<>(3);
		map.put("13320", early);
		map.put("13319", pregnant);
		map.put("13314", maternal);
		return CommonResponse.getSuccessResponse(map);
		/*
		 * return CommonResponse.getSuccessResponse(maternalManagementService.
		 * getMaternalDataByOrg(request.getStartTime(),request.getEndTime()));
		 */
	}

}
