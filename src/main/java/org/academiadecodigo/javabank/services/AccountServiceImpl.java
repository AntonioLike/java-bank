package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.swing.text.html.parser.Entity;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {

    private EntityManagerFactory emf;

    private Map<Integer, Account> accountMap = new HashMap<>();

    public AccountServiceImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void add(Account account) {

      /*  if (account.getId() == null) {
            account.setId(getNextId());
        }

        accountMap.put(account.getId(), account);*/

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();
        }catch (RollbackException ex){
            em.getTransaction().rollback();
        }finally {
            if (em != null)
                em.close();
        }

    }

    public void deposit(int id, double amount) {
        accountMap.get(id).credit(amount);
    }

    public void withdraw(int id, double amount) {

        Account account = accountMap.get(id);
        if (account.getAccountType() == AccountType.SAVINGS) {
            return;
        }

        accountMap.get(id).debit(amount);
    }

    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = accountMap.get(srcId);
        Account dstAccount = accountMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }

    private Integer getNextId() {
        return accountMap.isEmpty() ? 1 : Collections.max(accountMap.keySet()) + 1;
    }
}
