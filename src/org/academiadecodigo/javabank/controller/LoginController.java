package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.CustomerService;

public class LoginController extends AbstractController {

    private Controller nextController;

    private CustomerService bank;

    public void onLogin(int id) {
        bank.setLoginCustomer(id);
        nextController.init();
    }

    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    public void setBank(CustomerService bank) {
        this.bank = bank;
    }
}
