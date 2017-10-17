package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.SessionManager;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

public class JpaAccountDao extends GenericJpaDao<Account> implements AccountDao {

    public JpaAccountDao(JpaSessionManager sm) {
        super(sm, Account.class);
    }
}
