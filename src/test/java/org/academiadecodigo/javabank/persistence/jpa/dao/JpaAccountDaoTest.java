package org.academiadecodigo.javabank.persistence.jpa.dao;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.CheckingAccount;
import org.academiadecodigo.javabank.model.account.SavingsAccount;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaAccountDao;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class JpaAccountDaoTest {

    private JpaAccountDao customerDao;
    private EntityManager em = mock(EntityManager.class);

    @Before
    public void setup() {

        em = mock(EntityManager.class);
        customerDao = new JpaAccountDao();

    }

    @Test
    public void testFindAll() {

        // setup
        List<Account> mockAccounts = new ArrayList<>();
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        TypedQuery typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Account.class)).thenReturn(criteriaQuery);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(anyString(), any(Class.class))).thenReturn(typedQuery);
        when(em.createQuery(any(CriteriaQuery.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(mockAccounts);

        // exercise
        List<Account> customers = customerDao.findAll();

        // verify
        verify(typedQuery, times(1)).getResultList();
        assertEquals(mockAccounts, customers);
    }

    @Test
    public void testFindAllFail() {


        // setup
        List<Account> mockAccounts = new ArrayList<>();
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        TypedQuery typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Account.class)).thenReturn(criteriaQuery);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(anyString(), any(Class.class))).thenReturn(typedQuery);
        when(em.createQuery(any(CriteriaQuery.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(mockAccounts);
        doThrow(new HibernateException(new RuntimeException())).when(typedQuery).getResultList();

        // exercise
        customerDao.findAll();

        // verify
        verify(typedQuery.getResultList(), times(1));

    }

    @Test
    public void testFindByIdChecking() {

        // setup
        int fakeId = 9999;
        Account fakeAccount = new CheckingAccount();
        fakeAccount.setId(fakeId);
        when(em.find(Account.class, fakeId)).thenReturn(fakeAccount);

        // exercise
        Account customer = customerDao.findById(fakeId);

        // verify
        assertEquals(fakeAccount, customer);

    }

    @Test
    public void testFindByIdSavings() {

        // setup
        int fakeId = 9999;
        Account fakeAccount = new SavingsAccount();
        fakeAccount.setId(fakeId);
        when(em.find(Account.class, fakeId)).thenReturn(fakeAccount);

        // exercise
        Account customer = customerDao.findById(fakeId);

        // verify
        assertEquals(fakeAccount, customer);

    }

    @Test
    public void testFindByIdFails() {

        // setup
        int fakeId = 9999;
        doThrow(new HibernateException(new RuntimeException())).when(em).find(eq(Account.class), anyInt());

        // exercise
        customerDao.findById(fakeId);

        // verify
        verify(em, times(1)).find(Account.class, fakeId);

    }

    @Test
    public void testSaveOrUpdateChecking() {

        // setup
        Account fakeAccount = new CheckingAccount();
        when(em.merge(any(Account.class))).thenReturn(fakeAccount);

        // exercise
        Account customer = customerDao.saveOrUpdate(fakeAccount);

        // verify
        verify(em, times(1)).merge(any(Account.class));
        assertEquals(fakeAccount, customer);

    }

    @Test
    public void testSaveOrUpdateSavings() {

        // setup
        Account fakeAccount = new SavingsAccount();
        when(em.merge(any(Account.class))).thenReturn(fakeAccount);

        // exercise
        Account customer = customerDao.saveOrUpdate(fakeAccount);

        // verify
        verify(em, times(1)).merge(any(Account.class));
        assertEquals(fakeAccount, customer);

    }

    @Test
    public void testSaveOrUpdateFail() {

        // setup
        Account fakeAccount = new CheckingAccount();
        doThrow(new HibernateException(new RuntimeException())).when(em).merge(any(Account.class));

        // exercise
        customerDao.saveOrUpdate(fakeAccount);

        // verify
        verify(em, times(1)).merge(any(Account.class));
    }

    @Test
    public void testDeleteChecking() {

        // setup
        int fakeId = 9999;
        Account fakeAccount = new CheckingAccount();
        fakeAccount.setId(fakeId);
        when(em.find(Account.class, fakeId)).thenReturn(fakeAccount);

        // exercise
        customerDao.delete(fakeId);

        // verify
        verify(em, times(1)).remove(fakeAccount);

    }

    @Test
    public void testDeleteSavings() {

        // setup
        int fakeId = 9999;
        Account fakeAccount = new SavingsAccount();
        fakeAccount.setId(fakeId);
        when(em.find(Account.class, fakeId)).thenReturn(fakeAccount);

        // exercise
        customerDao.delete(fakeId);

        // verify
        verify(em, times(1)).remove(fakeAccount);

    }

    @Test
    void testDeleteFail() {


        // setup
        int fakeId = 9999;
        doThrow(new HibernateException(new RuntimeException())).when(em).find(eq(Account.class), anyInt());

        // exercise
        customerDao.delete(fakeId);

        // verify
        verify(em, times(1)).remove(any(Account.class));


    }
}
