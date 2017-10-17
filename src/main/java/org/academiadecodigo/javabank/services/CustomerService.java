package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.DAO;

import java.util.Set;

public interface CustomerService{

    double getBalance(Integer id);

    Set<Integer> getCustomerAccountIds(Integer id);

    Customer findById(Integer id);

}
