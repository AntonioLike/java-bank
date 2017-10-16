package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

public interface AccountService {

    Account add(Account account);

    void setCustomer(Customer customer, Account account);

    void deposit(int id, double amount);

    void withdraw(int id, double amount);

    void transfer(int srcId, int dstId, double amount);

    void setAuthService(AuthService authService);

}
