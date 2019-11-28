package com.paladin.qos.controller.org;

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
@RequestMapping("/home/page/qos/org/person")
public class OrgPersonHomePageController extends ControllerSupport {

    @Autowired
    private OrgPersonService orgPersonService;

    @RequestMapping(value = "/select/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object selectData() {
        return  CommonResponse.getSuccessResponse(orgPersonService.getTotalNumber());
    }
}