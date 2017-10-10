package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.managers.AccountService;
import org.academiadecodigo.javabank.model.account.Account;

import java.util.HashMap;
import java.util.Set;

public class CustomerService {

    private AccountService accountManager;
    private HashMap<Integer, Customer> customers;

    private int loginCustomer;

    public CustomerService() {
        this.customers = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Set<Integer> getCustomerIds() {
        return customers.keySet();
    }

    public void addAccount(Account account, int customerID) {
        customers.get(customerID).getAccountsMap().put(account.getId(), account);
    }

    public double getBalance() {

        double balance = 0;

        for (Customer customer : customers.values()) {
            balance += customer.getBalance();
        }

        return balance;
    }

    public void setAccountManager(AccountService accountManager) {
        this.accountManager = accountManager;
    }

    public AccountService getAccountManager() {
        return accountManager;
    }

    public Customer getLoginCustomer() {
        return customers.get(loginCustomer);
    }

    public void setLoginCustomer(int id) {
        this.loginCustomer = id;
    }
}
