package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.TransactionException;
import org.academiadecodigo.javabank.persistence.TransactionManager;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;
    private TransactionManager tx;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionManager(TransactionManager tx) {
        this.tx = tx;
    }

    @Override
    public Integer add(Account account) {

        Integer id = null;

        try {

            tx.beginWrite();

            id = accountDao.saveOrUpdate(account).getId();

            tx.commit();


        } catch (TransactionException ex) {

            tx.rollback();
        }

        return id;
    }

    @Override
    public void deposit(Integer id, double amount) {

        try {

            tx.beginWrite();

            Account account = accountDao.findById(id);

            if (account == null) {
                tx.rollback();
                throw new IllegalArgumentException("invalid account id");
            }

            account.credit(amount);

            accountDao.saveOrUpdate(account);

            tx.commit();

        } catch (TransactionException ex) {

            tx.rollback();
        }
    }

    @Override
    public void withdraw(Integer id, double amount) {

        try {

            tx.beginWrite();

            Account account = accountDao.findById(id);

            if (account == null) {
                tx.rollback();
                throw new IllegalArgumentException("invalid account id");
            }

            account.debit(amount);

            accountDao.saveOrUpdate(account);

            tx.commit();

        } catch (TransactionException ex) {

            tx.rollback();
        }
    }

    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        try {

            tx.beginWrite();

            Account srcAccount = accountDao.findById(srcId);
            Account dstAccount = accountDao.findById(dstId);

            if (srcAccount == null || dstAccount == null) {
                tx.rollback();
                throw new IllegalArgumentException("invalid account id");
            }

            // make sure transaction can be performed
            if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
                srcAccount.debit(amount);
                dstAccount.credit(amount);
            }

            accountDao.saveOrUpdate(srcAccount);
            accountDao.saveOrUpdate(dstAccount);

            tx.commit();

        } catch (TransactionException ex) {

            tx.rollback();
        }
    }
}


