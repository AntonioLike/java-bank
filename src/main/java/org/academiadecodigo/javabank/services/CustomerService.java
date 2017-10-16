package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.DAO;

import java.util.Set;

public interface CustomerService extends DAO<Customer> {

    double getBalance(Integer id);

    Set<Integer> getCustomerAccountIds(Integer id);

}
