package com.paladin.qos.controller.statistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.statistics.OpdLogService;
import com.paladin.qos.service.statistics.dto.OpdQuery;

/**
 * 门诊日志汇总表
 * 
 * @author MyKite
 * @version 2019年11月16日 下午12:31:13
 */
@Controller
@RequestMapping("/qos/com/log")
public class ComLogController {

    @Autowired
    OpdLogService opdLogService;

    @RequestMapping("/index")
    @QueryInputMethod(queryClass = OpdQuery.class)
    public String index(Model model) {
	return "/qos/statistics/com_log_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public Object findPage(OpdQuery query) {
	return CommonResponse.getSuccessResponse(opdLogService.searchComPage(query));
    }
}
