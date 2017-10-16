package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import java.util.Set;

public interface CustomerService extends CRUDService<Customer> {

    double getBalance(Integer id);

    Set<Integer> getCustomerAccountIds(Integer id);

}
