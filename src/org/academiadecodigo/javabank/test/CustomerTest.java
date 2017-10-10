package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.managers.AccountService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.CustomerService;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.model.account.SavingsAccount;

public class CustomerTest {

    public boolean test() {

        CustomerService customerService = new CustomerService();
        AccountService accountManager = new AccountService();
        Customer customer = new Customer(1, "Rui");

        // customer should not contain any accounts
        if (customer.getAccountIds().size() != 0) {
            return false;
        }

        // should be able to open accounts
        Account ac = accountManager.openAccount(AccountType.CHECKING);
        Account as = accountManager.openAccount(AccountType.SAVINGS);
        customerService.addAccount(ac,customer.getId());
        customerService.addAccount(as,customer.getId());

        if (customer.getAccountIds().size() != 2) {
            return false;
        }

        // customer should be able to get the balance of each individual account
        accountManager.deposit(ac.getId(), 100);
        accountManager.deposit(as.getId(), SavingsAccount.MIN_BALANCE + 100);
        if (customer.getBalance(ac.getId()) != 100 || customer.getBalance(as.getId()) != SavingsAccount.MIN_BALANCE + 100) {
            return false;
        }

        // customer should be able to get the combined balance of all its accounts
        if (customer.getBalance() != 200 + SavingsAccount.MIN_BALANCE) {
            return false;
        }

        return true;
    }

}
