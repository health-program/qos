package com.paladin.qos.controller.countAntibiotics;

import com.paladin.common.controller.syst.SysControllerLog;
import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.countAntibiotics.dto.CountAntibioticsExportCondition;
import com.paladin.qos.model.countantibiotics.CountAntibiotics;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.model.infectioncomplication.Infection;
import com.paladin.qos.service.countantibiotics.CountAntibioticsService;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsDTO;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsQuery;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsVO;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.infectioncomplication.vo.InfectionVO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/qos/countantibiotics")
public class CountAntibioticsController extends ControllerSupport {

	@Autowired
	private CountAntibioticsService countAntibioticsService;

	@Autowired
	private DataUnitService dataUnitService;

	@GetMapping("/index")
	public Object index( Model model) {
		List<Integer> types=new ArrayList<>();
		types.add(DataUnit.TYPE_HOSPITAL);
	    	model.addAttribute("unit", dataUnitService.selectData(types));
		return "/qos/count_antibiotics/count_antibiotics_index";
	}

	@RequestMapping(value = "/find/all", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findAll(CountAntibioticsQuery query) {
		return CommonResponse.getSuccessResponse(countAntibioticsService.searchFindPage(query));
	}
	
	
	@RequestMapping(value = "/find/percent", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object percent(){
	    return CommonResponse.getSuccessResponse(countAntibioticsService.percent());
	}

	// 查看详情
	@GetMapping("/detail")
	public Object detail(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/qos/count_antibiotics/count_antibiotics_detail";
	}

	@GetMapping("/get")
	@ResponseBody
	public Object getDetail(@RequestParam String id, Model model) {
		return CommonResponse.getSuccessResponse(beanCopy(countAntibioticsService.get(id), new CountAntibioticsVO()));
	}

	@GetMapping("/add")
	public String add(){
	    return "/qos/count_antibiotics/count_antibiotics_add";
	}

	//报表页面
    @GetMapping("/data/index")
    public Object dataIndex(Model model) {
		model.addAttribute("unit", dataUnitService.findAll());
		return "/qos/count/antibiotics_data";
    }

	@RequestMapping(value = "/data/processing", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getAntibioticsData(CountAntibioticsRequest request) {

		String unitId=request.getUnitId();

		String monthStr=request.getMonth();



		return CommonResponse.getSuccessResponse(countAntibioticsService.getReportByQuery(unitId,monthStr));
	}

	@PostMapping("/save")
	@SysControllerLog(action = "新增抗菌药物使用统计")
	@ResponseBody
	public Object save(@Valid CountAntibioticsDTO countAntibioticsDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		int b = countAntibioticsService.judgeDate(countAntibioticsDTO.getUnitId(),countAntibioticsDTO.getInputDate());
		if(b>0){
		    return CommonResponse.getErrorResponse("该月已添加记录，可以前去修改！");
		}
		CountAntibiotics model = beanCopy(countAntibioticsDTO, new CountAntibiotics());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (countAntibioticsService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(countAntibioticsService.get(id), new CountAntibioticsVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@SysControllerLog(action = "删除抗菌药物使用统计")
	@ResponseBody
	public Object delete(@RequestParam String id) {
		return CommonResponse.getResponse(countAntibioticsService.removeByPrimaryKey(id));
	}
	

	   @PostMapping("/update")
	   @SysControllerLog(action = "修改抗菌药物使用统计")
	   @ResponseBody
	    public Object update(@Valid @RequestBody CountAntibioticsDTO countAntibioticsDTO, BindingResult bindingResult) {
			if (bindingResult.hasErrors()) {
				return validErrorHandler(bindingResult);
			}
			String id = countAntibioticsDTO.getId();
			CountAntibiotics model = beanCopy(countAntibioticsDTO, countAntibioticsService.get(id));
			if (countAntibioticsService.update(model) > 0) {
				return CommonResponse.getSuccessResponse(beanCopy(countAntibioticsService.get(id), new CountAntibioticsVO()));
			}
			return CommonResponse.getFailResponse();
		}
	
	


	@PostMapping("/export")
	@ResponseBody
	public Object export(@RequestBody CountAntibioticsExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		CountAntibioticsQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					List<CountAntibiotics> countAntibioticsList=countAntibioticsService.searchAll(query);
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition,
							getExportData(countAntibioticsList), CountAntibioticsVO.class));
				} else if (condition.isExportPage()) {
					List<CountAntibiotics> countAntibioticsList=countAntibioticsService.searchPage(query).getData();
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition,
							getExportData(countAntibioticsList), CountAntibioticsVO.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}

	private List<CountAntibioticsVO> getExportData(List<CountAntibiotics> countAntibioticsList) {
		List<CountAntibioticsVO> countAntibioticsVOS = new ArrayList<>();
		for (CountAntibiotics countAntibiotics : countAntibioticsList) {
			CountAntibioticsVO countAntibioticsVO = new CountAntibioticsVO();
			beanCopy(countAntibiotics, countAntibioticsVO);
			countAntibioticsVOS.add(countAntibioticsVO);
		}
		if (!CollectionUtils.isEmpty(countAntibioticsVOS))
			return countAntibioticsVOS;
		return null;
	}

}
