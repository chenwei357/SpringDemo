package com.will.springDemo;

import com.will.springDemo.aop.config.Config;
import com.will.springDemo.aop.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author chenwei
 * @Date 2019-07-01
 */
public class AopTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = (UserService) context.getBean("userServiceImpl");
        try {
            userService.insert();
        } catch (Exception e) {
            System.out.println("=========Exception=========");
        }
    }


}
