package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.persistence.jpa.models.JpaCustomerDAO;
import org.academiadecodigo.javabank.services.CustomerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaCustomerService implements CustomerService {
    JpaCustomerDAO customerDAO;
    JpaTransactionManager tm;

    @Override
    public Customer findById(Integer id) {

    }

    public JpaCustomerService(JpaCustomerDAO customerDAO, JpaTransactionManager tm) {
        this.customerDAO = customerDAO;
        this.tm = tm;
    }

    @Override
    public double getBalance(Integer id) {
        tm.beginRead();
        try{
            Customer customer = customerDAO.findById(id);
            if(customer == null){
                throw new IllegalArgumentException("Customer does not exist");
            }

            List<Account> accounts = customer.getAccounts();

            double balance = 0;
            for (Account account : accounts){
                balance += account.getBalance();
            }

            return balance;
        } finally {
            tm.commit();
        }
    }

    @Override
    public Set<Integer> getCustomerAccountIds(Integer id) {
        tm.beginRead();

        try{
            Set<Integer> accountIds = new HashSet<>();
            Customer customer = customerDAO.findById(id);

            if (customer == null) {
                throw new IllegalArgumentException("Customer does not exist");
            }

            List<Account> accounts = customer.getAccounts();

            for (Account account : accounts) {
                accountIds.add(account.getId());
            }

            return accountIds;

        } finally {
            tm.commit();
        }

    }
}
