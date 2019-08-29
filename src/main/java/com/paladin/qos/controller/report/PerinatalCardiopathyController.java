package com.paladin.qos.controller.report;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.model.report.ReportData;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.report.ReportDataService;
import com.paladin.qos.service.report.dto.ReportDataDTO;
import com.paladin.qos.service.report.dto.ReportDataQueryDTO;
import com.paladin.qos.service.report.vo.ReportDataVO;

/**   
 * @author MyKite
 * @version 2019年8月23日 下午3:58:24 
 */
@Controller
@RequestMapping("/qos/perinatal/cardiopathy")
public class PerinatalCardiopathyController extends ControllerSupport{

    @Autowired
    private ReportDataService reportDataService;
    
    @Autowired
    private DataUnitService dataUnitService;
    
    @GetMapping("/index")
    public String index(Model model){
	model.addAttribute("unit", dataUnitService.selectData());
	return "/qos/report/perinatal_cardiopathy_index";
    }
    
    @RequestMapping("/find/all")
    @ResponseBody
    public Object findAll(ReportDataQueryDTO query) {
	query.setType(ReportData.REPORT_DATA_PERINATAL);
	return CommonResponse.getSuccessResponse(reportDataService.findAll(query));
    }
    
    @PostMapping("/save")
    @ResponseBody
    public Object save(@Valid ReportDataDTO dto,BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	if(reportDataService.judgePerinatal(dto.getUnitId()) > 0){
	    return CommonResponse.getErrorResponse("添加记录未满一个月");
	}
	ReportData data = beanCopy(dto,new ReportData());
	String id = UUIDUtil.createUUID();
	data.setId(id);
	data.setType(ReportData.REPORT_DATA_PERINATAL);
	if (reportDataService.save(data) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy( reportDataService.get(id),new ReportDataVO()));
	}
	return CommonResponse.getFailResponse();
    }
    
    @GetMapping("/detail")
    public Object detail(@RequestParam String id, Model model) {
	model.addAttribute("id", id);
	return "/qos/report/perinatal_cardiopathy_detail";
    }
    
    @GetMapping("/add")
    public String add() {
	return "/qos/report/perinatal_cardiopathy_add";
    }
    
    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {
	return CommonResponse.getSuccessResponse(beanCopy(reportDataService.get(id), new ReportDataVO()));
    }
    
    @PostMapping("/update")
    @ResponseBody
    public Object update(@Valid ReportDataDTO dto, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	String id = dto.getId();
	ReportData model = reportDataService.get(id);
	model.setUnitId(dto.getUnitId());
	model.setData(dto.getData());
	if (reportDataService.update(model) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy(reportDataService.get(id), new ReportDataVO()));
	}
	return CommonResponse.getFailResponse();
    }
    
    
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
	return CommonResponse.getSuccessResponse(reportDataService.removeByPrimaryKey(id));
    }
}
