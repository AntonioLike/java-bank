package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.AuthService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

public class AccountServiceJPA implements AccountService {

    private EntityManagerFactory emf;
    private AuthService authService;


    public AccountServiceJPA(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void setCustomer(Customer customer, Account account) {

        account.setCustomer(customer);

    }

    @Override
    public void deposit(int id, double amount) {
        
    }

    @Override
    public void withdraw(int id, double amount) {

    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public Account add(Account account) {

        setCustomer(authService.getAccessingCustomer(), account);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Account savedAccount = em.merge(account);
            em.getTransaction().commit();
            return savedAccount;

        }catch (RollbackException ex){
            em.getTransaction().rollback();
            return null;
        }finally {
            if (em != null)
                em.close();
        }


    }

}
