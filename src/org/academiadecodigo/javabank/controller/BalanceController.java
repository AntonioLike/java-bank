package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthenticationService;
import org.academiadecodigo.javabank.services.CustomerService;

public class BalanceController extends AbstractController {

    private AuthenticationService authenticationService;

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public String getName(){
        return authenticationService.getLoginCustomerName();
    }

    public Customer getCustomer(){
        return authenticationService.getLoginCustomer();
    }
}
