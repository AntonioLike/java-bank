package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.services.AuthenticationService;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.view.Messages;

public class LoginController extends AbstractController {

    private Controller nextController;
    private AuthenticationService authenticationService;

    public void onLogin(int inputID) {
        if(!authenticateLogin(inputID)){
            System.out.println("\n " + Messages.VIEW_LOGIN_ERROR);
            init();
        }
        nextController.init();
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean authenticateLogin(int id){
        return authenticationService.authenticateLogin(id);
    }

    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }
}
