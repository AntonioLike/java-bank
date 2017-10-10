package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Bank;

public class LoginController extends AbstractController {

    private Controller nextController;

    private Bank bank;

    public void onLogin(int id) {
        bank.setLoginCustomer(id);
        nextController.init();
    }

    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
