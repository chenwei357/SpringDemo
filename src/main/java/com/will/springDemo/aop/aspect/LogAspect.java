package com.will.springDemo.aop.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author chenwei
 * @Date 2019-07-01
 */
@Component
@Aspect
public class LogAspect {

    @Before("execution(* com.will.springDemo.aop.service.impl.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("=============before==============");
    }

    @After("execution(* com.will.springDemo.aop.service.impl.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("=============after==============");
    }

    @AfterThrowing("execution(* com.will.springDemo.aop.service.impl.UserServiceImpl.*(..))")
    public void afterThrowing() {
        System.out.println("=============afterThrowing==============");
    }

    @AfterReturning("execution(* com.will.springDemo.aop.service.impl.UserServiceImpl.*(..))")
    public void afterReturning() {
        System.out.println("=============afterReturning==============");
    }

}
