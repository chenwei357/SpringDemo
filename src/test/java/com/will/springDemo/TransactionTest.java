package com.will.springDemo;

import com.will.springDemo.transaction.config.Config;
import com.will.springDemo.transaction.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

    /**
     * 业务方法A和B, A方法正常执行,B方法会抛异常
     * 情景1： A没有事务注解,B有。A调用B ->  相当于方法正常的调用,两个方法都不会有事务
     * 情景2： A有事务注解,B没有。A调用B ->  两个方法都有事务,且事务名相同,采用A的事务名
     * 情景3： A有事务注解,B也有。A调用B ->  同一个service中的方法中A调用B,相当于正常的方法调用.如果A有事务,则B中也会有事务,且与A同在一个事务中.
     * 业务方法A和B不在同一个service中
     * 情景1： A有事务注解,B没有。A调用B ->  Propagation级别都为默认,A、B在同一个事务中
     *        A有事务注解,B也有。A调用B ->  Propagation级别A默认,B(Propagation.REQUIRES_NEW),B会新起一个事务,A挂起,两个事务之间互相独立.此时若B抛异常,A会捕获此异常.A是否回滚取决于此异常类型;若A抛异常,B不回滚
     *        A有事务注解,B也有。A调用B ->  Propagation级别A默认,B(Propagation.SUPPORTS),B会加入A事务.
     *        A有事务注解,B也有。A调用B ->  Propagation级别A默认,B(Propagation.NESTED),B会加入A事务.如果B抛异常回滚,则只会回滚到它执行前的savepoint;如果A抛异常回滚,B也会跟着回滚.这种情况与B(Propagation.REQUIRES_NEW)有所不同
     */
    @Test
    public void testTX() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = context.getBean(UserService.class);
        //userService.doWithPropagation();
        userService.doWithIsolation();
        //userService.doWithTXE();
        context.close();
    }

}
