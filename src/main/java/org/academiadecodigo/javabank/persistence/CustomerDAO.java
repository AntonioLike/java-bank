package org.academiadecodigo.javabank.persistence;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;

public interface CustomerDAO extends DAO<Customer>{
    @Override
    List<Customer> findAll();

    @Override
    Customer findById(Integer id);

    @Override
    Customer saveOrUpdate(Customer modelObject);

    @Override
    void delete(Integer id);
}
