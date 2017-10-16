package org.academiadecodigo.javabank.managers.TransactionManagers;

public interface TransactionManager {

    void beginRead();
    void beginWrite();
    void commit();
    void rollback();
}
