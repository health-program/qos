package com.paladin.qos.controller.report;

import com.paladin.common.controller.syst.SysControllerLog;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.model.report.ReportData;
import com.paladin.qos.service.report.ReportDataService;
import com.paladin.qos.service.report.dto.ReportDataDTO;
import com.paladin.qos.service.report.dto.ReportDataQueryDTO;
import com.paladin.qos.service.report.vo.ReportDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**   
 * @author MyKite
 * @version 2019年8月23日 下午4:03:11 
 */
@Controller
@RequestMapping("/qos/info")
public class XieGuanInfoController extends ControllerSupport{

    @Autowired
    private ReportDataService reportDataService;
    
    @GetMapping("/index")
    public String index(){
	return "/qos/report/xie_guan_info_index";
    }
    
    @RequestMapping("/find/all")
    @ResponseBody
    public Object findAll(ReportDataQueryDTO query) {
	query.setType(ReportData.REPORT_DATA_INFO);
	return CommonResponse.getSuccessResponse(reportDataService.searchPage(query));
    }
    
    @PostMapping("/save")
    @SysControllerLog(action = "新增卫计计生监督协管信息上报率")
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
    @SysControllerLog(action = "删除卫计计生监督协管信息上报率")
    @ResponseBody
    public Object delete(@RequestParam String id) {
	return CommonResponse.getSuccessResponse(reportDataService.removeByPrimaryKey(id));
    }
}
