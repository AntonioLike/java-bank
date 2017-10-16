package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.persistence.H2WebServer;
import org.academiadecodigo.javabank.services.jpa.JpaAccountService;
import org.academiadecodigo.javabank.services.jpa.JpaCustomerService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        try {


            H2WebServer h2WebServer = new H2WebServer();
            h2WebServer.start();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT);

            App app = new App();
            app.bootStrap(emf);

            emf.close();
            h2WebServer.stop();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void bootStrap(EntityManagerFactory emf) {

        Bootstrap bootstrap = new Bootstrap();

        JpaSessionManager sessionManager = new JpaSessionManager(emf);

        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new JpaAccountService(emf));
        bootstrap.setCustomerService(new JpaCustomerService(emf));
        bootstrap.setSessionManager(sessionManager);
        bootstrap.setTransactionManager(new JpaTransactionManager(sessionManager));

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();

    }
}
