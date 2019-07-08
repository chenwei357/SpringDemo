package com.will.springDemo;

import com.will.springDemo.transaction.config.Config;
import com.will.springDemo.transaction.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chenwei
 * @Date 2019-07-04
 */
public class TransactionTest {

    @Test
    public void teset() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = context.getBean(UserService.class);
        userService.insert();
        //userService.update();
        context.close();
    }

    @Test
    public void testFinally() {
        try {
            System.out.println("==========try===========");
            System.out.println(1/0);
            return;
        }catch (Exception e) {
            System.out.println("=======Exception==========");
        }finally {
            System.out.println("============finally=============");
        }
        return;
    }

}
