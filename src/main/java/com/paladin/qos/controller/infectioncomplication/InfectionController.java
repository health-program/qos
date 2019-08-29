package com.paladin.qos.controller.infectioncomplication;

import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.infectioncomplication.dto.InfectionExportCondition;
import com.paladin.qos.model.infectioncomplication.Infection;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.infectioncomplication.InfectionService;
import com.paladin.qos.service.infectioncomplication.dto.InfectionDTO;
import com.paladin.qos.service.infectioncomplication.dto.InfectionQuery;
import com.paladin.qos.service.infectioncomplication.vo.InfectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**   
 * @author luckywcw
 * @version 2019年6月24日 下午3:04:58 
 */
@Controller
@RequestMapping("/qos/infectioncomplication/infection")
public class InfectionController extends ControllerSupport {

    @Autowired
    private InfectionService infectionService;

    @Autowired
    private DataUnitService dataUnitService;

	@GetMapping("/index")
	@QueryInputMethod(queryClass = InfectionQuery.class)
	public String index(Model model) {
		model.addAttribute("unit", dataUnitService.selectData());
		return "/qos/infectioncomplication/infection_index";
	}

	@RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@QueryOutputMethod(queryClass = InfectionQuery.class, paramIndex = 0)
	public Object findPage(InfectionQuery query) {
		return CommonResponse.getSuccessResponse(infectionService.searchFindPage(query));
	}

	@GetMapping("/get")
	@ResponseBody
	public Object getDetail(@RequestParam String id, Model model) {
		return CommonResponse.getSuccessResponse(beanCopy(infectionService.get(id),new InfectionVO()));
	}

	@GetMapping("/add")
	public String addInput(Model model) {
		return "/qos/infectioncomplication/infection_add";
	}

	@GetMapping("/detail")
	public String detailInput(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/qos/infectioncomplication/infection_detail";
	}

	@PostMapping("/save")
	@ResponseBody
	public Object save(@Valid InfectionDTO infectionDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		if(!infectionService.canAdd(infectionDTO.getUnitId())){
			return CommonResponse.getErrorResponse("添加记录未满半年");
		}
		String id = UUIDUtil.createUUID();
		infectionDTO.setId(id);
		if (infectionService.saveInfectIndication(infectionDTO) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(infectionService.get(id), new InfectionVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@PostMapping("/update")
	@ResponseBody
	public Object update(@Valid @RequestBody InfectionDTO infectionDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = infectionDTO.getId();
		if (infectionService.updateInfectIndication(infectionDTO) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(infectionService.get(id), new InfectionVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object delete(@RequestParam String id) {
		return CommonResponse.getResponse(infectionService.delete(id));
	}
	
	@GetMapping("/charts/index")
	public String chartsIndex() {
		return "/qos/infectioncomplication/infection_data";
	}
	
	@GetMapping("/charts/count")
	@ResponseBody
	public Object infectionCount(InfectionQuery query){
	    return CommonResponse.getSuccessResponse(infectionService.infectionCount(query));
	}
	
	@GetMapping("/charts/count/yaer")
	@ResponseBody
	public Object infectionCountYaer(InfectionQuery query){
	    return CommonResponse.getSuccessResponse(infectionService.infectionCountYaer(query));
	}
	
	
	@PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody InfectionExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		InfectionQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, infectionService.searchAll(query),Infection.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, infectionService.searchPage(query).getData(), Infection.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}

}


 