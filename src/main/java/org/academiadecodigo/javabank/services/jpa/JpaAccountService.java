package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.persistence.jpa.models.JpaAccountDAO;
import org.academiadecodigo.javabank.services.AccountService;

import javax.persistence.RollbackException;

public class JpaAccountService implements AccountService{

    JpaAccountDAO accountDAO;
    JpaTransactionManager tm;

    public JpaAccountService(JpaAccountDAO accountDAO, JpaTransactionManager tm) {
        this.accountDAO = accountDAO;
        this.tm = tm;
    }

    @Override
    public void deposit(Integer id, double amount) {
        tm.beginWrite();
        try{
            Account account = accountDAO.findById(id);
            if (account == null){
                tm.rollback();
                throw new IllegalArgumentException("invalid account id");
            }

            account.credit(amount);
            accountDAO.saveOrUpdate(account);
            tm.commit();

        } catch (RollbackException ex){
            tm.rollback();
        }
    }

    @Override
    public void withdraw(Integer id, double amount) {

        tm.beginWrite();
        try{
            Account account = accountDAO.findById(id);
            if (account == null) {
                tm.rollback();
                throw new IllegalArgumentException("invalid account");
            }

                account.debit(amount);
                accountDAO.saveOrUpdate(account);
                tm.commit();
        } catch (RollbackException ex){
            tm.rollback();
        }
    }

    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        tm.beginWrite();
        try{
            Account srcAccount = accountDAO.findById(srcId);
            Account dstAccount = accountDAO.findById(dstId);

            if(srcAccount == null || dstAccount == null) {
                tm.rollback();
                throw new IllegalArgumentException(("at least one of the account id's is invalid"));
            }

            if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)){
                srcAccount.debit(amount);
                dstAccount.credit(amount);
            }

            accountDAO.saveOrUpdate(srcAccount);
            accountDAO.saveOrUpdate(dstAccount);

            tm.commit();
        } catch (RollbackException ex) {
            tm.rollback();
        }
    }
}
