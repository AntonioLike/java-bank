package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.model.Bank;

public abstract class AbstractView implements View {

    protected Prompt prompt;
    protected Bank bank;

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
