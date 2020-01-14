package com.paladin.qos.controller.school;

import com.paladin.common.controller.syst.SysControllerLog;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.model.school.DiseaseManage;
import com.paladin.qos.service.school.DiseaseManageService;
import com.paladin.qos.service.school.dto.DiseaseManageDTO;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

/**
 * < 病种管理>
 * @author  MyKite
 * @version  [版本号, 2019年7月23日]
 */
@Controller
@RequestMapping("/qos/disease/manage")
public class DiseaseManageController extends ControllerSupport {

    @Autowired
    DiseaseManageService diseaseManageService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = OrgSchoolQuery.class)
    public String index() {
        return "/qos/school/disease_manage_index";
    }

    @RequestMapping(value = "/find", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object find() {
        return CommonResponse.getSuccessResponse(diseaseManageService.findAll());
    }

    @GetMapping("/add")
    @QueryInputMethod(queryClass = OrgSchoolQuery.class)
    public String add() {
        return "/qos/school/disease_manage_add";
    }

    @PostMapping("/save")
    @SysControllerLog(action = "新增病种")
    @ResponseBody
    public Object save(DiseaseManageDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        DiseaseManage data = beanCopy(dto,new DiseaseManage());
        String id = UUIDUtil.createUUID();
        data.setId(id);
        Example example = new Example(DiseaseManage.class);
        example.setOrderByClause("code DESC");
        List<DiseaseManage> d =diseaseManageService.searchAll(example);
        if(d.size()== 0){
            data.setCode(1);
        }else {
            DiseaseManage maxCode = d.stream().max(Comparator.comparing(DiseaseManage::getCode)).get();
            data.setCode(maxCode.getCode()+1);
        }
        if (diseaseManageService.save(data) > 0) {
            return CommonResponse.getSuccessResponse(beanCopy( diseaseManageService.get(id),new DiseaseManageDTO()));
        }
        return CommonResponse.getFailResponse();
    }

    @PostMapping("/update")
    @SysControllerLog(action = "修改病种信息")
    @ResponseBody
    public Object update(@Valid DiseaseManageDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        String id = dto.getId();
        DiseaseManage model = diseaseManageService.get(id);
        model.setName(dto.getName());
        model.setWarningValue(dto.getWarningValue());
        if (diseaseManageService.update(model) > 0) {
            return CommonResponse.getSuccessResponse(beanCopy(diseaseManageService.get(id), new DiseaseManageDTO()));
        }
        return CommonResponse.getFailResponse();
    }

    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id) {
        return CommonResponse.getSuccessResponse(diseaseManageService.get(id));
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return "/qos/school/disease_manage_detail";
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @SysControllerLog(action = "删除病种信息")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(diseaseManageService.removeByPrimaryKey(id));
    }
}
