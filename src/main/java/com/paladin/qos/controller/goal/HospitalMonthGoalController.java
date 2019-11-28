package com.paladin.qos.controller.goal;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.StringUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.model.goal.HospitalMonthGoal;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.goal.HospitalMonthGoalService;
import com.paladin.qos.service.goal.dto.HospitalMonthGoalDTO;
import com.paladin.qos.service.goal.dto.HospitalMonthGoalQuery;
import com.paladin.qos.service.goal.vo.HospitalMonthGoalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 医院月度指标
 */
@Controller
@RequestMapping("/qos/hospital/month/goal")
public class HospitalMonthGoalController extends ControllerSupport {

    @Autowired
    private HospitalMonthGoalService hospitalMonthGoalService;
    @Autowired
    private DataUnitService dataUnitService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = HospitalMonthGoalQuery.class)
    public String index(Model model) {
        List<Integer> types=new ArrayList<>();
        types.add(DataUnit.TYPE_HOSPITAL);
        model.addAttribute("unit", dataUnitService.selectData(types));
        return "/qos/goal/month_goal_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = HospitalMonthGoalQuery.class, paramIndex = 0)
    public Object findPage(HospitalMonthGoalQuery query) {
        return CommonResponse.getSuccessResponse(hospitalMonthGoalService.searchFindPage(query));
    }

    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {
        return CommonResponse.getSuccessResponse(hospitalMonthGoalService.get(id));
    }

    @GetMapping("/add")
    public String addInput() {
        return "/qos/goal/month_goal_add";
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return "/qos/goal/month_goal_detail";
    }

    @PostMapping("/save")
    @ResponseBody
    public Object save(@Valid HospitalMonthGoalDTO hospitalMonthGoalDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        String id = UUIDUtil.createUUID();
        hospitalMonthGoalDTO.setId(id);
        try {
            if (hospitalMonthGoalService.saveGoal(beanCopy(hospitalMonthGoalDTO,new HospitalMonthGoal())) > 0) {
                return CommonResponse.getSuccessResponse(beanCopy( hospitalMonthGoalService.get(id), new HospitalMonthGoalVO()));
            }
        } catch (Exception e) {
            return CommonResponse.getFailResponse(e.getMessage());
        }
        return CommonResponse.getFailResponse();
    }

    @PostMapping("/update")
    @ResponseBody
    public Object update(@Valid HospitalMonthGoalDTO hospitalMonthGoalDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        if(StringUtil.isEmpty(hospitalMonthGoalDTO.getId())){
            return CommonResponse.getFailResponse("数据标识为空");
        }
        HospitalMonthGoal oldRecord = hospitalMonthGoalService.get(hospitalMonthGoalDTO.getId());//历史记录
        if (oldRecord == null) {
            return CommonResponse.getFailResponse("数据库中已不存在该记录");
        }

        if (hospitalMonthGoalService.updateGoal(hospitalMonthGoalDTO, oldRecord) > 0) {
            return CommonResponse.getSuccessResponse(beanCopy(hospitalMonthGoalService.get(hospitalMonthGoalDTO.getId()), new HospitalMonthGoalVO()));
        }
        return CommonResponse.getFailResponse();
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(hospitalMonthGoalService.deleteById(id));
    }


}