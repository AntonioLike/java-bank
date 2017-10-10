package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.model.CustomerService;

public abstract class AbstractView implements View {

    protected Prompt prompt;
    protected CustomerService bank;

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public void setBank(CustomerService bank) {
        this.bank = bank;
    }
}
