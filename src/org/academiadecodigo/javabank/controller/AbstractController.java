package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.view.View;

public abstract class AbstractController implements Controller {

    protected View view;
    protected Controller nextController;

    @Override
    public void init() {
        view.show();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }
}
