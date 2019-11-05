package com.paladin.qos.controller.improvement;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.improvement.ImprovementProblemService;
import com.paladin.qos.service.improvement.dto.ImprovementProblemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/qos/improvement/problem")
public class ImprovementProblemController extends ControllerSupport {

    @Autowired
    private ImprovementProblemService improvementProblemService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = ImprovementProblemQuery.class)
    public String index() {
        return "/qos/improvement/improvement_problem_index";
    }

    @RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = ImprovementProblemQuery.class, paramIndex = 0)
    public Object findPage(ImprovementProblemQuery query) {
        return CommonResponse.getSuccessResponse(improvementProblemService.searchPage(query));
    }
    
/*    @GetMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(improvementProblemService.get(id), new ImprovementProblemVO()));
    }
    
    @GetMapping("/add")
    public String addInput() {
        return "/qos/improvement/improvement_problem_add";
    }

    @GetMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/qos/improvement/improvement_problem_detail";
    }
    
    @PostMapping("/save")
	@ResponseBody
    public Object save(@Valid ImprovementProblemDTO improvementProblemDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        ImprovementProblem model = beanCopy(improvementProblemDTO, new ImprovementProblem());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (improvementProblemService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(improvementProblemService.get(id), new ImprovementProblemVO()));
		}
		return CommonResponse.getFailResponse();
	}

    @PostMapping("/update")
	@ResponseBody
    public Object update(@Valid ImprovementProblemDTO improvementProblemDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = improvementProblemDTO.getId();
		ImprovementProblem model = beanCopy(improvementProblemDTO, improvementProblemService.get(id));
		if (improvementProblemService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(improvementProblemService.get(id), new ImprovementProblemVO()));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(improvementProblemService.removeByPrimaryKey(id));
    }
    
    @PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody ImprovementProblemExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		ImprovementProblemQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success", ExportUtil.export(condition, improvementProblemService.searchAll(query), ImprovementProblem.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, improvementProblemService.searchPage(query).getData(), ImprovementProblem.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}*/
}