package org.academiadecodigo.javabank.persistence;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.account.Account;

import java.util.List;

public interface AccountDAO extends DAO<Account> {
    @Override
    List<Account> findAll();

    @Override
    Account findById(Integer id);

    @Override
    Account saveOrUpdate(Account modelObject);

    @Override
    void delete(Integer id);
}
