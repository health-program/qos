package com.paladin.qos.controller.familydoctor;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paladin.framework.core.ControllerSupport;

/**   
 * @author MyKite
 * @version 2019年9月9日 下午1:33:49 
 */
@Controller
@RequestMapping("/qos/data/familydoctor")
public class DataFamilyDoctorController extends ControllerSupport{

    @GetMapping("/service/index")
    public String serviceIndex(){
	return "/qos/familydoctor/data_family_doctor_service";
    }
    
    @GetMapping("/patient/index")
    public String patientIndex(){
	return "/qos/familydoctor/data_family_doctor_outpatient";
    }
    
    @GetMapping("/signing/index")
    public String signingIndex(){
	return "/qos/familydoctor/data_family_doctor_signingrate";
    }
}