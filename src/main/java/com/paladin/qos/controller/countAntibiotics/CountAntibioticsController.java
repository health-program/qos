package com.paladin.qos.controller.countAntibiotics;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

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

import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.countAntibiotics.dto.CountAntibioticsExportCondition;
import com.paladin.qos.model.countantibiotics.CountAntibiotics;
import com.paladin.qos.service.countantibiotics.CountAntibioticsService;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsDTO;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsQuery;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsVO;
import com.paladin.qos.service.data.DataUnitService;

import org.apache.commons.lang3.StringUtils;
@Controller
@RequestMapping("/qos/countantibiotics")
public class CountAntibioticsController extends ControllerSupport {

	@Autowired
	private CountAntibioticsService countAntibioticsService;

	@Autowired
	private DataUnitService dataUnitService;

	@GetMapping("/index")
	public Object index( Model model) {
	    	model.addAttribute("unit", dataUnitService.findAll());
		return "/qos/count_antibiotics/count_antibiotics_index";
	}

	@RequestMapping(value = "/find/all", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findAll(CountAntibioticsDTO query) {
		return CommonResponse.getSuccessResponse(countAntibioticsService.searchFindPage(query));
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
		Date month=null;
		if (StringUtils.isNotBlank(monthStr)){
			monthStr=monthStr+"-01";
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			try {
				month=format.parse(monthStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}


		return CommonResponse.getSuccessResponse(countAntibioticsService.getReportByQuery(unitId,month));
	}

	// 保存
	@PostMapping("/save")
	@ResponseBody
	public Object save(@Valid CountAntibioticsDTO CountAntibioticsDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		int b = countAntibioticsService.judge(CountAntibioticsDTO.getUnitId());
		if(b>0){
		    return CommonResponse.getErrorResponse("添加记录未满一个月");
		}
		CountAntibiotics model = beanCopy(CountAntibioticsDTO, new CountAntibiotics());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (countAntibioticsService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(countAntibioticsService.get(id), new CountAntibioticsVO()));
		}
		return CommonResponse.getFailResponse();
	}

	// 删除
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object delete(@RequestParam String id) {
		return CommonResponse.getResponse(countAntibioticsService.removeByPrimaryKey(id));
	}
	
	
	// 更新
	   @PostMapping("/update")
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
	
	

	// 导出
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
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition,
							countAntibioticsService.searchAll(query), CountAntibiotics.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition,
							countAntibioticsService.searchPage(query).getData(), CountAntibiotics.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}

}
