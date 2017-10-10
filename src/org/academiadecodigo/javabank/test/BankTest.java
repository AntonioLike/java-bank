package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.managers.AccountService;
import org.academiadecodigo.javabank.model.CustomerService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

public class BankTest {

    public boolean test() {

        AccountService accountManager = new AccountService();
        CustomerService bank = new CustomerService();
        bank.setAccountManager(accountManager);

        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        bank.addCustomer(c1);
        bank.addCustomer(c2);

        Account a1 = accountManager.openAccount(AccountType.CHECKING);
        Account a2 = accountManager.openAccount(AccountType.CHECKING);

        bank.addAccount(a1,c1.getId());
        bank.addAccount(a2,c2.getId());

        accountManager.deposit(a1.getId(), 100);
        accountManager.deposit(a2.getId(), 100);

        // bank balance should equal sum of all customers balance
        if (bank.getBalance() != 200) {
            return false;
        }

        return true;
    }
}
