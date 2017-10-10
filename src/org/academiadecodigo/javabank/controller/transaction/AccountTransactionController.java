package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.Controller;

public interface AccountTransactionController extends Controller {

    void submitTransaction(int accountId, double amount);

}
