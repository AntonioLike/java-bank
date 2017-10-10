package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.javabank.controller.LoginController;

public class LoginView extends AbstractView {

    private LoginController loginController;

    @Override
    public void show() {
        showBankName();
        showLoginPrompt();
    }

    private void showBankName() {
        System.out.println("\n" + Messages.VIEW_LOGIN_WELCOME);
    }

    private void showLoginPrompt() {

        IntegerInputScanner scanner = new IntegerInputScanner();
        //IntegerSetInputScanner scanner = new IntegerSetInputScanner(customerService.getCustomerIds());
        scanner.setMessage("\n" + Messages.VIEW_LOGIN_MESSAGE);
        Integer inputID = prompt.getUserInput(scanner);
        loginController.onLogin(inputID);
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

}
