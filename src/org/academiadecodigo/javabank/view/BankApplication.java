package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.controller.*;
import org.academiadecodigo.javabank.controller.transaction.DepositOperation;
import org.academiadecodigo.javabank.controller.transaction.WithdrawOperation;
import org.academiadecodigo.javabank.model.Bank;

import java.util.HashMap;
import java.util.Map;

public class BankApplication {

    private Prompt prompt;
    private MenuInputScanner mainMenu;
    private Map<Integer, Operation> operationsMap;

    private Bank bank;
    private int accessingCustomerId;

    public BankApplication(Bank bank) {
        this.bank = bank;
        this.prompt = new Prompt(System.in, System.out);
    }

    public void start() {

        mainMenu = buildMainMenu();

        accessingCustomerId = scanCustomerId();
        operationsMap = buildOperationsMap();
        menuLoop();

    }

    private void menuLoop() {

        int userChoice = prompt.getUserInput(mainMenu);

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        operationsMap.get(userChoice).execute();
        menuLoop();

    }

    private int scanCustomerId() {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);
        return prompt.getUserInput(scanner);

    }

    private MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;

    }

    private Map<Integer, Operation> buildOperationsMap() {

        Map<Integer, Operation> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceOperation(this));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositOperation(this));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawOperation(this));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperation(this));

        return map;

    }

    public int getAccessingCustomerId() {
        return accessingCustomerId;
    }

    public Bank getBank() {
        return bank;
    }

    public Prompt getPrompt() {
        return prompt;
    }
}