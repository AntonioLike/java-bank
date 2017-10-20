package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(Integer id);

    double getBalance(Integer id);

    Set<Integer> getCustomerAccountIds(Integer id);

}
