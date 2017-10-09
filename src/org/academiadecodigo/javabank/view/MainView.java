package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.controller.BalanceOperation;
import org.academiadecodigo.javabank.controller.MainController;
import org.academiadecodigo.javabank.controller.NewAccountOperation;
import org.academiadecodigo.javabank.controller.Operation;
import org.academiadecodigo.javabank.controller.transaction.DepositOperation;
import org.academiadecodigo.javabank.controller.transaction.WithdrawOperation;

import java.util.HashMap;
import java.util.Map;

public class MainView extends AbstractView implements View{
    private MainController mainController;
    private MenuInputScanner mainMenu;
    private Prompt prompt;

    public MainView(Prompt prompt) {
        this.prompt = prompt;
    }

    private MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;

    }

    @Override
    public void show() {
        mainMenu = buildMainMenu();
        int userChoice = prompt.getUserInput(mainMenu);
        mainController.setUserChoice(userChoice);
        controller.init();
    }
}
