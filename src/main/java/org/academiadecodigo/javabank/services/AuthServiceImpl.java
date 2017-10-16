package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import javax.persistence.EntityManagerFactory;

public class AuthServiceImpl implements AuthService {

    private EntityManagerFactory emf;

    public AuthServiceImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private Customer accesingCustomer;
    private CustomerService customerService;

    @Override
    public boolean authenticate(Integer id) {

        accesingCustomer = customerService.findById(id);
        return accesingCustomer != null;
    }

    @Override
    public Customer getAccessingCustomer() {
        return accesingCustomer;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
