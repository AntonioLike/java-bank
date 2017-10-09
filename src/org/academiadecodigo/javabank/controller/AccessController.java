package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.view.AccessView;
import org.academiadecodigo.javabank.view.BankApplication;
import org.academiadecodigo.javabank.view.View;

public class AccessController extends AbstractController implements Controller {

    private Bank bank;

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setAccessingCustomerID(int customerID) {
        bank.setAccessingCustomerId(customerID);
        nextController.init();
    }

}
