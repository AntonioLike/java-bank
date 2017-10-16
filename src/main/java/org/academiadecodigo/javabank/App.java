package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.persistence.H2WebServer;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.services.jpa.AccountServiceJPA;
import org.academiadecodigo.javabank.services.jpa.AuthServiceJPA;
import org.academiadecodigo.javabank.services.jpa.CustomerServiceJPA;
import org.academiadecodigo.javabank.services.mock.AccountServiceImpl;
import org.academiadecodigo.javabank.services.mock.AuthServiceImpl;
import org.academiadecodigo.javabank.services.mock.CustomerServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        try{
            H2WebServer h2WebServer = new H2WebServer();
            h2WebServer.start();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

            App app = new App();
            app.bootStrap(emf);

            emf.close();
            h2WebServer.stop();

        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    private void bootStrap(EntityManagerFactory emf) {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceJPA(emf));
        bootstrap.setAccountService(new AccountServiceJPA(emf));
        bootstrap.setCustomerService(new CustomerServiceJPA(emf));
        bootstrap.loadCustomers();

        LoginController loginController = bootstrap.wireObjects();

        // start application
        loginController.init();

    }
}
