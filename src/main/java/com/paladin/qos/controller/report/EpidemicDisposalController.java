package com.paladin.qos.controller.report;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.paladin.qos.service.report.ReportDataService;
import com.paladin.qos.service.report.dto.ReportDataDTO;
import com.paladin.qos.service.report.dto.ReportDataQueryDTO;
import com.paladin.qos.service.report.vo.ReportDataVO;

/**   
 * @author MyKite
 * @version 2019年8月23日 下午4:01:55 
 */
@Controller
@RequestMapping("/qos/epidemic/disposal")
public class EpidemicDisposalController extends ControllerSupport{

    @Autowired
    private ReportDataService reportDataService;
    
    @GetMapping("/index")
    public String index(){
	return "/qos/report/epidemic_disposal_index";
    }
    
    @RequestMapping("/find/all")
    @ResponseBody
    public Object findAll(ReportDataQueryDTO query) {
	query.setType(ReportData.REPORT_DATA_EPIDEMIC);
	return CommonResponse.getSuccessResponse(reportDataService.searchPage(query));
    }
    
    @PostMapping("/save")
    @ResponseBody
    public Object save(@Valid ReportDataDTO dto,BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return validErrorHandler(bindingResult);
	}
	if(reportDataService.judge(dto.getType()) > 0){
	    return CommonResponse.getErrorResponse("添加记录未满一个月");
	}
	ReportData data = beanCopy(dto,new ReportData());
	String id = UUIDUtil.createUUID();
	data.setId(id);
	if (reportDataService.save(data) > 0) {
	    return CommonResponse.getSuccessResponse(beanCopy( reportDataService.get(id),new ReportDataVO()));
	}
	return CommonResponse.getFailResponse();
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
	return CommonResponse.getSuccessResponse(reportDataService.removeByPrimaryKey(id));
    }
    
}
