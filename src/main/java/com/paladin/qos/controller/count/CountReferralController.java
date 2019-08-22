package com.paladin.qos.controller.count;

import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.count.dto.CountReferralExportCondition;
import com.paladin.qos.controller.school.dto.OrgSchoolNameExportCondition;
import com.paladin.qos.model.count.CountReferral;
import com.paladin.qos.model.school.OrgSchoolName;
import com.paladin.qos.service.count.CountReferralService;
import com.paladin.qos.service.count.dto.CountReferralDTO;
import com.paladin.qos.service.count.dto.CountReferralQuery;
import com.paladin.qos.service.count.vo.CountReferralVO;
import com.paladin.qos.service.school.dto.OrgSchoolNameQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/qos/count/referral")
public class CountReferralController extends ControllerSupport {

    @Autowired
    private CountReferralService countReferralService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = CountReferral.class)
    public String index(){
        return "/qos/count/count_referral_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = CountReferralQuery.class, paramIndex = 0)
    public Object findPage(CountReferralQuery query) {
        return CommonResponse.getSuccessResponse(countReferralService.searchFindPage(query));
    }

    @PostMapping("/save")
    @ResponseBody
    public Object save(@Valid CountReferralDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
//        if(orgSchoolNameService.judge(dto.getSchoolFullName()) !=null){
//            return CommonResponse.getErrorResponse("学校名称已存在");
//        }
        CountReferral model = beanCopy(dto, new CountReferral());
        String id = UUIDUtil.createUUID();

        model.setId(id);
        if (countReferralService.save(model) > 0) {
            return CommonResponse.getSuccessResponse(beanCopy(countReferralService.get(id), new CountReferralVO()));
        }
        return CommonResponse.getFailResponse();
    }

    @PostMapping("/update")
    @ResponseBody
    public Object update(@Valid @RequestBody CountReferralDTO countReferralDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        String id = countReferralDTO.getId();
        CountReferral model = beanCopy(countReferralDTO, countReferralService.get(id));
        if (countReferralService.update(model) > 0) {
            return CommonResponse.getSuccessResponse(beanCopy(countReferralService.get(id), new CountReferralVO()));
        }
        return CommonResponse.getFailResponse();
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(countReferralService.removeByPrimaryKey(id));
    }


    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return "/qos/count/count_referral_detail";
    }

    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id) {
        return CommonResponse.getSuccessResponse(beanCopy(countReferralService.get(id), new CountReferralVO()));
    }

    @PostMapping(value = "/export")
    @ResponseBody
    public Object export(@RequestBody CountReferralExportCondition condition) {
        if (condition == null) {
            return CommonResponse.getFailResponse("导出失败：请求参数异常");
        }
        condition.sortCellIndex();
        CountReferralQuery query = condition.getCountReferralQuery();
        try {
            if (query != null) {
                if (condition.isExportAll()) {
                    return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, countReferralService.searchAll(query), CountReferral.class));
                } else if (condition.isExportPage()) {
                    return CommonResponse.getSuccessResponse("success",
                            ExportUtil.export(condition, countReferralService.searchPage(query).getData(), CountReferral.class));
                }
            }
            return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
        } catch (IOException | ExcelWriteException e) {
            return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
        }
    }

}