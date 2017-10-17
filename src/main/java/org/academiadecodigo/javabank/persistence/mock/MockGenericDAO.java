package org.academiadecodigo.javabank.persistence.mock;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.persistence.DAO;

import java.util.List;

public class MockGenericDAO<T extends AbstractModel>  implements DAO<T> {
    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T findById(Integer id) {
        return null;
    }

    @Override
    public T saveOrUpdate(T modelObject) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
