package com.paladin.qos.controller.org;

import com.paladin.common.controller.syst.SysControllerLog;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.model.org.OrgPerson;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.org.OrgPersonService;
import com.paladin.qos.service.org.dto.OrgPersonDTO;
import com.paladin.qos.service.org.dto.OrgPersonQuery;
import com.paladin.qos.service.org.vo.OrgPersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/qos/org/person")
public class OrgPersonController extends ControllerSupport {

    @Autowired
    private OrgPersonService orgPersonService;

    @Autowired
    private DataUnitService dataUnitService;

    @RequestMapping("/index")
    public String index(Model model) {
        List<Integer> types=new ArrayList<>();
        types.add(DataUnit.TYPE_COMMUNITY);
        model.addAttribute("unit", dataUnitService.selectData(types));
        return "/qos/org/org_person_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = OrgPersonQuery.class, paramIndex = 0)
    public Object findPage(OrgPersonQuery query) {
        return CommonResponse.getSuccessResponse(orgPersonService.searchPage(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {
        return CommonResponse.getSuccessResponse(beanCopy(orgPersonService.get(id), new OrgPersonVO()));
    }

    @RequestMapping("/add")
    public String addInput(Model model) {
        return "/qos/org/org_person_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/qos/org/org_person_detail";
    }



    @RequestMapping("/save")
    @SysControllerLog(action = "新增机构人员")
	@ResponseBody
    public Object save(@Valid OrgPersonDTO orgPersonDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }

        OrgPerson model = beanCopy(orgPersonDTO, new OrgPerson());
        String id = UUIDUtil.createUUID();
        String unitName= DataConstantContainer.getUnitName(orgPersonDTO.getUnitId());
        model.setId(id);
        model.setUnitName(unitName);
        if (orgPersonService.save(model) > 0) {
            return CommonResponse.getSuccessResponse(beanCopy(orgPersonService.get(id), new OrgPersonVO()));
        }
        return CommonResponse.getFailResponse();
	}


        @PostMapping("/update")
        @SysControllerLog(action = "修改机构人员")
        @ResponseBody
        public Object update(@Valid @RequestBody OrgPersonDTO orgPersonDTO, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return validErrorHandler(bindingResult);
            }
            String id = orgPersonDTO.getId();
            String unitName= DataConstantContainer.getUnitName(orgPersonDTO.getUnitId());
            OrgPerson model = beanCopy(orgPersonDTO, orgPersonService.get(id));
            model.setUnitName(unitName);
            if (orgPersonService.update(model) > 0) {
                return CommonResponse.getSuccessResponse(beanCopy(orgPersonService.get(id), new OrgPersonVO()));
            }
            return CommonResponse.getFailResponse();
        }


    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @SysControllerLog(action = "删除机构人员")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(orgPersonService.removeByPrimaryKey(id));
    }

}