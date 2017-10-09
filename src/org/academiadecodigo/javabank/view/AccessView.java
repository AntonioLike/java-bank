package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.controller.AbstractController;
import org.academiadecodigo.javabank.controller.AccessController;
import org.academiadecodigo.javabank.model.Bank;

public class AccessView extends AbstractView implements View {

    Bank bank;
    AccessController accessController;
    Prompt prompt;

    public AccessView(Bank bank, Prompt prompt) {
        this.bank = bank;
        this.prompt = prompt;
    }

    public void setAccessController(AccessController accessController) {
        this.accessController = accessController;
    }

    @Override
    public void show() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);
        accessController.setAccessingCustomerID(prompt.getUserInput(scanner));
    }
}

