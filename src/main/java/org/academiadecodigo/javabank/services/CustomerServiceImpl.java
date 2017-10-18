package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.TransactionManager;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerServiceImpl implements CustomerService {

    private TransactionManager tx;
    private CustomerDao customerDao;

    public void setTransactionManager(TransactionManager tx) {
        this.tx = tx;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer findById(Integer id) {

        try {

            tx.beginRead();
            return customerDao.findById(id);

        } finally {
            tx.commit();
        }
    }

    @Override
    public double getBalance(Integer id) {

        try {

            tx.beginRead();

            Customer customer = customerDao.findById(id);

            if (customer == null) {
                throw new IllegalArgumentException("Customer does not exists");
            }

            List<Account> accounts = customer.getAccounts();

            double balance = 0;
            for (Account account : accounts) {
                balance += account.getBalance();
            }

            return balance;

        } finally {
            tx.commit();
        }
    }

    @Override
    public Set<Integer> getCustomerAccountIds(Integer id) {

        try {

            tx.beginRead();

            Customer customer = customerDao.findById(id);

            if (customer == null) {
                throw new IllegalArgumentException("Customer does not exists");
            }

            Set<Integer> accountIds = new HashSet<>();
            List<Account> accounts = customer.getAccounts();

            for (Account account : accounts) {
                accountIds.add(account.getId());
            }

            return accountIds;

        } finally {
            tx.commit();
        }
    }
}
