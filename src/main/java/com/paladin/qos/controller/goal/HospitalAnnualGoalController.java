package com.paladin.qos.controller.goal;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.StringUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.model.goal.HospitalAnnualGoal;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.goal.HospitalAnnualGoalService;
import com.paladin.qos.service.goal.dto.HospitalAnnualGoalDTO;
import com.paladin.qos.service.goal.dto.HospitalAnnualGoalQuery;
import com.paladin.qos.service.goal.vo.HospitalAnnualGoalVO;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 医院年度指标
 */
@Controller
@RequestMapping("/qos/hospital/annual/goal")
public class HospitalAnnualGoalController extends ControllerSupport {

    @Autowired
    private HospitalAnnualGoalService hospitalAnnualGoalService;
    @Autowired
    private DataUnitService dataUnitService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = HospitalAnnualGoalQuery.class)
    public String index(Model model) {
        List<Integer> types=new ArrayList<>();
        types.add(DataUnit.TYPE_HOSPITAL);
        model.addAttribute("unit", dataUnitService.selectData(types));
        return "/qos/goal/annual_goal_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = HospitalAnnualGoalQuery.class, paramIndex = 0)
    public Object findPage(HospitalAnnualGoalQuery query) {
        return CommonResponse.getSuccessResponse(hospitalAnnualGoalService.searchFindPage(query));
    }

    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {
        return CommonResponse.getSuccessResponse(hospitalAnnualGoalService.get(id));
    }

    @GetMapping("/add")
    public String addInput() {
        return "/qos/goal/annual_goal_add";
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return "/qos/goal/annual_goal_detail";
    }

    @PostMapping("/save")
    @ResponseBody
    public Object save(@Valid HospitalAnnualGoalDTO hospitalAnnualGoalDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        String id = UUIDUtil.createUUID();
        hospitalAnnualGoalDTO.setId(id);
        try {
            if (hospitalAnnualGoalService.saveGoal(beanCopy(hospitalAnnualGoalDTO,new HospitalAnnualGoal())) > 0) {
                return CommonResponse.getSuccessResponse(beanCopy( hospitalAnnualGoalService.get(id), new HospitalAnnualGoalVO()));
            }
        } catch (Exception e) {
            return CommonResponse.getFailResponse(e.getMessage());
        }
        return CommonResponse.getFailResponse();
    }

    @PostMapping("/update")
    @ResponseBody
    public Object update(@Valid HospitalAnnualGoalDTO hospitalAnnualGoalDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        if(StringUtil.isEmpty(hospitalAnnualGoalDTO.getId())){
            return CommonResponse.getFailResponse("数据标识为空");
        }
        HospitalAnnualGoal oldRecord = hospitalAnnualGoalService.get(hospitalAnnualGoalDTO.getId());//历史记录
        if (oldRecord == null) {
            return CommonResponse.getFailResponse("数据库中已不存在该记录");
        }
        try {
            if (hospitalAnnualGoalService.updateGoal(hospitalAnnualGoalDTO, oldRecord) > 0) {
                return CommonResponse.getSuccessResponse(beanCopy(hospitalAnnualGoalService.get(hospitalAnnualGoalDTO.getId()), new HospitalAnnualGoalVO()));
            }
        } catch (Exception e) {
            return CommonResponse.getFailResponse(e.getMessage());
        }
        return CommonResponse.getFailResponse();
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(hospitalAnnualGoalService.deleteById(id));
    }
}