package com.paladin.common.controller.syst;

import com.paladin.common.model.syst.SysLoggerLogin;
import com.paladin.common.service.syst.SysLoggerLoginService;
import com.paladin.common.util.IPUtil;
import com.paladin.framework.core.copy.SimpleBeanCopier;
import com.paladin.framework.core.session.UserSession;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.core.QosUserSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <日志切面>
 *
 * @author Huangguochen
 * @create 2019/10/28 9:05
 */
@Aspect
@Component
public class SysLogAspect {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(SysLogAspect.class);

    @Autowired
    private SysLoggerLoginService sysLoggerLoginService;


    /**Controller层切点*/
    @Pointcut("@annotation(com.paladin.common.controller.syst.SysControllerLog)")
    public void controllerAspect(){

    }


    @AfterReturning(value = "controllerAspect()",returning = "result")
    public void  doAfterReturning(JoinPoint joinPoint, Object result){
        LOGGER.info("==============日志后置返回通知开始==============");
        CommonResponse response = new CommonResponse();

        SimpleBeanCopier.SimpleBeanCopyUtil.simpleCopy(result,response);

        if ( response.getStatus() == 1) {
            // 获取request ip
            WebSubject webSubject = (WebSubject) SecurityUtils.getSubject();
            HttpServletRequest request = (HttpServletRequest) webSubject.getServletRequest();
            String  ip = IPUtil.getIpAddress(request);

            UserSession userSession = QosUserSession.getCurrentUserSession();

            String account = userSession.getAccount();

            SysLoggerLogin model = new SysLoggerLogin();
            model.setIp(ip);
            model.setAccount(account);
            model.setUserType(1);
            model.setLoginType(1);
            model.setUserId(userSession.getUserId());
            LocalDateTime nowTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm a");
            String loginTime = nowTime.format(formatter);
            String action;
            try {
                action = getControllerMethodDescription(joinPoint);
                model.setLoginAction(userSession.getUserName()+"："+action+"（"+loginTime+"）");
                model.setCreateTime(new Date());
                sysLoggerLoginService.save(model);
            } catch (Exception e) {
                LOGGER.error("===========后置返回通知异常==============>>"+e.getMessage());
            }
        }
        LOGGER.info("==============日志后置返回通知结束==============");
    }


    private String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    SysControllerLog annotation = method.getAnnotation(SysControllerLog.class);
                    description = annotation.action();
                    int index = annotation.dtoIndex();
                    description = description + "==>" +  arguments[index].toString();
                    break;
                }
            }
        }
        return description;
    }






}
