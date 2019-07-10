package com.will.springDemo.transaction.service;

public interface UserService {
    void insert();

    void delete();

    void update();

    void find(int id);

    void doWithTXA();

    void doWithTXB();

    void doWithTXC();

    void doWithTXD();

    void doWithTXE();

    void doWithTXF();

    void doWithPropagation();

    void doWithIsolation();
}
