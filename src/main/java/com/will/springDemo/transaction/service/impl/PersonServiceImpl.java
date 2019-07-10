package com.will.springDemo.transaction.service.impl;

import com.will.springDemo.transaction.dao.UserDao;
import com.will.springDemo.transaction.entity.User;
import com.will.springDemo.transaction.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @Author chenwei
 * @Date 2019-07-10
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private UserDao userDao;

    @Override
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    //@Transactional(propagation = Propagation.SUPPORTS)
    //@Transactional(propagation = Propagation.NESTED)
    @Transactional
    public void insert() {
        System.out.println("方法PersonServiceImpl.insert存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("方法PersonServiceImpl.insert事务名称：" + TransactionSynchronizationManager.getCurrentTransactionName());
        User user = new User();
        user.setUser_name("person_insert");
        user.setUser_pwd("123456");
        userDao.insert(user);
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
    public void findByName(String name) {
        System.out.println("方法findByName存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("方法findByName事务名称：" + TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("方法findByName事务隔离级别：" + TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());
        User user = userDao.findByName(name);
        System.out.println(user);
    }
}
