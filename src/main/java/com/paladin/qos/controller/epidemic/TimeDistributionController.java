package com.paladin.qos.controller.epidemic;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.epidemic.TimeDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <时间分布>
 *
 * @author Huangguochen
 * @create 2019/12/17 15:40
 */
@Controller
@RequestMapping("/qos/epidemic/time")
public class TimeDistributionController extends ControllerSupport {

    @Autowired
    private TimeDistributionService  timeDistributionService;

    @RequestMapping("/org/index")
    public String index() {
        return "/qos/epidemic/epidemic_time_distribution_org_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object find(@RequestParam(required = false) Date startTime, @RequestParam(required = false) Date endTime, @RequestParam(required = false) String  sickness, Integer type) {
        List<Map<String,Object>> lists = null;
        if ( 1 == type){
            lists = timeDistributionService.searchEpidemicDataByMonth(startTime,endTime,sickness);
        }else if ( 2 == type) {
            lists = timeDistributionService.searchPeoplesDataByMonth(startTime,endTime,sickness);
        }
        return CommonResponse.getSuccessResponse(lists);
    }

    @RequestMapping("/region/index")
    public String regionIndex() {
        return "/qos/epidemic/epidemic_time_distribution_region_index";
    }

    @RequestMapping("/find/region/page")
    @ResponseBody
    public Object findRegion(@RequestParam(required = false) Date startTime, @RequestParam(required = false) Date endTime, @RequestParam(required = false) String  sickness, Integer type) {
        List<Map<String,Object>> lists = null;
        if ( 1 == type){
            lists = timeDistributionService.searchRegionEpidemicDataByMonth(startTime,endTime,sickness);
        }else if ( 2 == type) {
            lists = timeDistributionService.searchRegionPeoplesDataByMonth(startTime,endTime,sickness);
        }
        return CommonResponse.getSuccessResponse(lists);
    }
}
