package com.will.springDemo.transaction.service.impl;

import com.will.springDemo.transaction.dao.UserDao;
import com.will.springDemo.transaction.entity.User;
import com.will.springDemo.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author chenwei
 * @Date 2019-07-01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 声明式事务
     */
    @Transactional
    public void insert(){
        User user = new User();
        user.setUser_name("will.chen");
        user.setUser_pwd("123456");
        userDao.insert(user);
        System.out.println("==========保存用户==========");
    }

    public void delete() {
        System.out.println("==========删除用户==========");
    }

    /**
     * 编程式事务
     */
    public void update() {
        User user = new User();
        user.setId(1);
        user.setUser_name("will.chen");
        user.setUser_pwd("123456");
        int affect = transactionTemplate.execute((status) -> {
            int count = userDao.update(user);
            //System.out.println(1/0);
            return count;
        });
        System.out.println("==========更新用户,影响行数=" + affect + "==========");
    }

    public void find(int id) {
        System.out.println("==========查找用户==========");
    }

}
