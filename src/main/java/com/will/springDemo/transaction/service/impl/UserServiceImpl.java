package com.will.springDemo.transaction.service.impl;

import com.will.springDemo.transaction.dao.UserDao;
import com.will.springDemo.transaction.entity.User;
import com.will.springDemo.transaction.service.PersonService;
import com.will.springDemo.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
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
    @Autowired
    private PersonService personService;

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

    //====================情景1代码========================
    public void doWithTXA() {
        System.out.println("方法doWithTXA存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        User user = new User();
        user.setUser_name("TxA");
        user.setUser_pwd("123456");
        System.out.println("-------------doWithTXA--------------");
        userDao.insert(user);
        doWithTXB();
    }

    @Transactional
    public void doWithTXB() {
        System.out.println("方法doWithTXB存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("方法doWithTXB事务名称：" + TransactionSynchronizationManager.getCurrentTransactionName());
        User user = new User();
        user.setUser_name("TxB");
        user.setUser_pwd("123456");
        System.out.println("-------------doWithTXB--------------");
        userDao.insert(user);
        System.out.println(1/0);
    }

    //====================情景1代码========================

    //====================情景2代码========================
    @Transactional
    public void doWithTXC() {
        System.out.println("方法doWithTXC存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("方法doWithTXC事务名称：" + TransactionSynchronizationManager.getCurrentTransactionName());
        User user = new User();
        user.setUser_name("TxC");
        user.setUser_pwd("123456");
        System.out.println("-------------doWithTXC--------------");
        userDao.insert(user);
        doWithTXD();
    }

    public void doWithTXD() {
        System.out.println("方法doWithTXD存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("方法doWithTXD事务名称：" + TransactionSynchronizationManager.getCurrentTransactionName());
        User user = new User();
        user.setUser_name("TxD");
        user.setUser_pwd("123456");
        System.out.println("-------------doWithTXD--------------");
        userDao.insert(user);
        System.out.println(1/0);
    }
    //====================情景2代码========================

    //====================情景3代码========================
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doWithTXE() {
        System.out.println("方法doWithTXE存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("方法doWithTXE事务名称：" + TransactionSynchronizationManager.getCurrentTransactionName());
        User user = new User();
        user.setUser_name("TxE");
        user.setUser_pwd("123456");
        System.out.println("-------------doWithTXE--------------");
        userDao.insert(user);
        doWithTXF();
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doWithTXF() {
        System.out.println("方法doWithTXF存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("方法doWithTXF事务名称：" + TransactionSynchronizationManager.getCurrentTransactionName());
        User user = new User();
        user.setUser_name("TxF");
        user.setUser_pwd("123456");
        System.out.println("-------------doWithTXF--------------");
        userDao.insert(user);
        System.out.println(1/0);
    }
    //====================情景3代码========================

    @Transactional
    public void doWithPropagation() {
        System.out.println("方法doWithPropagation存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("方法doWithPropagation事务名称：" + TransactionSynchronizationManager.getCurrentTransactionName());
        User user = new User();
        user.setUser_name("doWithPropagation");
        user.setUser_pwd("123456");
        userDao.insert(user);
        //调用业务方法B
        try {
            personService.insert();
        } catch (Exception e) {
            System.out.println("业务方法B发生异常,此处可以调用C方法");
        }
        System.out.println(1/0);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void doWithIsolation() {
        System.out.println("方法doWithIsolation存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("方法doWithIsolation事务名称：" + TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("方法doWithIsolation事务隔离级别：" + TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());
        User user = new User();
        user.setUser_name("doWithIsolation");
        user.setUser_pwd("123456");
        userDao.insert(user);
        //personService.findByName("doWithIsolation");
        this.findByName("doWithIsolation");
        System.out.println(1/0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void findByName(String name) {
        System.out.println("方法UserServiceImpl#findByName存在事务：" + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("方法UserServiceImpl#findByName事务名称：" + TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("方法UserServiceImpl#findByName事务隔离级别：" + TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());
        User user = userDao.findByName(name);
        System.out.println(user);
    }
}
