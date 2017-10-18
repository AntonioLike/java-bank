package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;

public class JpaAccountDao extends GenericJpaDao<Account> implements AccountDao {

    public JpaAccountDao() {
        super(Account.class);
    }
}
