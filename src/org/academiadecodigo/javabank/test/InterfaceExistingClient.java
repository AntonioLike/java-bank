package org.academiadecodigo.javabank.test;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Customer;

public class InterfaceExistingClient {
    private int userInput;
    public InterfaceExistingClient(Customer customer) {

        String [] options = {"Deposit", "Withdraw", "Transfer", "Create New Account", "Quit"};
        Prompt prompt = new Prompt(System.in, System.out);
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Choose an option: ");

        userInput = prompt.getUserInput(scanner);


        switch (userInput){
            case 1: options = {""}
                customer.getAccountManager().deposit(customer.getAccountManager().getAccountMap().get(customer);
        }
    }

    public int getUserInput() {
        return userInput;
    }
}
