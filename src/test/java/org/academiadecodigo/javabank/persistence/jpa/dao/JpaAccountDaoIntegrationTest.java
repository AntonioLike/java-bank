package org.academiadecodigo.javabank.persistence.jpa.dao;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.model.account.CheckingAccount;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaAccountDao;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class JpaAccountDaoIntegrationTest {

    private final static Integer INVALID_ID = 9999;
    private final static double DOUBLE_DELTA = 0.1;

    private EntityManager em = mock(EntityManager.class);
    private JpaAccountDao accountDao;

    @Before
    public void setup() {

        accountDao = new JpaAccountDao();

    }

    @Test
    public void testFindById() {

        // setup
        int id = 1;

        // exercise
        Account account = accountDao.findById(id);

        // verify
        assertNotNull("Account is null", account);
        assertEquals("Account id is wrong", id, account.getId().intValue());
        assertEquals("Account type is wrong", AccountType.CHECKING.toString(), account.getAccountType().toString());
        assertEquals("Account balance is wrong", 100, account.getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testFindByIdInvalid() {

        // exercise
        Account account = accountDao.findById(INVALID_ID);

        // verify
        assertNull("Account should be null", account);
    }

    @Test
    public void testFindAll() {

        // exercise
        List<Account> accounts = accountDao.findAll();

        // verify
        assertNotNull("Accounts are null", accounts);
        assertEquals("Number of accounts is wrong", 7, accounts.size());

    }

    @Test
    public void testFindAllFail() {

        // setup
        Query query = em.createQuery("delete from Account ");
        query.executeUpdate();
        query = em.createQuery("delete from Customer");
        query.executeUpdate();

        // exercise
        List<Account> accounts = accountDao.findAll();

        // verify
        assertNotNull("Accounts are null", accounts);
        assertEquals("Number of accounts is wrong", 0, accounts.size());

    }

    @Test
    public void testAddAccount() {

        // setup
        Account newAccount = new CheckingAccount();

        // exercise
        Account addedAccount = accountDao.saveOrUpdate(newAccount);

        // verify
        assertNotNull("Account not added", addedAccount);
        Account account = em.find(Account.class, addedAccount.getId());
        assertNotNull("Account not found", account);

    }

    @Test
    public void testUpdateAccount() {

        // setup
        int id = 1;
        Account account = em.find(Account.class, id);
        account.credit(100);

        // exercise
        accountDao.saveOrUpdate(account);

        // verify
        assertEquals("Account balance is wrong", 200, account.getBalance(), DOUBLE_DELTA);

    }


    @Test
    public void testDeleteAccountOwned() {

        // setup
        int id = 1;

        // exercise
        accountDao.delete(id);

        // verify
        Account account = em.find(Account.class, id);
        assertNotNull("Account owned by customer should not be deleted", account);
    }

    @Test
    public void testDeleteOrphanAccount() {

        // setup
        int id = 7;

        // exercise
        accountDao.delete(id);

        // verify
        Account account = em.find(Account.class, id);
        assertNull("Account is not null", account);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteInvalid() {

        // exercise
        accountDao.delete(INVALID_ID);
    }

}
