package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.view.View;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.never;

public class LoginControllerTest {

    private LoginController loginController;
    private View view;

    @Before
    public void setupUp(){
        loginController = new LoginController();
        view = Mockito.mock(View.class);
        loginController.setView(view);
    }

    @Test
    public void initTest(){

        loginController.init();

        Mockito.verify(view).show();

    }

    //@Test
    public void successLogin(){

        MainController nextController = Mockito.mock(MainController.class);
        AuthService authService = Mockito.mock(AuthService.class);
        loginController.setNextController(nextController);
        loginController.setAuthService(authService);
        Mockito.when(authService.authenticate(1)).thenReturn(true);


        loginController.onLogin(1);

        Mockito.verify(authService).authenticate(1);
        Mockito.verify(nextController).init();
        Mockito.verify(view).show();

    }

    @Test
    public void failedLogin(){

        MainController nextController = Mockito.mock(MainController.class);
        AuthService authService = Mockito.mock(AuthService.class);
        loginController.setNextController(nextController);
        loginController.setAuthService(authService);
        Mockito.when(authService.authenticate(1)).thenReturn(true);

        loginController.onLogin(2);

        Mockito.verify(nextController, never()).init();



    }


}

