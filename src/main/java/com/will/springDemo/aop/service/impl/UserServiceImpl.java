package com.will.springDemo.aop.service.impl;

import com.will.springDemo.aop.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author chenwei
 * @Date 2019-07-01
 */
@Service
public class UserServiceImpl implements UserService {

    public void insert(){
        System.out.println("==========保存用户==========");
    }

    public void delete() {
        System.out.println("==========删除用户==========");
    }

    public void update() {
        System.out.println("==========更新用户==========");
    }

    public void find(int id) {
        System.out.println("==========查找用户==========");
    }

}
