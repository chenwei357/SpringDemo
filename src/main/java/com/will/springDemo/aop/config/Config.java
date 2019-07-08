package com.will.springDemo.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author chenwei
 * @Date 2019-07-01
 */
@Configuration
@ComponentScan("com.will.springDemo")
@EnableAspectJAutoProxy         //开启aop支持
public class Config {


}
