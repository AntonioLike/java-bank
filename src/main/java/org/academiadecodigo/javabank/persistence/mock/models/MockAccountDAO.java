package org.academiadecodigo.javabank.persistence.mock.models;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.persistence.AccountDAO;
import org.academiadecodigo.javabank.persistence.mock.MockGenericDAO;

import java.util.List;

public class MockAccountDAO extends MockGenericDAO implements AccountDAO {
    @Override
    public List findAll() {
        return null;
    }

    @Override
    public AbstractModel findById(Integer id) {
        return null;
    }

    @Override
    public AbstractModel saveOrUpdate(AbstractModel modelObject) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
