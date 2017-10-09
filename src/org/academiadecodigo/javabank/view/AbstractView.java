package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.Controller;

public abstract class AbstractView implements View{

    protected Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
