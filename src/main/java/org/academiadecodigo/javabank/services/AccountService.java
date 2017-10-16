package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.DAO;

public interface AccountService extends DAO<Account> {

    void deposit(Integer id, double amount);

    void withdraw(Integer id, double amount);

    void transfer(Integer srcId, Integer dstId, double amount);

}