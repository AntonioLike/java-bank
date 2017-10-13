package org.academiadecodigo.javabank.models;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class CustomerTest {
    private Customer customer;
    private Account account;

    @Before
    public void setupUP(){
        customer = new Customer();
        account = Mockito.mock(Account.class);
    }

    @Test
    public void addAccountTest(){
        customer.addAccount(account);

        Mockito.verify(account);
    }
}
