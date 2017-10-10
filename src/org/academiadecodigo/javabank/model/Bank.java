package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashMap;
import java.util.Set;

public class Bank {

    private AccountManager accountManager;
    private HashMap<Integer, Customer> customers;

    private int loginCustomer;

    public Bank() {
        this.customers = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Set<Integer> getCustomerIds() {
        return customers.keySet();
    }

    public double getBalance() {

        double balance = 0;

        for (Customer customer : customers.values()) {
            balance += customer.getBalance();
        }

        return balance;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public Customer getLoginCustomer() {
        return customers.get(loginCustomer);
    }

    public void setLoginCustomer(int id) {
        this.loginCustomer = id;
    }
}
