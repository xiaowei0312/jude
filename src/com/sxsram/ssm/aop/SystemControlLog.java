package com.sxsram.ssm.aop;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截Controller
 */

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControlLog {

	String description() default "";

}