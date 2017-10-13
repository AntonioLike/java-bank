package org.academiadecodigo.javabank;

import com.mysql.fabric.Server;
import org.academiadecodigo.javabank.persistence.H2WebServer;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.services.AccountServiceImpl;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;
import org.hibernate.dialect.H2Dialect;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        try{
            H2WebServer h2WebServer = new H2WebServer();
            h2WebServer.start();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev ");

            App app = new App();
            app.bootStrap();

            emf.close();
            h2WebServer.stop();

        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new AccountServiceImpl());
        bootstrap.setCustomerService(new CustomerServiceImpl());
        bootstrap.loadCustomers();

        LoginController loginController = bootstrap.wireObjects();

        // start application
        loginController.init();

    }
}
