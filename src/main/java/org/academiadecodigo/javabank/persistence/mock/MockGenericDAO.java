package org.academiadecodigo.javabank.persistence.mock;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.persistence.DAO;

import java.util.List;

public class MockGenericDAO implements DAO {
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
