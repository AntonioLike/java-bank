package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.transaction.DepositOperation;
import org.academiadecodigo.javabank.controller.transaction.WithdrawOperation;
import org.academiadecodigo.javabank.view.UserOptions;
import org.academiadecodigo.javabank.view.View;

import java.util.HashMap;
import java.util.Map;

public class MainController extends AbstractController implements Controller {

    private Map<Integer, Controller> buildControllersMap() {

        Map<Integer, Controller> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceController());
        map.put(UserOptions.DEPOSIT.getOption(), new DepositController());
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawController());
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountController());

        return map;

    }

    public void setUserChoice(int userChoice) {
        if (userChoice == UserOptions.QUIT.getOption())
        {
            return;
        }
        buildControllersMap().get(userChoice).init();
    }

    @Override
    public void init() {
        super.init();
    }
}
