package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.services.AuthenticationService;

public interface AccountTransactionController extends Controller {

    void submitTransaction(int accountId, double amount);

    AuthenticationService getAuthenticationService();

    void setAuthenticationService(AuthenticationService authenticationService);
}
