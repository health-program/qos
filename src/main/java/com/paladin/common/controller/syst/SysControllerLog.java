package com.paladin.common.controller.syst;

import java.lang.annotation.*;

/**
 * <p>功能描述</p>:自定义注解，拦截controller
 *
 * @author Huangguochen
 * @create 2019/10/28 9:01
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented //表明这个注解应该被 javadoc工具记录
public @interface SysControllerLog {
     /** 操作名*/
     String action() default "";
     /** 参数实体类位置*/
     int dtoIndex() default  0;

}
