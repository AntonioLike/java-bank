package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.AccessController;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.MainController;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.managers.AccountManager;
import org.academiadecodigo.javabank.view.AccessView;
import org.academiadecodigo.javabank.view.BankApplication;
import org.academiadecodigo.javabank.view.MainView;
import org.academiadecodigo.javabank.view.View;
import sun.applet.Main;

public class BootStrap {

    public BootStrap() {
        Bank bank = new Bank();
        AccountManager accountManager = new AccountManager();
        bank.setAccountManager(accountManager);

        Customer c1 = new Customer(1,"Rui");
        Customer c2 = new Customer(2,"Sergio");
        Customer c3 = new Customer(3,"Bruno");
        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);

        Prompt prompt = new Prompt(System.in, System.out);
        AccessView accessView = new AccessView(bank,prompt);
        MainController mainController = new MainController();
        AccessController accessController = new AccessController();
        accessController.setView(accessView);
        accessView.setController(accessController);
        accessController.setBank(bank);
        accessController.setNextController(mainController);
        accessView.setAccessController(accessController);

        MainView mainView = new MainView(prompt);

        mainController.setView(mainView);
        mainView.setController(mainController);



        accessController.init();



    }
}
