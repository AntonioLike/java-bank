package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.model.CustomerService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceView implements View {

    private CustomerService bank;
    DecimalFormat df = new DecimalFormat("#.##");

    public void setBank(CustomerService bank) {
        this.bank = bank;
    }

    @Override
    public void show() {
        showBalance();
    }

    private void showBalance() {

        Customer customer = bank.getLoginCustomer();
        System.out.println("\n" + customer.getName() + Messages.VIEW_BALANCE_MESSAGE + "\n");

        Set<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.VIEW_BALANCE_TOTAL_MESSAGE + df.format(customer.getBalance()));
    }
}
